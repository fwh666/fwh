package com.fuwenhao.interviewTest.leetCodeTest;

/**
 * create by fwh on 2018/7/25 下午4:35
 * @remark
 * leetCode网址是在线编程网站
 * @URL
 * https://leetcode-cn.com/problems/two-sum/description/
 * @description
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。

 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。

 示例:

 给定 nums = [2, 7, 11, 15], target = 9

 因为 nums[0] + nums[1] = 2 + 7 = 9
 所以返回 [0, 1]
 */
public class test1_Simple {
    public static void main(String[] args) {
        int target = 13;
        int[] nums ={2,7,11,15};
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j <nums.length ; j++) {
                if (nums[i]+nums[j]==target){
                    System.out.println("输出：【"+i+","+j+"】");
                    break;
                }
            }
        }

    }
}