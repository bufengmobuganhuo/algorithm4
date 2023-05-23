package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex334 {

    public static void main(String[] args) {
        int[] nums = {1,2,1,2,1,2,1,2,1,2};
        System.out.println(new Ex334().increasingTriplet(nums));
    }

    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > second) {
                return true;
            } else if (nums[i] > first){
                second = nums[i];
            } else {
                first = nums[i];
            }
        }
        return false;
    }
}
