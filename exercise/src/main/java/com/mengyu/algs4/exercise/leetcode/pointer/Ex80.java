package com.mengyu.algs4.exercise.leetcode.pointer;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex80 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 2, 2, 4};
        System.out.println(new Ex80().removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) {
            return nums.length;
        }
        int n = nums.length;
        // slow表示下一个待填入的位置，fast表示当前检查到的位置
        int slow = 2, fast = 2;
        while (fast < n) {
            // 如果当前检查到的值与slow-2的值不相同，那这个值可以保留下来；
            // 如果相同的话，就是1, 1, 1(slow), 1(fast)这样，那fast处的值不应该填入slow处
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
