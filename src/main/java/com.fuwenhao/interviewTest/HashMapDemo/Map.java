package com.fuwenhao.interviewTest.HashMapDemo;

/**
 * create by fwh on 2018/5/23 下午3:15
 */
public interface Map<K,V> {
    public V put(K k,V v);
    public V get(K k);
    public  int size();
    public  interface Entry<K,V>{
        public K getKey();
        public V getValue();
    }
}
