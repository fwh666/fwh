package com.fuwenhao.interviewTest.ThreadingMultiDemo;

/**
 * Created by FWH on 2018-06-12.
 * 面试题:
 *   建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC。
 *   这个问题用Object的wait()，notify()就可以很方便的解决。
 *----------------------------------------------------------------------
 * Obj.wait()，与Obj.notify()必须要与synchronized(Obj)一起使用.
 * 从语法角度来说就是Obj.wait(),Obj.notify必须在synchronized(Obj){...}语句块内
 * 从功能上来说wait就是说线程在获取对象锁后，主动释放对象锁，同时本线程休眠。直到有其它线程调用对象的notify()唤醒该线程，才能继续获取对象锁，并继续执行。相应的notify()就是对对象锁的唤醒操作。但有一点需要注意的是notify()调用后，并不是马上就释放对象锁的，而是在相应的synchronized(){}语句块执行结束，自动释放锁后，JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。
 */
public class waitThreadDemo implements Runnable {
    /**
     * 分析: 三个线程循环打印ABC.
     * A等待.B打印;
     * B等待.C打印.
     * C等待.A打印.
     * 参数:  上一个等待.当前唤醒打印.
     */
    //构造变量
    private String name;
    private Object prev;//上一个
    private Object self;//自己
    //构造参数
    public waitThreadDemo(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        //实现打印10次
        int count =10;
        while (count>0){
            synchronized(prev){
                synchronized(self){
                    System.out.print(name);
                    count--;
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //测试方法.
    public static void main(String[] args) throws InterruptedException {
        //详情:https://blog.csdn.net/evankaka/article/details/44153709
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        waitThreadDemo A = new waitThreadDemo("A",c,a);//a前面是c
        waitThreadDemo B = new waitThreadDemo("B",a,b);//a前面是c
        waitThreadDemo C = new waitThreadDemo("C",b,c);//a前面是c
        new Thread(A).start();
        Thread.sleep(100);
        new Thread(B).start();
        Thread.sleep(100);
        new Thread(C).start();
        Thread.sleep(100000);
    }
}
