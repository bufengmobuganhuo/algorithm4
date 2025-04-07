package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex209 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(new Ex209().minSubArrayLen(11, nums));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0, n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0; r < n; ) {
            while (r < n && sum < target) {
                sum += nums[r];
                r++;
            }
            if (sum >= target) {
                ans = Math.min(ans, r - l);
            }
            while (l < r && sum >= target) {
                sum -= nums[l];
                l++;
                if (sum >= target) {
                    ans = Math.min(ans, r - l);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
