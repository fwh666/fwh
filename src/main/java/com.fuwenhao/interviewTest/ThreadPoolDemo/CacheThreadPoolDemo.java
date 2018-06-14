package com.fuwenhao.interviewTest.ThreadPoolDemo;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.joda.time.Instant.now;

/**
 * Created by FWH on 2018-06-12.
 * CacheThreadPool线程池
 *   1、主线程的执行与线程池里的线程分开，有可能主线程结束了，但是线程池还在运行
     2、放入线程池的线程并不一定会按其放入的先后而顺序执行
 */
public class CacheThreadPoolDemo implements Runnable{
    private String name;
    public CacheThreadPoolDemo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CacheThreadPoolDemo{" +
                "name='" + name + '\'' +
                '}';
    }

    //单线程执行
    @Override
    public void run() {
        System.out.println("开始"+name);
        try {
            Thread.sleep(1000);
            System.out.println("结束"+name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //Chache线程池
  /*  public static void main(String[] args) {
        *//*  int i = Runtime.getRuntime().availableProcessors();//获取当前系统的CPU数目
        ExecutorService executorService = Executors.newFixedThreadPool(i);*//*
        System.out.println("开启线程池"+now());
        ExecutorService executorService = Executors.newCachedThreadPool();//创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
        for (int i = 0; i < 10; i++) {
            executorService.execute(new CacheThreadPoolDemo(String.valueOf(i)));
        }
        executorService.shutdown();//执行到此处并不会马上关闭线程池,但之后不能再往线程池中加线程，否则会报错
        System.out.println("关闭线程池"+now());
    }*/
    //时间调度
    public static void main(String[] args) {
        System.out.println("Main Thread: Starting at: "+ new Date());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 10; i++) {
            scheduledExecutorService.schedule(new CacheThreadPoolDemo(String.valueOf(i)),10, TimeUnit.SECONDS);//延迟10秒执行
        }
        scheduledExecutorService.shutdown(); //执行到此处并不会马上关闭线程池
        while(!scheduledExecutorService.isTerminated()){
            //wait for all tasks to finish
        }
        System.out.println("Main Thread: Finished at:"+ new Date());
    }

}
