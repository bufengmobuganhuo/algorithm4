package com.mengyu.algs4.exercise.leetcode.hash;

/**
 * @author yu zhang
 */
public class Ex2103 {

    public static void main(String[] args) {
        System.out.println(new Ex2103().countPoints("B0B6G0R6R0R6G9"));
    }

    public int countPoints(String rings) {
        int n = rings.length();
        int[] map = new int[10];
        int cnt = 0;
        for (int i = 0; i < n - 1; i += 2) {
            char color = rings.charAt(i);
            int idx = rings.charAt(i + 1) - '0';
            if (map[idx] == 7) {
                continue;
            }
            if (color == 'R') {
                map[idx] = map[idx] | 4;
            } else if (color == 'G') {
                map[idx] = map[idx] | 2;
            } else {
                map[idx] = map[idx] | 1;
            }
            if (map[idx] == 7) {
                cnt++;
            }
        }
        return cnt;
    }
}
