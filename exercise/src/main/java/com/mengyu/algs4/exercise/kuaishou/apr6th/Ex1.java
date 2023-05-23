package com.mengyu.algs4.exercise.kuaishou.apr6th;

/**
 * @author yuzhang
 * @date 2021/4/6 上午8:28
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.searchInsert(nums,2));
    }
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int leftPtr = 0, rightPtr = nums.length - 1;
        while (leftPtr <= rightPtr){
            int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
            if (target == nums[midPtr]){
                return midPtr;
            }else if (target < nums[midPtr]){
                rightPtr = midPtr - 1;
            }else {
                leftPtr = midPtr + 1;
            }
        }
        return nums[leftPtr] > target ? leftPtr - 1 : leftPtr;
    }
}
