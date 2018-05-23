package com.fuwenhao.interviewTest.ThreadDemo;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * create by fwh on 2018/5/23 上午11:25
 * url:http://ifeve.com/tag/semaphore/
 * 用来控制同时访问特定资源的线程数量
 * semaphore--翻译：信号量
 * <p>
 * 模拟情景：
 *      20个用户，只有两个购票窗口，当一个窗口购票结束后，后面的人继续购票。
 */
public class SemaphoreDemo {
    /**
     * 执行任务类。获取信号量+释放信号量；
     * 实现Runable接口必须要重写run方法；
     */
    class SemaphoreRunable implements Runnable {
        private Semaphore semaphore;//信号量
        private int user;//用户

        public SemaphoreRunable(Semaphore semaphore, int user) {
            this.semaphore = semaphore;
            this.user = user;
        }

        public void run() {
            try {
                semaphore.acquire();//获取信号量
                System.out.println(user + "用户进入窗口开始买票");
                Thread.sleep((long) (10000 * Math.random()));
                System.out.println(user + "用户购票成功，离开窗口");
                semaphore.release();//完成后，要释放信号量
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void execute() {
        final Semaphore semaphore = new Semaphore(2);//定义两个窗口
        //创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();//缓存类型线程池
     /* ExecutorService executorService = Executors.newFixedThreadPool(2);//指定工作量的线程池
        Executors.newScheduledThreadPool(5000);//定时任务的线程池
        Executors.newSingleThreadExecutor();//单例线程池*/
        for (int i = 0; i < 20; i++) {
            threadPool.execute(new SemaphoreRunable(semaphore, i));//用户们去购票
        }
        threadPool.shutdown();//关闭线程池结束后释放资源；
    }

    public static void main(String[] args) {
        SemaphoreDemo sd = new SemaphoreDemo();
        sd.execute();
    }
}
