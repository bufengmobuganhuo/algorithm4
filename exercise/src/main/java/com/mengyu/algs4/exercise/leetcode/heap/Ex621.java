package com.mengyu.algs4.exercise.leetcode.heap;

/**
 * @author yu zhang
 */
public class Ex621 {
    public static void main(String[] args) {
        Ex621 ex621 = new Ex621();
        char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        System.out.println(ex621.leastInterval(tasks, 2));
    }

    /**
     * 填格子，查看官方题解二
     */
    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        int maxExec = 0;
        for (char task : tasks) {
            counts[task - 'A']++;
            maxExec = Math.max(maxExec, counts[task - 'A']);
        }
        int maxCount = 0;
        for (int count : counts) {
            if (count == maxExec) {
                maxCount++;
            }
        }
        return Math.max(tasks.length, (maxExec - 1) * (n + 1) + maxCount);
    }

}
