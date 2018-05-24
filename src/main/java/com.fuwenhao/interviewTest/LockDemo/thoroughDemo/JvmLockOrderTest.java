package com.fuwenhao.interviewTest.LockDemo.thoroughDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by fwh on 2018/5/24 上午9:14
 * 情景模拟：
 */
public class JvmLockOrderTest {
    public static void main(String[] args) {
        ExecutorService service =Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
//        OrderService orderService = new OrderServiceImpl();//放在此处不会重复
        for (int i = 0; i < 20; i++) {
            OrderService orderService = new OrderServiceImpl();//放在此处会重复
            service.submit(new OrderControl(countDownLatch,orderService));
        }
        countDownLatch.countDown();
        service.shutdown();
    }






    /*@Test
    public void test2(){
        ExecutorService service =Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        OrderService orderService = new OrderServiceImpl();
        for (int i = 0; i < 20; i++) {
            service.submit(new OrderControl(countDownLatch,orderService));
        }
        countDownLatch.countDown();
        service.shutdown();
    }*/

}
