package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex3153 {
    public static void main(String[] args) {
        System.out.println(new Ex3153().sumDigitDifferences(new int[]{13, 23, 12}));
    }
    public long sumDigitDifferences(int[] nums) {
        int[][] map = new int[10][10];
        for (int i = 0; i < 10; i++) {
            if (nums[0] == 0) {
                break;
            }
            for (int j = 0; j < nums.length; j++) {
                int num = nums[j];
                int digit = num % 10;
                map[i][digit]++;
                nums[j] /= 10;
            }
        }
        long ans = 0;
        for (int i = 0; i < 10; i++) {
            long multi = 0;
            for (int j = 0; j < map[i].length; j++) {
                for (int k = j + 1; k < map.length; k++) {
                    if (map[i][j] * map[i][k] != 0) {
                        multi += (long) map[i][j] * map[i][k];
                    }
                }
            }
            ans += multi;
        }
        return ans;
    }
}
