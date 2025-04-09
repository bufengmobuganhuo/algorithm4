package com.mengyu.algs4.interview.ant;

/**
 * @date 2025/3/3 21:32
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] nums = {5, 1, 9, 14, 10, 8};
        System.out.println(findAbsentMin(nums));

        int[] nums1 = {1};
        System.out.println(findAbsentMin(nums1));
    }

    private static int findAbsentMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int absentMin = 1;
        for (int num : nums) {
            if (absentMin >= num) {
                absentMin = num + 1;
            }
        }
        return absentMin;
    }
}
