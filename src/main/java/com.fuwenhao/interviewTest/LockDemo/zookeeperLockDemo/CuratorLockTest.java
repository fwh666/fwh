package com.fuwenhao.interviewTest.LockDemo.zookeeperLockDemo;

import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.OrderControl;
import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.OrderService;
import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.OrderServiceImpl;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by fwh on 2018/5/24 上午10:47
 * 模拟：
 *      zookeeper的分布式锁机制，
 * 注意：
 *      需要加载jar包
 */
public class CuratorLockTest {
    final static CuratorFramework curatorFramework =
            CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").
                    retryPolicy(new ExponentialBackoffRetry(100, 1)).
                    build();

    public static void main(String[] args) {
        curatorFramework.start();


        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        InterProcessMutex lock = new InterProcessMutex(curatorFramework,"/fuwenhao");

        for (int i = 0; i < 20; i++) {
//            OrderService orderService = new OrderServiceImpl();//放在此处会重复
            service.submit(new OrderControl2(countDownLatch,new OrderNoLockServiceImpl(),lock));
        }
        countDownLatch.countDown();
        service.shutdown();
    }


}
