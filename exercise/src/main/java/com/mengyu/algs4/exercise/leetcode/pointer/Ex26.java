package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex26 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(new Ex26().removeDuplicates(nums));
    }

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int l = 0;
        for (int r = 1; r < n; ) {
            while (r < n && nums[l] == nums[r]) {
                r++;
            }
            if (r == n) {
                break;
            }
            l++;
            int tmp = nums[r];
            nums[r] = nums[l];
            nums[l] = tmp;
            r++;
        }
        return l + 1;
    }
}
