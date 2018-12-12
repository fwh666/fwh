package com.fuwenhao.test;

/**
 * create by fwh on 2018/9/7 下午9:54
 */
public class TestSwitch {
    public static void main(String[] args) {
        int m =0;
        m++;
        System.out.println("diyici: "+m);
        switch (1){
            case 0 :m++;
            case 1 :m++;
            case 2 :m++;break;
        }
        System.out.println("最后值："+m);
    }
}
