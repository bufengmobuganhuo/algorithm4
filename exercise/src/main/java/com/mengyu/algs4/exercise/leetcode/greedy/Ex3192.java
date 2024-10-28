package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex3192 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 1, 0, 1};
        System.out.println(new Ex3192().minOperations(nums));
    }

    public int minOperations(int[] nums) {
        int cnt = 2;
        for (int num : nums) {
            if ((cnt % 2 == 0) && num == 0) {
                cnt++;
            } else if ((cnt % 2 == 1) && num == 1) {
                cnt++;
            }
        }
        return cnt - 2;
    }
}
