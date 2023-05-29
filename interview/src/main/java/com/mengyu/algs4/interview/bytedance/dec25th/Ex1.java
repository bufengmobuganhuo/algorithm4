package com.mengyu.algs4.interview.bytedance.dec25th;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/12/25 上午8:54
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] nums = {-4,-1,0,3,10};
        Ex1 ex1 = new Ex1();
        System.out.println(Arrays.toString(ex1.sortedSquares(nums)));
    }
    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int[] res = new int[nums.length];
        if (nums[0] >= 0) {
            for (int i = 0; i < nums.length; i++) {
                res[i] = nums[i] * nums[i];
            }
            return res;
        }
        int mid = 0;
        while (mid < nums.length && nums[mid] < 0) {
            mid++;
        }
        int left = mid - 1, right = mid;
        for (int i = 0; i < nums.length; i++) {
            if (left < 0) {
                res[i] = nums[right] * nums[right];
                right++;
            } else if (right >= nums.length) {
                res[i] = nums[left] * nums[left];
                left--;
            } else if (Math.abs(nums[right]) > Math.abs(nums[left])) {
                res[i] = nums[left] * nums[left];
                left--;
            } else {
                res[i] = nums[right] * nums[right];
                right++;
            }
        }
        return res;
    }
}
