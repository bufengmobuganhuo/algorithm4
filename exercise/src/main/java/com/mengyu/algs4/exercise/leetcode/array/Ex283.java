package com.mengyu.algs4.exercise.leetcode.array;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/12/9 上午9:55
 * TODO
 */
public class Ex283 {
    public static void main(String[] args) {
        Ex283 ex283 = new Ex283();
        int[] nums = {0, 1, 1, 0};
        ex283.moveZeroes(nums);
    }

    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
            }
            right++;
        }
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int leftPtr = 0, rightPtr = 0;
        while (leftPtr < nums.length) {
            // 找到0的范围
            while (leftPtr < nums.length && nums[leftPtr] != 0) {
                leftPtr++;
            }
            rightPtr = leftPtr;
            while (rightPtr < nums.length && nums[rightPtr] == 0) {
                rightPtr++;
            }
            if (rightPtr >= nums.length) {
                break;
            }
            // 找到非0的范围
            int nLeftPtr = rightPtr, nRightPtr = rightPtr;
            while (nRightPtr < nums.length && nums[nRightPtr] != 0) {
                nRightPtr++;
            }
            exch(nums, leftPtr, rightPtr, nLeftPtr, nRightPtr);
            leftPtr += (nRightPtr - nLeftPtr);
        }
        System.out.println(Arrays.toString(nums));
    }

    private void exch(int[] nums, int left, int right, int nLeft, int nRight) {
        while (nLeft < nRight) {
            int tmp = nums[left];
            nums[left] = nums[nLeft];
            nums[nLeft] = tmp;
            left++;
            nLeft++;
        }
    }
}
