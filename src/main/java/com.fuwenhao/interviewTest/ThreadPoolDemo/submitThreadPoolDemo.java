package com.fuwenhao.interviewTest.ThreadPoolDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by FWH on 2018-06-12.
 * 除了使用execute，也可以使用submit，它们两个的区别是一个使用有返回值，一个没有返回值。
 * submit的方法很适应于生产者-消费者模式，通过和Future结合一起使用，可以起到如果线程没有返回结果，就阻塞当前线程等待线程 池结果返回。
 *
 * submit中的线程要实现接口Callable
 */
public class submitThreadPoolDemo  implements Callable<String>{
    private int id;

    public submitThreadPoolDemo(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "submitThreadPoolDemo{" +
                "i=" + id +
                '}';
    }
    @Override
    public String call() throws Exception {
        System.out.println("call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());
        //一个模拟耗时的操作
        for (int i = 99; i > 0; i--) ;
        return"call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> sort = new ArrayList<Future<String>>();
        for (int i = 0; i <10; i++) {
            Future<String> future =executorService.submit(new submitThreadPoolDemo(i));
            sort.add(future);
//            System.out.println(future.toString());
        }
        executorService.shutdown();
        for (Future<String> stringFuture : sort) {
            System.out.println("返回值-->:"+stringFuture.get());
        }
    }


}

