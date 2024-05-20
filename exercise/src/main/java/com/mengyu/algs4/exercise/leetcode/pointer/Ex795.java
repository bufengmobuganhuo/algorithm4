package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex795 {
    /**
     * 维护两个指针，last1: 距离当前i最近的一个num，left <= num <= right，last2：距离当前i最近的一个num，num > right
     * 当遍历到i时，如果 left <= nums[i] <= right，另last1 = i
     * 如果nums[i] <= right，说明这个数字是可以包含到子数组中的，组成子数组的个数为: last1 - last2，last1 != -1
     * 如果nums[i] > right，说明这个数不能包含到子数组，另last1 = -1, last2 = i
     */
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int last1 = -1, last2 = -1;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                last1 = i;
            } else if (nums[i] > right) {
                last1 = -1;
                last2 = i;
            }
            if (last1 != -1) {
                cnt += last1 - last2;
            }
        }
        return cnt;
    }
}
