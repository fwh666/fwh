package com.fuwenhao.interviewTest.LockDemo.thoroughDemo;

import java.util.concurrent.CountDownLatch;

/**
 * create by fwh on 2018/5/24 上午9:40
 */
public class OrderControl implements Runnable {

    private CountDownLatch countDownLatch;
    private OrderService orderService;

    public OrderControl(CountDownLatch countDownLatch, OrderService orderService) {
        this.countDownLatch = countDownLatch;
        this.orderService = orderService;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("线程名%s 订单号：%s \r\n",Thread.currentThread().getName(),orderService.getOrder());

    }
}
