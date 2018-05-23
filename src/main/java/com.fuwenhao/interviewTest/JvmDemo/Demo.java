package com.fuwenhao.interviewTest.JvmDemo;

import org.junit.Test;

/**
 * create by fwh on 2018/5/23 下午8:41
 * debug确认加载顺序，
 * 先加载静态变量--静态区代码块--非静态区父类---子类--构造函数。
 * 先静态后非静态。
 * 先父类后子类。
 */
public class Demo {
    public Demo(){
        System.out.println("Demo fuwenhao在此"+this.getClass().getClassLoader());
    }

//    public static void main(String[] args) {
//        System.out.println("ceshi");
//    }
}
