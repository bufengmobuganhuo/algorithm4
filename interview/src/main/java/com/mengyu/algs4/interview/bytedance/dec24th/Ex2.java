package com.mengyu.algs4.interview.bytedance.dec24th;

/**
 * @author yuzhang
 * @date 2020/12/24 上午9:40
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.findMin(nums));
    }
    public int findMin(int[] nums) {
        if (nums == null | nums.length == 0) {
            return -1;
        }
        int leftPtr = 0, rightPtr = nums.length - 1;
        while (leftPtr < rightPtr) {
            if (nums[leftPtr] > nums[leftPtr + 1]) {
                return nums[leftPtr + 1];
            }
            if (nums[rightPtr] < nums[rightPtr - 1]) {
                return nums[rightPtr];
            }
            leftPtr++;
            rightPtr--;
        }
        return nums[0];
    }
}
