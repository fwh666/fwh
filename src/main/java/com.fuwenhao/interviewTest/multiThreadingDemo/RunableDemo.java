package com.fuwenhao.interviewTest.multiThreadingDemo;

/**
 * Created by FWH on 2018-06-12.
 * Thread和Runnable的区别
 如果一个类继承Thread，则不适合资源共享。但是如果实现了Runable接口的话，则很容易的实现资源共享。

 总结：

 实现Runnable接口比继承Thread类所具有的优势：

 1）：适合多个相同的程序代码的线程去处理同一个资源

 2）：可以避免java中的单继承的限制

 3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立

 4）：线程池只能放入实现Runable或callable类线程，不能直接放入继承Thread的类
 */
public class RunableDemo implements Runnable {
    private String name;
    public RunableDemo(String name){
        this.name=name;
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name+"___"+i);
            try {
                Thread.sleep((int)Math.random()*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        RunableDemo runableDemo1 = new RunableDemo("fuwenhao");
//        RunableDemo runableDemo2 = new RunableDemo("sishuiliuan");
        new Thread(new RunableDemo("fuwenhao")).start();
        new Thread(new RunableDemo("似水刘安")).start();
    }
}
