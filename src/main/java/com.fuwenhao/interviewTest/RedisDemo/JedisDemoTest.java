package com.fuwenhao.interviewTest.RedisDemo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * create by fwh on 2018/5/26 下午12:22
 */
public class JedisDemoTest {
    private static final String ip = "127.0.0.1";
    private static final int port = 6379;
    private static Jedis jedis = null;

    //加载之前要进行初始化
    @BeforeClass
    public static void init() {
//        Jedis jedis = JedisUtil.getJedis(ip, port);
        jedis = JedisUtils.getInstance().getJedis(ip, port);//注意连接后，要将值返回给jedis
        System.out.println("redis连接成功");
    }

    //之后要关闭资源
    @AfterClass
    public static void close() {
//        JedisUtil.closeJedis(jedis);
        JedisUtils.getInstance().closeJedis(jedis, ip, port);
        System.out.println("关闭redis连接");
    }

    @Test
    public void testKey() {
        //查看所有的key
        Set<String> keys = jedis.keys("*");
        System.out.println("查看所有的key" + keys);
        //查看key是否存在
        System.out.println("查看key是否只存在：" + jedis.exists("key"));
        //设置key
        jedis.set("wenhao", "fuwenhao");
        System.out.println("获取key值为：" + jedis.get("wenhao"));
        //删除key
        jedis.del("wenhao");
    }

    @Test
    public void testList() {
        jedis.flushDB();//清除库数据
        jedis.lpush("lists", "wukong", "孙悟空", "wuneng", "猪八戒");//放入list值
        jedis.lpush("lists", "唐三藏");//往list里面放入
        jedis.lpush("sortList", "3", "8", "9", "2", "0", "3");
        List<String> lists = jedis.lrange("lists", 0, -1);//获取list值
        System.out.println("获取list值：" + lists.toString());
        System.out.println("获取sortLIst值，会自动升序：" + jedis.sort("sortList"));
//        System.out.println("获取sortLIst值，会自动降序："+jedis.sort("sortList",new SortingParams().alpha()));//按字母降序
        System.out.println("获取sortLIst值，会自动降序：" + jedis.sort("sortList", new SortingParams().desc()));//按参数降序--注意方法：SortingParams

//        jedis.sinter("key1","key2");//设置两个key中的交集；
//        jedis.sunion("key1","key2");//求两个key的并集
//        jedis.sdiff("key1","key2");//求两个key的差集
    }

    @Test
    public void testMap() {
        Map map = new HashMap();
        map.put("xiaoming", 5d);
        map.put("xiaoqiang", 4d);
        map.put("xiaohong", 3d);
        map.put("xiaogang", 2d);
        map.put("xiaoli", 51d);
        map.put("xiaobai", 1d);
        jedis.zadd("map", map);//放入
        System.out.println("获取所有元素：" + jedis.zrange("map", 0, -1));
        System.out.println("获取分值：" + jedis.zscore("map", "xiaoming"));
        System.out.println("获取排名：" + jedis.zrank("map", "xiaoming"));//正序0开始
    }

    @Test
    public void testHash() {
        Map<String ,String> map = new HashMap<String, String>();
        map.put("xiaoming", "5d");
        map.put("xiaoqiang", "4");
        map.put("xiaohong", "5d");
        map.put("xiaogang", "5d");
        map.put("xiaoli", "5d");
        map.put("xiaobai", "5d");
        jedis.hmset("hash",map);
        jedis.hset("hash","xiaohao","99d");
        System.out.println(jedis.hgetAll("hash"));//获取所有hash值
        System.out.println(jedis.hkeys("hash"));//获取所有的key
        System.out.println(jedis.hvals("hash"));//获取所有的值
    }
}
