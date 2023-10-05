package com.mengyu.algs4.exercise.leetcode.rank.year2023.october1;

/**
 * @date 2023/10/1 09:14
 * TODO
 */
public class Ex2 {

    public static void main(String[] args) {
        int[] nums = {1,10,3,4,19};
        System.out.println(new Ex2().maximumTripletValue(nums));
    }

    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        // nums[i:]的最大值
        int[] sufMax = new int[n];
        sufMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i > -1; i--) {
            sufMax[i] = Math.max(nums[i], sufMax[i + 1]);
        }
        long ans = 0;
        // nums[:i]的最大值
        int preMax = nums[0];
        for (int j = 1; j < n - 1; j++) {
            ans = Math.max(ans, (long) (preMax - nums[j]) * sufMax[j + 1]);
            preMax = Math.max(preMax, nums[j]);
        }
        return ans;
    }
}
