package com.fuwenhao.interviewTest.LockDemo.atomicDemo;

import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.OrderControl;
import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.OrderService;
import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.OrderServiceImpl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by fwh on 2018/5/24 上午10:36
 */
public class atomicOrderTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        OrderService orderService = new atomicServiceImpl();//放在此处不会重复
        for (int i = 0; i < 20; i++) {
            service.submit(new OrderControl(countDownLatch,orderService));
        }
        countDownLatch.countDown();
        service.shutdown();
    }
}
