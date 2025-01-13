package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex1387 {

    public static void main(String[] args) {
        System.out.println(new Ex1387().getKth(12, 15, 2));
    }

    private int[] dp;

    public Ex1387() {
        dp = new int[10000];
    }

    public int getKth(int lo, int hi, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] != o2[1]) {
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });
        for (int i = lo; i < hi + 1; i++) {
            int power = getPower(i);
            if (heap.size() < k) {
                heap.offer(new int[]{i, power});
            } else if (heap.peek()[1] > power) {
                heap.poll();
                heap.offer(new int[]{i, power});
            }
        }
        return heap.peek()[0];
    }

    private int getPower(int num) {
        if (num >= 0 && num < dp.length && ( num == 1 || dp[num] != 0)) {
            return dp[num];
        }
        if (num % 2 == 0) {
            dp[num] = getPower(num / 2) + 1;
        } else {
            dp[num] = getPower(3 * num + 1) + 1;
        }
        return dp[num];
    }
}
