package com.fuwenhao.test;

/**
 * Created by FWH on 2018-05-22.
 * 月份日期补零
 */
public class dateTest {
    public static void main(String[] args) {
        String year ="2018";
        String  month ="1";
        if (Integer.parseInt(month)<10)
            month="0"+month;
        String cjrq =year+month;
        System.out.println(cjrq);

    }
}
