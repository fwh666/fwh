package com.fuwenhao.interviewTest.LockDemo.redisDemo;

import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.OrderService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create by fwh on 2018/5/24 上午11:23
 */
public class OrderRedisServiceImpl implements OrderService {
    //连接redis
    static JedisPool jedisPool;
    static {
        jedisPool =new JedisPool(new JedisPoolConfig(),"10.1.1.101",6379,100);
//        jedisPool =new JedisPool(new JedisPoolConfig(),"10.1.1.101",6379,100,"");//末尾有的需要输入密码

    }

    @Override
    public String getOrder() {
        try{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDDHHmmss");
        Jedis jedis = jedisPool.getResource();//此处代码断点没走需确认。
        return simpleDateFormat.format(new Date()) + jedis.incr("order_key");
        }finally {
            jedisPool.close();//注意一定要释放资源。否则无法正常输出结果。
        }

    }
}
