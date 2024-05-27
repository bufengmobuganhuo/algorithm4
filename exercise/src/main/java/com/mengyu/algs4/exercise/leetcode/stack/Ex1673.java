package com.mengyu.algs4.exercise.leetcode.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex1673 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Ex1673().mostCompetitive(new int[]{2, 4, 3, 3, 5, 4, 9, 6}, 4)));
    }

    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> stack = new LinkedList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            // 还需要保证组成要求的子数组长度
            while (!stack.isEmpty() && stack.size() + n - i > k && stack.peekLast() > num) {
                stack.pollLast();
            }
            stack.offerLast(num);
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = stack.pollFirst();
        }
        return ans;
    }
}
