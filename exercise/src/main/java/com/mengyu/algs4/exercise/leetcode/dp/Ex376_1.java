package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex376_1 {
    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        int[] down = new int[len];
        int[] up = new int[len];
        down[0] = 1;
        up[0] = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                down[i] = down[ i -1];
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
            }else if (nums[i] < nums[i - 1]){
                down[i] = Math.max(down[i - 1], up[i - 1] + 1);
                up[i] = up[i - 1];
            }else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }
        return Math.max(down[len - 1], up[len - 1]);
    }
}
