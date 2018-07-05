package com.fuwenhao.interviewTest.ThreadingMultiDemo.ThreadDemoScene;

/**
 * create by fwh on 2018/7/5 下午3:54
 */
public class KeyPersonRunable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始出现");
        for (int i = 0; i < 1; i++) {
            System.out.println(Thread.currentThread().getName()+"鸡腿各路神仙");
        }
        System.out.println(Thread.currentThread().getName()+"结束");
    }
}
