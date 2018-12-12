package com.fuwenhao.test;

/**
 * create by fwh on 2018/12/12 下午2:00
 * 建立三个线程，
 * A线程打印10次A，B线程打印10次B,C线程打印10次C，
 * 要求线程同时运行，交替打印10次ABC。
 * 这个问题用Object的wait()，notify()就可以很方便的解决。
 * 效果：
 *  ABCABCABCABCABCABCABCABCABCABC
 * 原理：
 *  打印A时候，占用c a锁，打印B时候，占用a b锁。
 *  打印B：先得到A锁，申请B锁，打印B，释放B锁
 *  打印C：先得到B锁，申请C锁，打印C，释放C锁
 *
 *  用C唤醒，打印A，A唤醒，C等待。
 *  用A唤醒，打印B，B唤醒，A等待。
 *  用B唤醒，打印C，C唤醒，B等待。
 *
 *
 */
public class ThreadInterviewDemo implements Runnable{
    private Object before;
    private Object current;
    private String name;

    private ThreadInterviewDemo(String name,Object before,Object current){
        this.name=name;
        this.before=before;
        this.current=current;
    }

    @Override
    public String toString() {
        return "ThreadInterviewDemo{" +
                "before=" + before +
                ", current=" + current +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void run() {
        int count =10;
        while (count>0){
            synchronized (before){
                synchronized (current){
                    System.out.print(name);
                    count--;
                    current.notify();
                }
                try {
                    before.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadInterviewDemo a1 = new ThreadInterviewDemo("A",c,a);
        ThreadInterviewDemo b1 = new ThreadInterviewDemo("B",a,b);
        ThreadInterviewDemo c1 = new ThreadInterviewDemo("C",b,c);

        Thread a11 = new Thread(a1);
        if (a11.isDaemon()){
            System.out.println("是守护线程");
        }else {
            System.out.println("不是守护线程");
        }
        a11.start();



        Thread.sleep(100);
        new Thread(b1).start();
        Thread.sleep(100);
        new Thread(c1).start();
        Thread.sleep(100);


    }
}
