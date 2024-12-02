package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex3206 {
    public static void main(String[] args) {
        int[] colors = {0, 1, 0, 0, 1};
        System.out.println(new Ex3206().numberOfAlternatingGroups(colors));
    }

    public int numberOfAlternatingGroups(int[] colors) {
        int n = colors.length, cnt = 1;
        int ans = 0;
        for (int i = -2; i < n - 1; i++) {
            if (colors[(i + n) % n] != colors[(i + 1 + n) % n]) {
                cnt++;
            } else {
                cnt = 1;
            }
            if (cnt >= 3) {
                ans++;
            }
        }
        return ans;
    }
}
