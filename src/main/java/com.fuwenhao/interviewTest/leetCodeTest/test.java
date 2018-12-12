package com.fuwenhao.interviewTest.leetCodeTest;

/**
 * create by fwh on 2018/7/25 下午5:01
 */
public class test {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j <nums.length ; j++) {
                if (nums[i]+nums[j]==target){
                    System.out.println("输出：【"+i+","+j+"】");
                    int[] fuck ={i,j};
                    return fuck;
                }
            }
        }
        return null;
    }
}
