package com.mengyu.algs4.exercise.leetcode.rank.year2021.junly4;

/**
 * @author yuzhang
 * @date 2021/7/4 上午10:13
 * TODO
 */
public class Ex1 {
    public int[] buildArray(int[] nums) {
        if(nums == null || nums.length == 0){
            return new int[0];
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }
}
