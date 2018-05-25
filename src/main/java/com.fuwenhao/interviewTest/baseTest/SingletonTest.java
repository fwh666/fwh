package com.fuwenhao.interviewTest.baseTest;

import com.jd.open.api.sdk.response.digtal.Sing;
import org.junit.Test;

import static org.apache.ibatis.executor.ErrorContext.instance;

/**
 * create by fwh on 2018/5/25 上午8:28
 * 单例模式
 */
public class SingletonTest {

    //饿汉式
    static class SingletonHungry {
        private static SingletonHungry instance = new SingletonHungry();

        private SingletonHungry() {
        }

        public static SingletonHungry getInstance() {
            return instance;
        }
    }

    //懒汉式
    static class SingletonLazy {
        private static SingletonLazy instacneL;

        private SingletonLazy() {
        }

        public static SingletonLazy getInstacneL() {
            if (instacneL == null)
                instacneL = new SingletonLazy();
            return instacneL;
        }
    }
}
