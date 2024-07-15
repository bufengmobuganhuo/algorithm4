package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex3101 {

    public static void main(String[] args) {
        System.out.println(new Ex3101().countAlternatingSubarrays(new int[]{0, 1, 1, 1}));
    }

    public long countAlternatingSubarrays(int[] nums) {
        long ans = 0;
        int n = nums.length;
        int leftPtr = 0, rightPtr = 1;
        int last = nums[leftPtr];
        while (rightPtr < n) {
            if (last != nums[rightPtr]) {
                last = nums[rightPtr];
                rightPtr++;
            } else {
                long len = rightPtr - leftPtr;
                ans = ans + (len * (len + 1) / 2);
                leftPtr = rightPtr;
                rightPtr++;
                last = nums[leftPtr];
            }
        }
        long len = rightPtr - leftPtr;
        return ans + (len * (len + 1) / 2);
    }
}
