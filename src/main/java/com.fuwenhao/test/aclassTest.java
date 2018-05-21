package com.fuwenhao.test;

/**
 * 面试题
 * 正确的输出顺序
 */
public class aclassTest {
    public aclassTest(){
        System.out.println("A class");
    }
    public void doPrint(){
        System.out.println("printer in A class");
    }
}
class Bclass extends aclassTest{
    public Bclass(){
        System.out.println("B class");
    }
    public void doPrint(){
        System.out.println("printer in B class");
    }
}
class Cclass extends Bclass {
    public Cclass(){
        System.out.println("C class");
    }
    public static void main(String[] args){
        aclassTest c=new Cclass();
        c.doPrint();
    }
}

