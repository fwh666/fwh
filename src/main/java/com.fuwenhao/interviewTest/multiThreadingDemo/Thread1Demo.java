package com.fuwenhao.interviewTest.multiThreadingDemo;

/**
 * Created by FWH on 2018-06-12.
 * 实现多线程的三种方式:
 *  继承thread        --注意是run而不是Run
 *  实现runable 推荐使用Runable
 *  实现Callable并与Future、线程池结合使用
 */
public class Thread1Demo extends Thread {
    private String name;
    public Thread1Demo(String name){
        this.name=name;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("姓名:"+name+"数值_:"+i+"");
            try {
                sleep((int) (Math.random()* 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Thread1Demo thread1 = new Thread1Demo("fuwenhao");
        Thread1Demo thread2 = new Thread1Demo("似水流年");
        thread1.start();
        thread2.start();
    }
}
