package com.fuwenhao.interviewTest.ListDemo;

import org.junit.Test;

/**
 * create by fwh on 2018/5/30 上午11:43
 * @description
 * 代码实现 arraylist原理。
 *      --内部原理：使用数组来实现。
 *      --动态大小。
 *      --使用索引来实现。
 */
public class ArrayListDemo {
    @Test
    public void ArrayListTest() throws Exception {
        MyArrayLIst lIst = new MyArrayLIst();
        lIst.add(1);
        lIst.add(2);
        lIst.add(3);
        lIst.add(4);
        lIst.add(8);
        lIst.add(7);
        //测试set方法
        lIst.set(2,"99");
        //测试删除元素
        lIst.remoreIndex(1);
        lIst.remoreIndex(1);//理论显示：1487
        for (int s = 0; s < lIst.size; s++) {
            System.out.print(lIst.get(s)+",");
        }
    }

}
