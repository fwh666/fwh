package com.fuwenhao.interviewTest.baseTest;

import org.junit.Test;

/**
 * create by fwh on 2018/6/8 下午8:01
 * 面试题：
 *  一个整数，大于0，不用循环和本地变量，按照n，2n，4n，8n的顺序递增，当值大于5000时，把值按照指定顺序输出来
 *  -- 1237，

 2474，

 4948，

 9896，

 9896，

 4948，

 2474，

 1237，


 *  --- https://blog.csdn.net/maleizzia/article/details/8823230
 */
public class calculateTest {
    //值小于5000 递增
    public void big(int n){
        System.out.println("big:"+n);
        if (n<5000){
            big(n*2);
        }else{
            //值大于5000 递减
            little(n);
        }
    }
    //小于5000
    public void little (int n){
        System.out.println("little:"+n);
        if(n>5000){
            little(n/2);
        }
    }
    //测试入口
    @Test
    public void test(){
        int n =1000;
        big(n);
    }

    /**
     * java的递归原理。
     * -- https://blog.csdn.net/lfsf802/article/details/7696405
     */
    @Test
    public void test4(){
        count(1000);
    }
    public void count(int n){
        System.out.println(n);
        if (n<=5000) {
            count(n*2);
        }
        System.out.println(n);
    }

}
