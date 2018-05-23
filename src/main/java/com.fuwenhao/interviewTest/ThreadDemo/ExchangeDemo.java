package com.fuwenhao.interviewTest.ThreadDemo;

import org.apache.cxf.message.Exchange;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by fwh on 2018/5/23 下午12:45
 * Exchange用于两个线程交换数据；
 *      两个线程在同一个点（阻塞点）交换数据。
 *      校对工作。
 * 模拟情景：
 *     张三绑架小乔，要一手交钱一手交人；
 */
public class ExchangeDemo {
    public static void main(String[] args) {

        final Exchanger<String> exchanger = new Exchanger<String>();
        ExecutorService threadPool = Executors.newCachedThreadPool();//创建线程池
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                //张三团伙
                try {
                    String returnStr = exchanger.exchange("小乔");
                    System.out.println("绑架者用小乔交换："+returnStr);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                //张三团伙
                try {
                    String returnStr = exchanger.exchange("1000万");
                    System.out.println("大乔用1000万换回来:"+returnStr);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.shutdown();
    }


}
