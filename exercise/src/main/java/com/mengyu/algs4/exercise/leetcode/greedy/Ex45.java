package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex45 {
    public int jump(int[] nums) {
        int maxPos = 0;
        // 上次起跳点能到达的最远位置
        int end = 0;
        int step = 0;
        // 只需要遍历到n - 2即可，如果遍历到n - 1的话会多算一次
        for (int i = 0; i < nums.length - 1; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            // 说明已经遍历到了上次起跳点能到达的最远位置，需要再跳一次
            if (i == end) {
                end = maxPos;
                step++;
            }
        }
        return step;
    }
}
