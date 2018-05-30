package com.fuwenhao.interviewTest.baseTest.SortDemo;

import org.junit.Test;

/**
 * create by fwh on 2018/5/29 下午10:08
 * @description
 * 情景描述：
 * 由于单个数据类型来是显示很长的数字是无法完整显示的，
 * 需要用数据形式来解决表达。
 * 例如：3.0414093201713376E64
 * 0000000000000008881784197001252323389053344726562500000000000000000000000000000000000000000000000000
 *
 */
public class BigNumDemo {
    @Test
    public void test1() {
        /*
        /实现5的阶乘; 5! 即：1*2*3*4*5...n
         */
        int n = 5;
        int num1 = 1;
        for (int i = 1; i <= n; i++) {
            num1 *= i;
        }
        System.out.println(num1);
    }

    /**
     * 如果阶乘数超过来int的范围？
     *  --使用double  会显示科学计数法
     */
    @Test
    public void test2(){
        int n = 50;
        double num1 = 1;
        for (int i = 1; i <= n; i++) {
            num1 *= i;
        }
        System.out.println(num1);

    }
    /**
     * 大数字运算，使用数组形式。--参见图片"大数字数组运算原理"
     *  372*18=6696
     *      方法步骤：
     *          1。计算每一个结果位置
     *          2。进位和留位 （过十进位）
     */
    @Test
    public void test3(){
        //声明数组长度
        int[] ints = new int[6];
        //将数值放入数组中
        ints[ints.length-1]=2;//个位数
        ints[ints.length-2]=7;//十位数
        ints[ints.length-3]=3;//百位数
        int num = 18;
        for (int i = 0; i < ints.length; i++) {
            ints[i] =num*ints[i];        //计算结果位置
        }
        //进位和留位--从后往前循环
        for (int i = ints.length-1; i>0; i--) {//注意：要循环到索引为1的位置上。
            ints[i-1] += ints[i]/10;//进上一位--十位数
            ints[i] = ints[i]%10;//留当前位--个位数
        }
        //得到结果
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ints.length; i++) {
            //作业一：去掉前面显示的零。 006696
            String s = String.valueOf(ints[i]);
            sb.append(s);
        }
        System.out.println("未去掉零之前："+sb);
        String str = sb.toString();
        String newStr = str.replaceFirst("^0*", "");
        System.out.println("去掉零之后："+newStr);

    }
    /**
     * 方法抽取封装：计算更大的数值
     * @param ints--数组长度；num--阶乘数
     */
    static int[] demo(int[] ints,int num){
        for (int i = 0; i < ints.length; i++) {
            ints[i] =num*ints[i];        //计算结果位置
        }
        //进位和留位--从后往前循环
        for (int i = ints.length-1; i>0; i--) {//注意：要循环到索引为1的位置上。
            ints[i-1] += ints[i]/10;//进上一位--十位数
            ints[i] = ints[i]%10;//留当前位--个位数
        }
        /*//得到结果
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ints.length; i++) {
            //作业一：去掉前面显示的零。 006696
            String s = String.valueOf(ints[i]);
            sb.append(s);

        }
        System.out.println("未去掉零之前："+sb);
        String str = sb.toString();
        String newStr = str.replaceFirst("^0*", "");
        System.out.println("去掉零之后："+newStr);*/
        return ints;
    }

    /**
     * 得到阶乘大数字
     */
    @Test
    public void test4(){
        int n = 50;     //阶乘数
        int[] ints = new int[100];//承载数值的长度
        ints[ints.length-1]=1;
        for (int i = 1; i <= n; i++) {
            ints= demo(ints, n);
        }
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]);
        }
    }
}

