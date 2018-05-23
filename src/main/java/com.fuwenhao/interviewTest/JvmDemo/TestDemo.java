package com.fuwenhao.interviewTest.JvmDemo;

import org.junit.Test;

/**
 * create by fwh on 2018/5/23 下午9:16
 * 测试自定义类加载器；
 *  目前代码有问题，无法记载本地路径的class
 */
public class TestDemo {
    public static void main(String[] args) {
        try {
            MyClassLoaderDemo loaderDemo = new MyClassLoaderDemo("zhangfei","/Users/fwh/A_FWH/GItHub/fwh/target/classes/com/fuwenhao/interviewTest/JvmDemo/");

//            Class<?> c = loaderDemo.loadClass("com.fuwenhao.interviewTest.JvmDemo.Demo");//加载指定包下面的类
            Class<?> c = loaderDemo.loadClass("Demo");//

            c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
