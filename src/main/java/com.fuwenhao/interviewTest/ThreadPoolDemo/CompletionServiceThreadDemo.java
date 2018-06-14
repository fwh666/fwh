package com.fuwenhao.interviewTest.ThreadPoolDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by FWH on 2018-06-14.
 */
public class CompletionServiceThreadDemo implements Callable<Integer> {
    private int num;

    public CompletionServiceThreadDemo(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        System.out.println("线程名:"+Thread.currentThread().getName());
        return  num;
    }

    /**
     * 使用ExecutorCompletionService方法
     * 结果的输出和线程的放入顺序无关系。每一个线程执行成功后，立刻就输出。
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
   /* public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("kaishi ");
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorCompletionService<Integer> integerExecutorCompletionService = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < 10; i++) {
            integerExecutorCompletionService.submit(new CompletionServiceThreadDemo(i));
        }
        for (int i = 0; i < 10; i++) {
            Integer integer = integerExecutorCompletionService.take().get();
            System.out.println("返回的结果值:"+integer);
        }
        System.out.println("jieshu");
    }*/

    /**Future
     *从输出结果可以看出,我们只能一个一个阻塞的取出。这中间肯定会浪费一定的时间在等待上
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main Thread begin:");
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Integer>> result = new ArrayList<Future<Integer>>();
        for (int i = 0;i<10;i++) {
            Future<Integer> submit = executor.submit(new CompletionServiceThreadDemo(i));
            result.add(submit);
        }
        executor.shutdown();
        for (int i = 0;i<10;i++) {//一个一个等待返回结果
            System.out.println("返回结果："+result.get(i).get());
        }
        System.out.println("main Thread end:");
    }
}
