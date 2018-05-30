package com.fuwenhao.interviewTest.baseTest.SortDemo;

import org.junit.Test;

import java.util.Arrays;

/**
 * create by fwh on 2018/5/25 上午9:26
 * 冒泡排序
 */
public class bubbleSortDemo {
    @Test //此处冒泡有问题
    public void sortTest() {
        int[] array = {6, 5, 8, 3, 2, 1, 9};
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; --j) {
                if (array[i] - array[j] + 1 < 0) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }


    @Test //实现冒泡排序
    public void reviewSortTest() {
//        int[] array = {1, 2, 5, 7, 4, 9, 3};
        int[] array = {1, 2, 5, 7, 11, 9, 3};
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-1; j++) {
                if (array[j]> array[j+1]) {
                    int temp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }

            }
        }
        System.out.println(Arrays.toString(array));
    }


    //=================================================
    @Test
    public void bubbleSortTest() {
        bubbleSortDemo bubble = new bubbleSortDemo();
        bubble.bubbleSort();
    }

    int[] a = {6, 5, 8, 3, 2, 1, 9};

    public void bubbleSort() {
        for (int i = 0; i < a.length; i++) {
            // 算法设计:
            // 1.初始化:a[0,i]即a[0]有序
            // 2.不变式:a[0,i]有序==>加入a[i+1]即a[j]后,保持a[0,i+1]有序
            // -->实现:通过依次对a[j]<a[j-1]-->a[i,0]比较判断,然后交换位置确保有序
            // 3.终止:a[0,a.length]有序

            // 因为a[0,i]有序,可以将a[j] < a[j - 1]放入for内部,等同于while的条件
            for (int j = i + 1; j > 0 && a[j] < a[j - 1]; j--) {
                int x = a[j];
                System.out.println("x=a[" + j + "]" + "=" + x);
                a[j] = a[j - 1];
                a[j - 1] = x;
            }
            show(a);
        }
    }

    public void show(int a[]) {
        StringBuffer arr = new StringBuffer("[");
        for (int i = 0; i < a.length; i++) {
            arr.append(a[i] + ",");
        }
        arr.deleteCharAt(arr.lastIndexOf(","));
        arr.append("]");
        System.out.println(arr.toString());
    }





   /* @Test //实现层操作
    public <T extends Comparable<T>> void sort(T[] list) {
        boolean swapped = true;
        for(int i = 1; i < list.length && swapped;i++) {
            swapped= false;
            for(int j = 0; j < list.length - i; j++) {
                if(list[j].compareTo(list[j+ 1]) > 0 ) {
                    T temp = list[j];
                    list[j]= list[j + 1];
                    list[j+ 1] = temp;
                    swapped= true;
                }
            }
        }
    }*/

}
