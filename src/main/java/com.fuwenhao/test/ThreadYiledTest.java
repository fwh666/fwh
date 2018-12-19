package com.fuwenhao.test;

/**
 * create by fwh on 2018/12/12 下午4:28
 *
 * 举个例子：一帮朋友在排队上公交车，轮到Yield的时候，他突然说：我不想先上去了，咱们大家来竞赛上公交车。
 * 然后所有人就一块冲向公交车， 有可能是其他人先上车了，也有可能是Yield先上车了。
 */
public class ThreadYiledTest implements Runnable{
    private String name;
    private ThreadYiledTest(String name){
        this.name=name;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name+"_"+i);
            if (i==4){
                Thread.yield();//用了yield方法后，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            }
        }
    }

    public static void main(String[] args) {
        ThreadYiledTest t1 = new ThreadYiledTest("t1");
        ThreadYiledTest t2 = new ThreadYiledTest("t2");
        ThreadYiledTest t3 = new ThreadYiledTest("t3");
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();

    }
}
