package com.fuwenhao.interviewTest.RedisDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * create by fwh on 2018/5/26 下午12:15
 */
public class JedisUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JedisUtil.class);

    private static ConcurrentMap<String, JedisPool> jedisPools = new ConcurrentHashMap<String, JedisPool>();

    private static ConcurrentMap<String, String> locks = new ConcurrentHashMap<String, String>();

    private static JedisPool getPool(String host, int port, int database,
                                     int timeout, String password) {
        String key = host + ":" + port + ":" + database;
        JedisPool pool = null;

        // 线程池Map里已有对应的JedisPool则直接返回，提高效率
        if (jedisPools.containsKey(key)) {
            pool = jedisPools.get(key);
            return pool;
        }

        // 同步代码块防止多个线程同时产生多个相同key的JedisPool
        synchronized (JedisUtil.class) {
            if (!jedisPools.containsKey(key)) {
                JedisPoolConfig config = new JedisPoolConfig();
                // 1个JedisPool最多分配的Jedis实例数
                config.setMaxTotal(100);
                // borrow一个Jedis实例时最大的等待时长
                config.setMaxWaitMillis(10000);
                // 1个JedisPool中最多存在的状态为idle的Jedis实例数
                config.setMaxIdle(10);
                // 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的
                config.setTestOnBorrow(true);
                // 在return给pool时，是否提前进行validate操作
                config.setTestOnReturn(true);
                try {
                    /*
                     * 如果遇到 java.net.SocketTimeoutException: Read timed out
                     * exception的异常信息 请尝试在构造JedisPool的时候设置自己的超时值.
                     * JedisPool默认的超时时间是2秒
                     */
                    pool = new JedisPool(config, host, port, timeout, password,
                            database);
                    jedisPools.put(key, pool);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                pool = jedisPools.get(key);
            }
        }
        return pool;
    }

    public static Jedis getJedis(String host, int port) {
        return getJedis(host, port, Protocol.DEFAULT_DATABASE);
    }

    public static Jedis getJedis(String host, int port, int database) {
        return getJedis(host, port, database, Protocol.DEFAULT_TIMEOUT);
    }

    public static Jedis getJedis(String host, int port, int database,
                                 int timeout) {
        return getJedis(host, port, database, timeout, null);
    }

    public static Jedis getJedis(String host, int port, int database,
                                 int timeout, String password) {
        Jedis jedis = null;
        try {
            jedis = getPool(host, port, database, timeout, password)
                    .getResource();
        } catch (Exception e) {
            LOG.error("get redis master failed.", e);
        }

        return jedis;
    }

    /**
     * 释放redis实例到连接池.
     *
     * @param jedis redis实例
     */
    public static void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 锁在给定的等待时间内空闲，则获取锁成功，返回true，否则返回false
     *
     * @param jedis          Jedis实例
     * @param lockKey        需要加锁的key
     * @param lockTimeout    锁超时时间，单位秒
     * @param acquireTimeout 获取锁超时时间，为0则立即返回
     * @param unit           获取锁超时时间单位
     * @return 是否获取到锁
     */
    public static boolean acquireLockWithTimeout(Jedis jedis, String lockKey,
                                                 int lockTimeout, long acquireTimeout, TimeUnit unit) {
        String uuid = UUID.randomUUID().toString();
        String lockName = "lock:" + lockKey;
        try {
            long nano = System.nanoTime();
            do {
                LOG.debug("Try lock key: " + lockKey);
                // 获取锁
                String result = jedis.set(lockName, uuid, "NX");
                if ("OK".equals(result)) {
                    // 设置锁过期时间，客户端在执行介于setnx和expire之间的时候崩溃会导致死锁
                    jedis.expire(lockName, lockTimeout);
                    locks.put(lockName, uuid);
                    LOG.debug("Get lock, key: {}, expire in {} seconds.",
                            lockKey, lockTimeout);
                    return true;
                } else {
                    // 获取锁失败，检查锁的超时时间
                    if (jedis.ttl(lockName) < 0) {
                        jedis.expire(lockName, lockTimeout);
                    }

                    if (LOG.isDebugEnabled()) {
                        String value = jedis.get(lockName);
                        LOG.debug("key:{} locked by another business:{}.",
                                lockKey, value);
                    }
                }
                if (acquireTimeout == 0) {
                    break;
                }
                // TODO 重试间隔设置
                Thread.sleep(1000);
            } while ((System.nanoTime() - nano) < unit.toNanos(acquireTimeout));
            return false;
        } catch (Exception e) {
            LOG.error("get lock exception", e);
        }
        return false;
    }

    /**
     * 如果锁空闲立即返回，获取失败，一直等待
     *
     * @paramlockName
     */
    public static void acquireLockWaitUntilGet(Jedis jedis, String lockKey,
                                               int lockTimeout) {
        String uuid = UUID.randomUUID().toString();
        String lockName = "lock:" + lockKey;
        try {
            do {
                LOG.debug("Try lock key: " + lockName);
                String result = jedis.set(lockName, uuid, "NX");
                if ("OK".equals(result)) {
                    jedis.expire(lockName, lockTimeout);
                    locks.put(lockName, uuid);
                    LOG.debug("Get lock, key: {}, expire in {} seconds.",
                            lockName, lockTimeout);
                    return;
                } else {
                    // 获取锁失败，检查锁的超时时间
                    if (jedis.ttl(lockName) < 0) {
                        jedis.expire(lockName, lockTimeout);
                    }

                    if (LOG.isDebugEnabled()) {
                        String value = jedis.get(lockName);
                        LOG.debug("key:{} locked by another business:{}.",
                                lockName, value);
                    }
                }
                Thread.sleep(100);
            } while (true);
        } catch (Exception e) {
            LOG.error("get lock exception", e);
        }
    }

    public static boolean releaseLock(Jedis jedis, String lockKey) {
        String lockname = "lock:" + lockKey;
        String identifier = locks.get(lockname);
        String lockValue = jedis.get(lockname);
        Pipeline pipe = jedis.pipelined();
        while (true) {
            try {
                pipe.watch(lockname);
                // 检查进程是否仍然持有锁
                // 若在pipeline之后调用以下代码，第一次会返回OK，第二次调用才会返回value，导致if条件失效
                // String lockValue = jedis.get(lockname);
                // if (identifier != null
                // && identifier.equals(jedis.get(lockname))) {

                if (identifier != null && identifier.equals(lockValue)) {
                    // 释放锁，防止程序执行到这里时，别的进程修改了锁;或者当前线程阻塞了很长时间，过了超时时间再释放锁，
                    // 这时锁已被其他线程获得，所以要比较唯一的随机串
                    // Thread.sleep(10000);
                    pipe.multi();
                    pipe.del(lockname);
                    pipe.exec();
                    pipe.sync();
                    return true;
                }
                break;
            } catch (Exception e) {
                // 有其他客户端修改了锁，重试
                LOG.error("release lock exception", e);
            } finally {
                locks.remove(lockname);
            }
        }
        // 进程已失去锁
        return false;
    }

    public static String getLockIdentifier(String lockKey) {
        return locks.get("lock:" + lockKey);
    }
}

