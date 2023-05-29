package com.mengyu.algs4.interview.kuaishou.apr5th;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/4/5 上午10:50
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,2,3,5};
        Ex2 ex2 = new Ex2();
        System.out.println(Arrays.toString(ex2.singleNumber(nums)));
    }
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int count = 0;
        int[] res = new int[2];
        sort(nums, 0, nums.length - 1);
        for (int i = 1; i < nums.length; i += 2) {
            if (nums[i - 1] != nums[i]) {
                res[count++] = nums[i-1];
                i--;
            }
        }
        if (count==1){
            res[1] = nums[nums.length-1];
        }
        return res;
    }

    private void sort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int partitionIdx = partition(nums, start, end);
        sort(nums, start, partitionIdx - 1);
        sort(nums, partitionIdx + 1, end);
    }

    private int partition(int[] nums, int start, int end) {
        int partitionEle = nums[start], leftPtr = start, rightPtr = end + 1;
        while (true) {
            while (nums[++leftPtr] <= partitionEle) {
                if (leftPtr == end) {
                    break;
                }
            }
            while (nums[--rightPtr] > partitionEle) {
                if (rightPtr == start) {
                    break;
                }
            }
            if (rightPtr <= leftPtr) {
                break;
            }
            exch(nums, leftPtr, rightPtr);
        }
        exch(nums, rightPtr, start);
        return rightPtr;
    }

    private void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
