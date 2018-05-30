package com.fuwenhao.interviewTest.baseTest.SortDemo;

import org.junit.Test;

/**
 * create by fwh on 2018/5/30 上午11:16
 * @description
 * 1。必须是有序数组；
 * 2。从中间查找。
 * 3。三种情况：比中间小；就是中间；大于中间值；
 * 4。如果末坐标小于是起始坐标，则不存在。结束。
 */
public class BinarySearchDemo {
    @Test
    public void test(){
        int[] ints ={1, 3, 5, 8, 10, 16, 20};
        int num = 88;//要查找的数。
        int s =0;//起始位置
        int e =ints.length-1;//结束位置
        int m =0;//中间位置
        //注意：此处使用while循环，而不是for。因为不知道要循环多少次。
        while (s<=e){
            m=(s+e)/2;
            if(ints[m]==num){
                System.out.println("下标位置为："+m);
                return;//此处直接结束返回，而不是跳出循环。
            }else if (ints[m]<num){//在右边
                s=m+1;
            }else {//在左边
                e=m-1;
            }
        }
        System.out.println("该数下标不存在");
    }
}
