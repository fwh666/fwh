package com.fuwenhao.interviewTest.JvmDemo;

import org.junit.Test;

/**
 * create by fwh on 2018/5/23 下午8:43
 * 类加载器
 */
public class Demo2 {
    @Test
    public void test1(){
        //sun.misc.Launcher$AppClassLoader@4dfd245f  --系统类加载器
        System.out.println(Demo2.class.getClassLoader());
    }
    @Test
    public void test2(){
        //sun.misc.Launcher$AppClassLoader@4dfd245f  --系统类加载器
//        System.out.println(Demo2.class.getClassLoader());
        ClassLoader loader=  Demo2.class.getClassLoader();
        System.out.println("当前类："+loader);
        while (loader!=null){
            System.out.println("当前类的父类："+loader.getParent());
        }
        System.out.println(loader);
    }
}
