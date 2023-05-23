package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/7/4 上午10:13
 * TODO
 */
public class Ex5801 {
    public static void main(String[] args) {
        Ex5801 ex5801 = new Ex5801();
        int[] dist = {46, 33, 44, 42, 46, 36, 7, 36, 31, 47, 38, 42, 43, 48, 48, 25, 28, 44, 49, 47, 29, 32, 30, 6, 42, 9, 39, 48, 22, 26, 50, 34, 40, 22, 10, 45, 7, 43, 24, 18, 40, 44, 17, 39, 36};
        int[] speed = {1, 2, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 7, 1, 1, 3, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 6, 1, 1, 1, 8, 1, 1, 1, 3, 6, 1, 3, 1, 1};
        System.out.println(ex5801.eliminateMaximum(dist, speed));
    }

    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int count = 0;
        // 不消灭怪物时，到达的时间
        double[] times = new double[n];
        for (int i = 0; i < n; i++) {
            times[i] = (double) dist[i] / speed[i];
        }
        // 按照到达时间排序，先到达的要先消灭
        Arrays.sort(times);
        for (int i = 0; i < n; i++) {
            // 到第i分钟时，就需要消灭times[i]的怪物
            if (times[i] > i){
                count++;
                // 如果提前到达，就结束游戏
            }else {
                return count;
            }
        }
        return count;
    }
}
