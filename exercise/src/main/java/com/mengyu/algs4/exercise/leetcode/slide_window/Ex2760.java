package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex2760 {

    public static void main(String[] args) {
        int[] nums = {2, 5};
        System.out.println(new Ex2760().longestAlternatingSubarray(nums, 2));
    }

    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int leftPtr = 0, rightPtr = 0, len = nums.length;
        leftPtr = findLeftPtr(leftPtr, nums, threshold, len);
        if (leftPtr == -1) {
            return 0;
        }
        int cnt = 1;
        rightPtr = leftPtr + 1;
        while (rightPtr < len) {
            if (nums[rightPtr] <= threshold && nums[rightPtr - 1] % 2 != nums[rightPtr] % 2) {
                cnt = Math.max(cnt, rightPtr - leftPtr + 1);
                rightPtr++;
            } else {
                leftPtr = findLeftPtr(leftPtr + 1, nums, threshold, len);
                if (leftPtr == -1) {
                    break;
                }
                rightPtr = leftPtr + 1;
            }
        }
        if (rightPtr >= len) {
            cnt = Math.max(cnt, rightPtr - leftPtr);
        }
        return cnt;
    }

    private int findLeftPtr(int leftPtr, int[] nums, int threshold, int len) {
        while (leftPtr < len && (nums[leftPtr] % 2 != 0 || nums[leftPtr] > threshold)) {
            leftPtr++;
        }
        if (leftPtr >= len) {
            return -1;
        }
        return leftPtr;
    }
}
