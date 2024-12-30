package com.mengyu.algs4.exercise.leetcode.hash;

/**
 * @author yu zhang
 */
public class Ex3159 {
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        int[] map = new int[nums.length];
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) {
                map[idx] = i;
                idx++;
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (queries[i] > idx) {
                ans[i] = -1;
            } else {
                ans[i] = map[queries[i - 1]];
            }
        }
        return ans;
    }
}
