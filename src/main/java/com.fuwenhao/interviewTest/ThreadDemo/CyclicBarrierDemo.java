package com.fuwenhao.interviewTest.ThreadDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by fwh on 2018/5/23 下午12:17
 * CyclicBarrier ---翻译：栅栏
 * 模拟情景：
 *      三个用户用餐，当三个用户全部到齐后开始点餐。
 *      人员全部到齐后才能吃饭（同步点）
 *      提前到的人只能等待（阻塞点）
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //创建栅栏
        final CyclicBarrier cd = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("人员到齐，拍照留恋");
                try {
                    Thread.sleep((long) (Math.random()*1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });//三个用户聚餐
        ExecutorService threadPool = Executors.newCachedThreadPool();//创建线程池
        for (int i = 0; i < 3; i++) {
            //模拟三个用户到齐
            final int user = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                        System.out.println(user +
                                "到达餐厅，当前已有" + (cd.getNumberWaiting()+1)+ "人到达");
                        cd.await();//消息阻塞
                        if(user==1)
                        System.out.println("拍照OK，开始点餐");

                        Thread.sleep((long) (Math.random()*1000));
                        System.out.println(user+"吃完饭，开始回家");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPool.execute(runnable);//线程执行栅栏
        }
        threadPool.shutdown();//关闭线程池 释放资源
    }
}
