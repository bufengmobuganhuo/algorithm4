package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yu zhang
 */
public class Ex1696 {
    public static void main(String[] args) {
        int[] nums = {10,-5,-2,4,0,3};
        System.out.println(new Ex1696().maxResult(nums, 3));
    }
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerFirst(0);
        for (int i = 1; i < n; i++) {
            // 超出范围
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }
            // 从大 -> 小的队列，队首是最大值
            dp[i] = dp[deque.peekFirst()] + nums[i];
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        return dp[n - 1];
    }
}
