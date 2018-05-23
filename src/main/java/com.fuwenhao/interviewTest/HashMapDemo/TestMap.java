package com.fuwenhao.interviewTest.HashMapDemo;

import org.junit.Test;

/**
 * create by fwh on 2018/5/23 下午4:19
 */
public class TestMap {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("wukong", 30);
        map.put("wukong", 99);
        System.out.println(map.get("luban"));//返回空值
        System.out.println(map.get("wukong"));//返回最新放入值
    }
    @Test
    public void test1(){
        Long start = System.currentTimeMillis();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("wukong", 30);
        map.put("wukong", 99);
        System.out.println(map.get("luban"));//返回空值
        System.out.println(map.get("wukong"));//返回最新放入值
    }
    @Test
    public void tessFor(){
        Long start = System.currentTimeMillis();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i <100 ; i++) {
            map.put("wukong"+i, i);
        }
        for (int i = 0; i <100; i++) {
            System.out.println(map.get("wukong"+i));
        }
        Long end =System.currentTimeMillis();
        System.out.println("大小为："+map.size()+"耗时为："+(end-start));
    }

}
