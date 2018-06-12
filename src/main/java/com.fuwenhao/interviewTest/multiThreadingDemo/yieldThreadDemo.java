package com.fuwenhao.interviewTest.multiThreadingDemo;

/**
 * Created by FWH on 2018-06-12.
 * sleep()和yield()的区别
 sleep()和yield()的区别):sleep()使当前线程进入停滞状态，所以执行sleep()的线程在指定的时间内肯定不会被执行；yield()只是使当前线程重新回到可执行状态，所以执行yield()的线程有可能在进入到可执行状态后马上又被执行。
 sleep 方法使当前运行中的线程睡眼一段时间，进入不可运行状态，这段时间的长短是由程序设定的，yield 方法使当前线程让出 CPU 占有权，但让出的时间是不可设定的。实际上，yield()方法对应了如下操作：先检测当前是否有相同优先级的线程处于同可运行状态，如有，则把 CPU  的占有权交给此线程，否则，继续运行原来的线程。所以yield()方法称为“退让”，它把运行机会让给了同等优先级的其他线程
 另外，sleep 方法允许较低优先级的线程获得运行机会，但 yield()  方法执行时，当前线程仍处在可运行状态，所以，不可能让出较低优先级的线程些时获得 CPU 占有权。在一个运行系统中，如果较高优先级的线程没有调用 sleep 方法，又没有受到 I\O 阻塞，那么，较低优先级线程只能等待所有较高优先级的线程运行结束，才有机会运行。
 */
public class yieldThreadDemo extends Thread {
    private String name;
    public yieldThreadDemo(String name){this.name=name;}
    public void run(){
        for (int i = 0; i < 20; i++) {
            System.out.println(this.name+"...."+i);
            if(i==15){
                 this.yield();
            }
        }
    }

    public static void main(String[] args) {
        yieldThreadDemo yieldThreadDemo =new yieldThreadDemo("fuwenhao");
        yieldThreadDemo yieldThreadDemo2 =new yieldThreadDemo("四会流年");
        yieldThreadDemo2.start();
        yieldThreadDemo.start();
        //此处设置优先级
        /**
         * setPriority(): 更改线程的优先级。
         　　　　MIN_PRIORITY = 1
         　　   NORM_PRIORITY = 5
                MAX_PRIORITY = 10
         */
        yieldThreadDemo.setPriority(Thread.MAX_PRIORITY);
        yieldThreadDemo2.setPriority(Thread.MIN_PRIORITY);
    }
}
