package com.fuwenhao.test;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by fwh on 2018/12/12 上午11:24
 */
public class ThreadTest {

    public static void main(String[] args) {
        System.out.println("主线程开始");
//        new Thread(new RunnableTest("fwh")).start();
//        new Thread(new RunnableTest("wahaha")).start();

        ThreadExtendTest fwh = new ThreadExtendTest("fwh");
        fwh.setPriority(Thread.MAX_PRIORITY);
        fwh.start();
        try {
            fwh.join();//由于加入了join方法。主线程要等当前子线程结束后才会继续执行。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        new Thread(new ThreadExtendTest("fwh")).start();
//        new Thread(new ThreadExtendTest("wahaha")).start();
        ThreadExtendTest wahaha = new ThreadExtendTest("wahaha");
        wahaha.setPriority(Thread.MIN_PRIORITY);
        wahaha.start();
        System.out.println("主线程结束");
    }

}

class ThreadExtendTest extends Thread {
    private String name;

    public ThreadExtendTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
//        super.run();
        for (int i = 0; i < 3; i++) {
            System.out.println("ThreadExt_" + name + ":运行第" + i + "次");
        }
    }
}

class RunnableTest implements Runnable {

    private String name;

    public RunnableTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                if (name.equals("fwh")) {
//                    System.out.println(Thread.currentThread().getPriority());
//                    Thread.currentThread().setPriority(1);
//                    Thread.currentThread().join(1000L);
                    Thread.currentThread().sleep(1000L);
                }
//                Thread.currentThread().join(1000L);
                System.out.println("Runnbale_" + name + ":运行第" + i + "次");
//                System.out.println(Thread.currentThread().getName().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ThreadFacotryTest implements ThreadFactory{

    private static final AtomicInteger poolNumber = new AtomicInteger(1);//原子类，线程池编号
    private final ThreadGroup group;//线程组
    private final AtomicInteger threadNumber = new AtomicInteger(1);//线程数目
    private final String namePrefix;//为每个创建的线程添加的前缀

    ThreadFacotryTest() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();//取得线程组
        namePrefix = "pool-" +
                poolNumber.getAndIncrement() +
                "-thread-";
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);//真正创建线程的地方，设置了线程的线程组及线程名
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)//默认是正常优先级
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }

}



