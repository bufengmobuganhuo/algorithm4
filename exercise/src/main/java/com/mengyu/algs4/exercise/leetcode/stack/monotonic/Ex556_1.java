package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

/**
 * @author yuzhang
 * @date 2021/3/19 上午11:31
 * TODO
 */
public class Ex556_1 {
    public static void main(String[] args) {
        Ex556_1 ex556_1 = new Ex556_1();
        System.out.println(ex556_1.nextGreaterElement(13));
    }
    public int nextGreaterElement(int n) {
        char[] nums = ("" + n).toCharArray();
        int len = nums.length, i = len - 2;
        // 找到第一个不是降序的
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = len - 1;
        while (j >= 0 && nums[j] <= nums[i]) {
            j--;
        }
        exch(nums, i, j);
        reverse(nums, i + 1);
        String str = new String(nums);
        long num = Long.parseLong(str);
        if (num > 2147483647){
            return -1;
        }
        return (int) num;
    }

    private void reverse(char[] nums, int startIdx) {
        int leftPtr = startIdx, rightPtr = nums.length - 1;
        while (leftPtr < rightPtr) {
            exch(nums, leftPtr, rightPtr);
            leftPtr++;
            rightPtr--;
        }
    }

    private void exch(char[] nums, int i, int j) {
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
