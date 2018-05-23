package com.fuwenhao.interviewTest.HashMapDemo;

/**
 * create by fwh on 2018/5/23 下午3:33
 */
public class HashMap<K,V> implements Map<K,V>{
    private static int defaultLength=16;//默认初始化容量
    private static double defaultLoader=0.75;//默认扩容临界点


    private Entry[] table =null; //模拟hashmap入口；对象。
    private  int size = 0;          //大小


   /* public HashMap(Entry<K, V> table, int size) {
        this.table = table;
        this.size = size;
    }*/


    public HashMap(){
        this(defaultLength,defaultLoader);
    }

    public HashMap(int length,double loader){
        defaultLength=length;
        defaultLoader=loader;
        table =new Entry[defaultLength];
    }





    //放入值
    @Override
    public V put(K k,V v) {
        size++;//用于计算循环放入值的大小
        int index = hash(k);//定义hash算法存值
        Entry<K,V> entry =table[index];//将值放入entry中
        if(entry==null){
            table[index] =newEntry(k,v,null);
        }else {
            table[index] =newEntry(k,v,entry);
            /*
            如出现键重复的情况下，会将老值放入entry中，新值放入对应的键中。
             */
//            System.out.println("老值是："+table[index].next.getValue());
        }
        return (V) table[index].getValue();
    }


    //定义一个hash方法
    public int hash(K k){
        int m = defaultLength;
        int i =k.hashCode()%m;
        return i>=0?i:-i;
    }

    //定义方法
    public Entry<K, V> newEntry(K k,V v,Entry<K,V> next){
        return new Entry<K,V>(k,v,next);
    }


    //获取值
    @Override
    public V get(K key) {
        int index = hash(key);
        if(table[index]==null){
            return null;
        }

        return find(key,table[index]);//如果不为空，寻找对应的值
    }
    //查找对应的键值。
    public V find(K k,Entry<K,V> entry){
        if(k==entry.getKey()||k.equals(entry.getKey())){
            if(entry.next!=null)
                System.out.println("1get方法中获取老值："+entry.next.getValue());
            return entry.getValue();
        }else {
            if(entry.next!=null){
                System.out.println("2get方法中获取老值："+entry.next.getValue());
                return  find(k,entry.next);
            }
        }
        return  null;
    }


    @Override
    public int size() {
        return size;
    }






    class Entry<K,V> implements Map.Entry<K,V>{

        K k;
        V v;
        Entry<K,V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
