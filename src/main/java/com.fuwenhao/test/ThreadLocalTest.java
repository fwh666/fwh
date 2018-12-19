package com.fuwenhao.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by fwh on 2018/12/12 下午3:11
 */
public class ThreadLocalTest {
    //创建一个Integer型的线程本地变量
    static final ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    static class Task implements Runnable{
        private int num;

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            //获取当前线程的本地变量，然后累加10次
            Integer i = local.get();
            while(++i<10);
            System.out.println("Task " + num + "local num resutl is " + i);
        }
    }

    static class TestThread extends Thread{
        @Override
        public void run() {
//            super.run();
            System.out.println("测试继承的方法是否能被放入线程池中.:"+local.get());
        }
    }
    static class CallableTest implements Callable{

        @Override
        public Object call() throws Exception {
            System.out.println("Callable有返回值和抛出异常");
            return null;
        }
    }

    static void Test1(){
        System.out.println("main thread begin");
        ExecutorService executors = Executors.newCachedThreadPool();
        for(int i =1;i<=5;i++) {
//            executors.execute(new Task(i));
//            executors.execute(new TestThread());//测试
            TestThread testThread = new TestThread();
            boolean b = Thread.holdsLock(testThread);//判断线程是否拥有锁。
            executors.submit(testThread);

            //测试输出
            CallableTest callableTest = new CallableTest();
            try {
                callableTest.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executors.shutdown();
        System.out.println("main thread end");
    }

    public static void main(String[] args){
        Test1();
    }
}
