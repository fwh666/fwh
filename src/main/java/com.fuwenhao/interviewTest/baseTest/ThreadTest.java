package com.fuwenhao.interviewTest.baseTest;

import org.junit.Test;

/**
 * create by fwh on 2018/6/8 下午7:12
 * 面试题：java多线程
 * ---面试题-设计4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少
 * ---一道面试题 设计4个线程，其中两个每次对j增加1，另外两个对j每次减少1。循环100次。
 * -- https://blog.csdn.net/never_cxb/article/details/50379047
 */
public class ThreadTest {
    private int j;

    //线程一增加
    public synchronized void inc() {
        j++;
        System.out.println("inc当前线程名：" + Thread.currentThread().getName() + "_inc_" + j);//输出结果
    }

    //线程二递减
    public synchronized void dec() {
        j--;
        System.out.println("dec当前线程名：" + Thread.currentThread().getName() + "_dec_" + j);//输出结果
    }

    //内部实现线程类
    class Inc implements Runnable {

        @Override
        public void run() {
            //循环一百次
            for (int i = 0; i < 10; i++) {
                inc();
            }
        }
    }

    class Dec implements Runnable {

        @Override
        public void run() {
            /*for (int i = 0; i < 10; i++) {
                dec();
            }*/
            int j = 0;
            while (j++ < 10) {
                dec();
            }
        }
    }

    //测试入口
    @Test
    public void testThreadOne() {
        ThreadTest threadTest = new ThreadTest();
        Inc inc = threadTest.new Inc();
        Dec dec = threadTest.new Dec();
        for (int i = 0; i < 2; i++) {//// 4个线程（0、1、2、3）
            Thread thread = new Thread(inc);
            thread.start();
            thread = new Thread(dec);
            thread.start();
        }
    }

    @Test
    public void testThreadTwo() {
        ThreadTest threadTest = new ThreadTest();
        Inc inc = threadTest.new Inc();
        Dec dec = threadTest.new Dec();
        Thread thread = null;
        for (int i = 0; i < 2; i++) {
            thread = new Thread(inc);
            thread.start();
        }
        for (int i = 0; i < 2; i++) {
            thread = new Thread(dec);
            thread.start();
        }

    }


}
