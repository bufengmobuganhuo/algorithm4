package com.mengyu.algs4.exercise.leetcode.bit;

/**
 * @author yu zhang
 */
public class Ex2917 {
    public int findKOr(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 31; i++) {
            int cnt = 0;
            for (int num : nums) {
                if (((num >> i) & 1) == 1) {
                    cnt++;
                }
            }
            if (cnt >= k) {
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
