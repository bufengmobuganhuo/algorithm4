package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex3143 {
    public int maxPointsInsideSquare(int[][] points, String s) {
        int[] map = new int[26];
        Arrays.fill(map, 1000001);
        int min = 1000000001;
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0], y = points[i][1], chr = s.charAt(i);
            int distance = Math.max(Math.abs(x), Math.abs(y));
            if (distance < map[chr]) {
                map[chr] = distance;
                min = Math.min(min, distance);
            } else if (distance < min) {
                min = distance;
            }
        }
        int ans = 0;
        for (int rad : map) {
            if (rad < min) {
                ans++;
            }
        }
        return ans;
    }
}
