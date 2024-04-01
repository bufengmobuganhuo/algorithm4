package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yu zhang
 */
public class Ex2908 {

    public static void main(String[] args) {
        System.out.println(new Ex2908().minimumSum(new int[]{5,4,8,7,10,2}));
    }

    public int minimumSum(int[] nums) {
        int n = nums.length;
        int[] mins = new int[n];
        int min = 100;
        for (int i = n - 1; i >= 0; i--) {
            mins[i] = min;
            min = Math.min(nums[i], min);
        }
        int sum = Integer.MAX_VALUE;
        min = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > min && nums[i] > mins[i]) {
                sum = Math.min(sum, min + nums[i] + mins[i]);
            }
            min = Math.min(nums[i], min);
        }
        return sum == Integer.MAX_VALUE ? -1 : sum;
    }
}
