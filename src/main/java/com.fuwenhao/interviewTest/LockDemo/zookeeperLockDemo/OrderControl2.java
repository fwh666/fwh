package com.fuwenhao.interviewTest.LockDemo.zookeeperLockDemo;

import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.OrderService;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.CountDownLatch;

/**
 * create by fwh on 2018/5/24 上午9:40
 */
public class OrderControl2 implements Runnable {

    private CountDownLatch countDownLatch;
    private OrderService orderService;
    InterProcessMutex lock;

    public OrderControl2(CountDownLatch countDownLatch, OrderService orderService, InterProcessMutex lock) {
        this.countDownLatch = countDownLatch;
        this.orderService = orderService;
        this.lock =lock;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            lock.acquire();
            System.out.printf("类名%s 订单号：%s \r\n",Thread.currentThread().getName(),orderService.getOrder());
            lock.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
