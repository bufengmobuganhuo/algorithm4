package com.mengyu.algs4.exercise.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/1/12 上午9:43
 * TODO
 */
public class Ex1658 {

    /**
     * 转化问题：从两边删除的最少步骤，相当于在中间找一段最长的自数组，使其和=sum(nums)-x
     */
    public int minOperations2(int[] nums, int x) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int maxMidLen = -1;
        int sum = Arrays.stream(nums).sum();
        int leftPtr = 0, rightPtr = 0, curSum = 0, target = sum - x;
        while (leftPtr < nums.length) {
            // 让中间的部分最大，则让右指针尽可能向右移动
            if (rightPtr < nums.length) {
                curSum += nums[rightPtr++];
            }
            // 如果当前的和大于目标和，则让左指针向右移动
            while (curSum > target && leftPtr < nums.length) {
                curSum -= nums[leftPtr++];
            }
            // 如果找到了目标和，则更新中间跨度
            if (curSum == target) {
                maxMidLen = Math.max(maxMidLen, rightPtr - leftPtr);
            }
            // 如果右边到头了，则变左边
            if (rightPtr == nums.length) {
                leftPtr++;
            }
        }
        return maxMidLen == -1 ? -1 : nums.length - maxMidLen;
    }

    /**
     * 一看到求连续区间和问题，条件反射想到前缀和能否求解。
     *
     * @param nums
     * @param x
     * @return
     */
    public int minOperations(int[] nums, int x) {
        // 左半部分前缀和 <sum,i>：0-i的前缀和为sum(不包括nums[i])
        Map<Integer, Integer> lSumMap = new HashMap<>();
        // 右半部分前缀和
        Map<Integer, Integer> rSumMap = new HashMap<>();
        int len = nums.length;
        int lSum = 0;
        for (int i = 0; i < len; i++) {
            lSum += nums[i];
            if (lSum > x) {
                break;
            }
            lSumMap.put(lSum, i + 1);
        }
        int rSum = 0;
        for (int i = len - 1; i >= 0; i--) {
            rSum += nums[i];
            if (rSum > x) {
                break;
            }
            rSumMap.put(rSum, len - i);
        }
        int lStep = lSumMap.getOrDefault(x, Integer.MAX_VALUE);
        int rStep = rSumMap.getOrDefault(x, Integer.MAX_VALUE);
        int cur = Math.min(lStep, rStep);
        for (int lSumItem : lSumMap.keySet()) {
            // 如果右半部分包含另一半的和，则说明可以减成0
            if (rSumMap.containsKey(x - lSumItem)) {
                lStep = lSumMap.get(lSumItem);
                rStep = rSumMap.get(x - lSumItem);
                if (lStep + rStep <= len) {
                    cur = Math.min(cur, lStep + rStep);
                }
            }
        }
        return cur == Integer.MAX_VALUE ? -1 : cur;
    }
}
