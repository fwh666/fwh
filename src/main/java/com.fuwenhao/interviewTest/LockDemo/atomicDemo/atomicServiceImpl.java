package com.fuwenhao.interviewTest.LockDemo.atomicDemo;

import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by fwh on 2018/5/24 上午10:36
 * 描述：
 *      AtomicInteger底层实现原子锁机制。unsafe  使用native。
 */
public class atomicServiceImpl implements OrderService {
    static AtomicInteger num = new AtomicInteger();

    @Override
    public String getOrder() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDDHHmmss");
        return simpleDateFormat.format(new Date()) + num.incrementAndGet();
    }
}
