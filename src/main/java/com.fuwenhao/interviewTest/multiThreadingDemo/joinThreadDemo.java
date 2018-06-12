package com.fuwenhao.interviewTest.multiThreadingDemo;

/**
 * Created by FWH on 2018-06-12.
 * ②join():指等待t线程终止。
 使用方式。
 join是Thread类的一个方法，启动线程后直接调用，即join()的作用是：“等待该线程终止”，这里需要理解的就是该线程是指的主线程等待子线程的终止。
 也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了才能执行。
 */
public class joinThreadDemo extends Thread{
    private String name;
    public joinThreadDemo(String name){
        this.name=name;
    }
    public void run(){
        System.out.println("线程开始:"+name);
        for (int i = 0; i < 5; i++) {
            System.out.println(name+"子线程输出"+i);
            try {
                sleep((int)Math.random()*100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程结束:"+name);
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"主线程运行开始!");
        joinThreadDemo joinThreadDemo =new joinThreadDemo("fuwenhao");
        joinThreadDemo joinThreadDemo2 =new joinThreadDemo("松树林");
        joinThreadDemo.start();
        joinThreadDemo2.start();
        //使用join方法. 让主线程等待子线程结束.再执行.
        try {
            joinThreadDemo.join();
            joinThreadDemo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");
    }
}
