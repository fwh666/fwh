package com.fuwenhao.interviewTest.LockDemo.redisDemo;

import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.OrderControl;
import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.OrderService;
import com.fuwenhao.interviewTest.LockDemo.zookeeperLockDemo.OrderControl2;
import com.fuwenhao.interviewTest.LockDemo.zookeeperLockDemo.OrderNoLockServiceImpl;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by fwh on 2018/5/24 上午11:21
 */
public class redisLockTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        OrderService orderService = new OrderRedisServiceImpl();
        for (int i = 0; i < 10; i++) {
            service.submit(new OrderControl(countDownLatch,orderService));
        }
        countDownLatch.countDown();
        service.shutdown();
    }
}
