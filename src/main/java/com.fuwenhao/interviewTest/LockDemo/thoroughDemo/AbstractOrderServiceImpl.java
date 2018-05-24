package com.fuwenhao.interviewTest.LockDemo.thoroughDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create by fwh on 2018/5/24 上午10:02
 */
public abstract class AbstractOrderServiceImpl implements OrderService {

    static int num = 0;//此处代码为了实现获取不同的订单号，防止重复。
    //注意此处是静态同步的，会先执行这里。
     public static synchronized String getOrder2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDDHHmmss");
        return simpleDateFormat.format(new Date()) + num++;
    }

    @Override
    public String getOrder() {
        return getOrder2();
    }
}
