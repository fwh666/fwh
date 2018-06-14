package com.fuwenhao.interviewTest.ThreadPoolDemo;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by FWH on 2018-06-14.
 * Future返回线程结果值
 */
public class FutureThredDemo implements Callable<Integer>{
    private String name;

    public FutureThredDemo(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("task:"+name+"开始");
        int sum = new Random().nextInt(3000);
        int result=0;
        for (int i = 0; i < sum; i++) {
            result+=i;
        }
        return result;
    }

    /**
     * 用线程池submit来实现
     * @param args
     */
   /* public static void main(String[] args) {
        System.out.println("开始时间"+System.nanoTime());
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> fuwenhao = executorService.submit(new FutureThredDemo("fuwenhao"));
        Future<Integer> songxuedong = executorService.submit(new FutureThredDemo("songxuedong"));
        Future<Integer> qiaojianguo = executorService.submit(new FutureThredDemo("qiaojianguo"));
        executorService.shutdown();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("fuwenhao:"+fuwenhao.get());
            System.out.println("songxuedong:"+songxuedong.get());
            System.out.println("qiaojianguo:"+qiaojianguo.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("结束时间"+System.nanoTime());
    }*/
    /**
     * 用new thread来实现
     */
    public static void main(String[] args) {
        System.out.println("开始时间"+System.nanoTime());
        FutureTask<Integer> fuwenhao = new FutureTask<>(new FutureThredDemo("fuwenhao"));
        FutureTask<Integer> songxuedong = new FutureTask<>(new FutureThredDemo("songxuedong"));
        FutureTask<Integer> qiaojianguo = new FutureTask<>(new FutureThredDemo("qiaojianguo"));
        new Thread(fuwenhao).start();
        new Thread(songxuedong).start();
        new Thread(qiaojianguo).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("fuwenhao:"+fuwenhao.get());
            System.out.println("songxuedong:"+songxuedong.get());
            System.out.println("qiaojianguo:"+qiaojianguo.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("结束时间"+System.nanoTime());

    }
}
