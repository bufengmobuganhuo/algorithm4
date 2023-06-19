package com.mengyu.algs4.exercise.leetcode.rank.year2023.february12;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2023/2/12 10:03
 * TODO
 */
public class Ex2 {

    public static void main(String[] args) {
        int[] nums = {0,0,0,0,0,0};
        System.out.println(new Ex2().countFairPairs(nums, 0, 0));
    }

    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int len = nums.length;
        long ans = 0;

        for (int i = 0; i < len - 1; i++) {
            int num = nums[i];
            int leftPtr = i + 1, rightPtr = len - 1;
            if (num + nums[rightPtr] < lower || num + nums[leftPtr] > upper) {
                continue;
            }
            while (leftPtr < len && lower > num + nums[leftPtr]) {
                leftPtr++;
            }
            while (rightPtr > i && num + nums[rightPtr] > upper) {
                rightPtr--;
            }
            if (leftPtr > rightPtr) {
                continue;
            }
            ans += (rightPtr - leftPtr + 1);
        }
        return ans;
    }

    private int floor(int[] nums, int start, int end, int target) {
        int leftPtr = start - 1, rightPtr = end;
        while (leftPtr < rightPtr) {
            int mid = leftPtr + (rightPtr - leftPtr + 1) / 2;
            if (nums[mid] >= target) {
                rightPtr = mid - 1;
            } else {
                leftPtr = mid;
            }
        }
        if (leftPtr + 1 <= end && nums[leftPtr + 1] == target) {
            return leftPtr + 1;
        }
        return leftPtr;
    }

    private int ceil(int[] nums, int start, int end, int target) {
        int leftPtr = start, rightPtr = end + 1;
        while (leftPtr < rightPtr) {
            int mid = leftPtr + (rightPtr - leftPtr) / 2;
            if (nums[mid] <= target) {
                leftPtr = mid + 1;
            } else {
                rightPtr = mid;
            }
        }
        if (rightPtr - 1 >= start && nums[rightPtr - 1] == target) {
            return rightPtr - 1;
        }
        return rightPtr;
    }
}
