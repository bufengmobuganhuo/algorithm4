package com.mengyu.algs4.exercise.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yu zhang
 */
public class Ex452 {

    public static void main(String[] args) {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println(new Ex452().findMinArrowShots(points));
    }

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int cnt = 1;
        int pos = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (pos < points[i][0]) {
                pos = points[i][1];
                cnt++;
            }
        }
        return cnt;
    }
}
