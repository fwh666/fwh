package com.fuwenhao.interviewTest.ThreadingMultiDemo.ThreadDemoScene;

/**
 * create by fwh on 2018/7/5 下午3:36
 */
public class Stage implements Runnable{
    @Override
    public void run() {
        System.out.println("请开始你的表演");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArmyRunnable test1 = new ArmyRunnable();
        ArmyRunnable test2 = new ArmyRunnable();
        Thread testOne = new Thread(test1,"革命军fwh");
        Thread testTwo = new Thread(test2,"改革派whh");
        testOne.start();
        testTwo.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关键人物出现
        System.out.println("关键人物出现---程咬金--------");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        KeyPersonRunable keyPersonRunable = new KeyPersonRunable();
        Thread keyThread = new Thread(keyPersonRunable,"程咬金");
        test1.keepRunning=false;
        test2.keepRunning=false;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        keyThread.start();
        try {
            keyThread.join();//当前线程结束后，才会继续主线程。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("-----战争结束----");
    }

    public static void main(String[] args) {
        Stage s = new Stage();
        s.run();
    }
}
