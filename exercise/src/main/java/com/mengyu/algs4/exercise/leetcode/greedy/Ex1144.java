package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex1144 {

    public static void main(String[] args) {
        int[] nums = {9, 6, 1, 6, 2};
        System.out.println(new Ex1144().movesToMakeZigzag(nums));
    }

    public int movesToMakeZigzag(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int evenCount = 0, oddCount = 0, len = nums.length;
        if (nums[0] >= nums[1]) {
            evenCount += (nums[0] - nums[1] + 1);
        }
        for (int i = 1; i < len - 1; i++) {
            int min = Math.min(nums[i - 1], nums[i + 1]);
            if (nums[i] >= min) {
                if (i % 2 == 0) {
                    evenCount += (nums[i] - min + 1);
                } else {
                    oddCount += (nums[i] - min + 1);
                }
            }
        }
        if (nums[len - 1] >= nums[len - 2]) {
            if ((len - 1) % 2 == 0) {
                evenCount += (nums[len - 1] - nums[len - 2] + 1);
            } else {
                oddCount += (nums[len - 1] - nums[len - 2] + 1);
            }
        }
        return Math.min(evenCount, oddCount);
    }
}
