package com.fuwenhao.interviewTest.ThreadingMultiDemo.ThreadDemoScene;

/**
 * create by fwh on 2018/7/5 下午2:34
 */
public class ArmyRunnable implements Runnable{
    volatile Boolean keepRunning =true;
    @Override
    public void run() {
        while (keepRunning){
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName()+"发动攻击"+"【"+i+"】");
//                Thread.yield();
            }
        }
        keepRunning =false;
        System.out.println(Thread.currentThread().getName()+"----攻击结束----");

    }

    public static void main(String[] args) {
        Thread test = new Thread(new ArmyRunnable(),"fuwenhao");
        test.start();
    }
}
