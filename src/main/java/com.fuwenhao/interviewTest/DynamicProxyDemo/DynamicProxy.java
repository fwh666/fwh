package com.fuwenhao.interviewTest.DynamicProxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * create by fwh on 2018/5/26 下午10:42
 * //调用处理器
 */
public class DynamicProxy implements InvocationHandler {
    //添加对象
    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用之前的方法名："+method.getName());
        method.invoke(object,args);
        System.out.println("调用以后的方法名："+method.getName());

        return null;
    }
}
