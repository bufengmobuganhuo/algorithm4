package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 *
 * @author yuzhang
 * @date 2026/11/5 21:59
 */
public class Ex1899 {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int targetX = target[0], targetY = target[1], targetZ = target[2];
        int x = Integer.MIN_VALUE, y = Integer.MIN_VALUE, z = Integer.MIN_VALUE;
        for (int[] triplet : triplets) {
            int x1 = triplet[0], y1 = triplet[1], z1 = triplet[2];
            if (x1 <= targetX && y1 <= targetY && z1 <= targetZ) {
                x = Math.max(x, x1);
                y = Math.max(y, y1);
                z = Math.max(z, z1);
            }
        }
        return x == targetX && y == targetY && z == targetZ;
    }
}
