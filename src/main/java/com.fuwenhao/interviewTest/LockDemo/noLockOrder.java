package com.fuwenhao.interviewTest.LockDemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by fwh on 2018/5/24 上午9:14
 * 情景模拟：
 *      无锁情况下，多个线程访问统一资源。
 */
public class noLockOrder {
    public static void main(String[] args) {
        ExecutorService service =Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 20; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println("线程名:"+Thread.currentThread().getName()+"_订单号："+getOrder());
                    //另一种表达方式：
                    System.out.printf("线程名%s 订单号：%s \r\n",Thread.currentThread().getName(),getOrder());
                }
            });
        }
        countDownLatch.countDown();
        service.shutdown();
    }
    static int num =0;//此处代码为了实现获取不同的订单号，防止重复。
    synchronized public static String getOrder(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDDHHmmss");
        return  simpleDateFormat.format(new Date())+num++;
    }
}
