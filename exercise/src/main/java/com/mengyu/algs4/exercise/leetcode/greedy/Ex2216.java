package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex2216 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 5};
        System.out.println(new Ex2216().minDeletion(nums));
    }

    public int minDeletion(int[] nums) {
        int deleteCnt = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int idx = i - deleteCnt;
            if (idx % 2 == 0 && i + 1 < n && nums[i] == nums[i + 1]) {
                deleteCnt++;
            }
        }
        deleteCnt += ((n - deleteCnt) % 2);
        return deleteCnt;
    }
}
