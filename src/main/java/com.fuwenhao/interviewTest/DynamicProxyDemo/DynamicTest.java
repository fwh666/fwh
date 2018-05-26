package com.fuwenhao.interviewTest.DynamicProxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * create by fwh on 2018/5/26 下午10:45
 * 其实就是代理类为被代理类预处理消息、过滤消息并在此之后将消息转发给被代理类，之后还能进行消息的后置处理。代理类和被代理类通常会存在关联关系(即上面提到的持有的被带离对象的引用)，代理类本身不实现服务，而是通过调用被代理类中的方法来提供服务。
 *
 * 动态代理的个人理解：
 *      代理本身不实现方法，而是当请求过来，通过代理去调用接口方法来实现。替代来程序直接去调用接口的方法。
 *
 */
public class DynamicTest {
    public static void main(String[] args) {
        //new一个实现类
        DynameProxyServiceImpl dynamicProxyService = new DynameProxyServiceImpl();
        //用动态代理类加载实现类
        InvocationHandler dynamicProxy = new DynamicProxy(dynamicProxyService);

        //创建动态代理
        DynamicProxyService ds = (DynamicProxyService)Proxy.newProxyInstance
                (dynamicProxy.getClass().getClassLoader(),
                        dynamicProxyService.getClass().getInterfaces(),
                        dynamicProxy);
        ds.sayHello();

    }
}
