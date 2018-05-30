package com.fuwenhao.interviewTest.ListDemo;

import org.junit.Test;

/**
 * create by fwh on 2018/5/30 上午11:43
 * @description
 * linkedlist和arraylist性能比较：
 *
 * 添加：linkedlist
 * 删除：linkedlist
 * 获取和位置：arraylist
 *
 * 注意：
 *  删除的时候：arrylist是从后往前删除，越往后删除性能越高。
 *           linkedlist前后删除效率都高，越往中间走 效率越低。
 */
public class LinkedLIstDemo {
    @Test
    public void LinkedLIstTest() throws Exception {
        MylinkedList lIst = new MylinkedList();
        lIst.add(1);
        lIst.add(2);
        lIst.add(3);
        lIst.add(4);
        lIst.add(8);
        lIst.add(7);
        //测试set方法
        lIst.set(2,"99");
        //测试删除元素
        lIst.removeIndex(1);
        lIst.removeIndex(1);//理论显示：1487
        for (int s = 0; s < lIst.size; s++) {
            System.out.print(lIst.get(s)+",");
        }
    }

}
