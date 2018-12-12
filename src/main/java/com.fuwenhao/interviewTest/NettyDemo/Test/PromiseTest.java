package com.fuwenhao.interviewTest.NettyDemo.Test;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

/**
 * create by fwh on 2018/6/7 下午3:52
 * 采用netty来实现异步传输
 */
public class PromiseTest {
    public static void main(String[] args) {
        //线程池
        EventExecutorGroup group = new DefaultEventExecutorGroup(1);
        //向线程池中提交任务，并返回Future，该Future是netty自己实现的future
        //位于io.netty.util.concurrent包下，此处运行时的类型为PromiseTask
        Future<?> f = group.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("任务正在执行");
                //模拟耗时操作，比如IO操作
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务执行完毕");
            }
        });
        //增加监听
        f.addListener( new FutureListener() {
            @Override
            public void operationComplete(Future arg0) throws Exception {
                System.out.println("ok!!!");
            }
        });
        System.out.println("main thread is running.");
    }
}
