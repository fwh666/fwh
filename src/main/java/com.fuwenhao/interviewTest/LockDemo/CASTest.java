package com.fuwenhao.interviewTest.LockDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by fwh on 2018/12/19 下午2:45
 * 乐观锁（CAS）代码实现
 * 对于ABA问题，使用计数来区分。
 *
 * 从Java1.5开始JDK的并发包里提供了一些类来支持原子操作，
 * 如AtomicBoolean（用原子方式更新的 boolean 值），AtomicInteger（用原子方式更新的 int 值），
 * AtomicLong（用原子方式更新的 long 值），
 * 这些原子包装类还提供了有用的工具方法，比如以原子的方式将当前值自增1和自减1
 */
public class CASTest {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {

        final CASTest cas = new CASTest();

        List<Thread> ts = new ArrayList<Thread>(600);

        long start = System.currentTimeMillis();

        for (int j = 0; j < 100; j++) {

            Thread t = new Thread(new Runnable() {

                public void run() {

                    for (int i = 0; i < 10000; i++) {

                        cas.count();

                        cas.safeCount();

                    }

                }

            });

            ts.add(t);

        }

        for (Thread t : ts) {

            t.start();

        }

        // 等待所有线程执行完成

        for (Thread t : ts) {

            try {

                t.join();

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

        System.out.println(cas.i);

        System.out.println(cas.atomicI.get());

        System.out.println(System.currentTimeMillis() - start);

    }

    /**
     * 使用CAS实现线程安全计数器
     */

    private void safeCount() {

        for (; ; ) {

            int i = atomicI.get();

            boolean suc = atomicI.compareAndSet(i, ++i);

            if (suc) {

                break;

            }

        }

    }

    /**
     * 非线程安全计数器
     */

    private void count() {

        i++;

    }

}


