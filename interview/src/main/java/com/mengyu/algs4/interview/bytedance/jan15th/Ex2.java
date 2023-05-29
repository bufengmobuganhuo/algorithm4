package com.mengyu.algs4.interview.bytedance.jan15th;

/**
 * @author yuzhang
 * @date 2021/1/15 上午9:40
 * TODO
 */
public class Ex2 {
    public int findMin(int[] nums) {
        if (nums==null||nums.length==0){
            return -1;
        }
        int leftPtr = 0, rightPtr = nums.length - 1;
        while (leftPtr < rightPtr){
            if (leftPtr < nums.length - 1 && nums[leftPtr] > nums[leftPtr + 1]){
                return nums[leftPtr + 1];
            }
            if (rightPtr > 0 && nums[rightPtr] < nums[rightPtr - 1]){
                return nums[rightPtr];
            }
            leftPtr++;
            rightPtr--;
        }
        return nums[0];
    }
}
