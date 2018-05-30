package com.fuwenhao.interviewTest.baseTest.SortDemo;

import org.junit.Test;

/**
 * create by fwh on 2018/5/30 上午9:40
 * 插入排序
 *
 * @description 详情参见："插入排序原理步骤图"
 * 步骤：1。 i的位置小于i-1的位置
 * 2。i的位置小于j的位置
 * 3。将i的位置空出来，将前面数据后移，然后将i的位置插入前面
 */
public class InsertSortDemo {
    @Test
    public void test1() {
        int[] ints = {1, 3, 5, 8, 32, 6, 2};
        for (int i = 1; i < ints.length; i++) {//注意：需要从1开始，第二个下标
            //判断i的位置是否小于前一个位置的值
            if (ints[i] < ints[i - 1]) {
                //循环找到插入的位置
                for (int j = 0; j < i; j++) {
                    //然后再判断i的位置 小于插入的位置
                    if (ints[i] < ints[j]) {
                        int temp = ints[i];
                        //然后循环后移
                        for (int k = i - 1; k >= j; k--) {
                            ints[k + 1] = ints[k];//后移
                        }
                        ints[j] = temp;
                        break;
                    }
                }
            }
        }
        //输出结果
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + ",");
        }
    }
    //作业：把上面的三个循环，变成两个循环。--k循环和j循环可以合并。
    public void test2(){

    }
}
