package com.fuwenhao.interviewTest.DynamicProxyDemo;

/**
 * create by fwh on 2018/5/26 下午10:41
 */
public class DynameProxyServiceImpl implements  DynamicProxyService{
    @Override
    public void sayHello() {
        System.out.println("输出：hello");
    }
}
