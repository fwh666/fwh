package com.fuwenhao.interviewTest.LockDemo.zookeeperLockDemo;

import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.AbstractOrderServiceImpl;
import com.fuwenhao.interviewTest.LockDemo.thoroughDemo.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create by fwh on 2018/5/24 上午9:41
 */
public class OrderNoLockServiceImpl implements OrderService {
    /*
    /如果注释掉一下代码，会走父类的方法；不注释会重写父类方法。
     */
    static int num = 0;//此处代码为了实现获取不同的订单号，防止重复。

    @Override
    public String getOrder() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDDHHmmss");
        return simpleDateFormat.format(new Date()) + num++;
    }
}
