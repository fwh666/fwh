package com.fuwenhao.interviewTest.ThreadDemo;

import java.util.concurrent.CountDownLatch;

/**
 * create by fwh on 2018/5/23 下午1:44
 * 模拟情景：
 *       倒计时计数器；
 *       等待其他任务完成后，开始执行。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);//两个任务
        new Thread(){
            public void run(){
                try {
                    System.out.println("任务一正在执行。。");
                    Thread.sleep((long) (Math.random()*1000));
                    System.out.println("任务一已经完成。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    System.out.println("任务二正在执行。。");
                    Thread.sleep((long) (Math.random()*10000));
                    System.out.println("任务二已经完成。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        }.start();

        //主线程任务：
        System.out.println("等待其他两个线程完成后，再开始执行："+Thread.currentThread().getName());
        try {
            countDownLatch.await();
            System.out.println("两个线程执行完毕，开始主线程："+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
