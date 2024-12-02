package com.mengyu.algs4.exercise.leetcode.hash;

/**
 * @author yu zhang
 */
public class Ex3238 {
    public int winningPlayerCount(int n, int[][] pick) {
        int cnt = 0;
        boolean[] marked = new boolean[n];
        int[][] map = new int[n][11];
        for (int i = 0; i < pick.length; i++) {
            int player = pick[i][0], color = pick[i][1];
            map[player][color]++;
            if (!marked[player] && map[player][color] > player) {
                marked[player] = true;
                cnt++;
            }
        }
        return cnt;
    }
}
