package com.mengyu.algs4.exercise.leetcode.slide_window;

import java.util.List;

/**
 * @author yu zhang
 */
public class Ex2824 {
    public int countPairs(List<Integer> nums, int target) {
        int n = nums.size();
        nums.sort(Integer::compareTo);
        int cnt = 0;
        int leftPtr = 0, rightPtr = n - 1;
        while (leftPtr < rightPtr) {
            if (nums.get(leftPtr) + nums.get(rightPtr) >= target) {
                rightPtr--;
            } else {
                cnt += (rightPtr - leftPtr);
                leftPtr++;
            }
        }
        return cnt;
    }
}
