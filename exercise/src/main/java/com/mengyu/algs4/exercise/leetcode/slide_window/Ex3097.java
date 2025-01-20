package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex3097 {

    public static void main(String[] args) {
        int[] nums = {1,2,32,21};
        System.out.println(new Ex3097().minimumSubarrayLength(nums, 55));
    }

    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int[] bit = new int[30];
        int ans = Integer.MAX_VALUE;
        for (int left = 0, right = 0; right < n; right++) {
            for (int i = 0; i < bit.length; i++) {
                bit[i] += ((nums[right] >> i) & 1);
            }

            while (left <= right && cal(bit) >= k) {
                ans = Math.min(ans, right - left + 1);
                for (int i = 0; i < bit.length; i++) {
                    bit[i] -= ((nums[left] >> i) & 1);
                }
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int cal(int[] bit) {
        int num = 0;
        for (int i = 0; i < bit.length; i++) {
            if (bit[i] > 0) {
                num |= (1 << i);
            }
        }
        return num;
    }
}
