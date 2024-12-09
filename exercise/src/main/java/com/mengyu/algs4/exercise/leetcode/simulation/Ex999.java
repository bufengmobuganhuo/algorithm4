package com.mengyu.algs4.exercise.leetcode.simulation;

/**
 * @author yu zhang
 */
public class  Ex999 {
    public int numRookCaptures(char[][] board) {
        int si = 0, sj = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    si = i;
                    sj = j;
                    break;
                }
            }
        }
        int res = 0;
        int[][] directions = {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };
        for (int[] direction : directions) {
            for (int step = 0; ; step++) {
                int i = si + step * direction[0], j = sj + step * direction[1];
                if (i < 0 || j < 0 || i > 7 || j > 7 || board[i][j] == 'B') {
                    break;
                }
                if (board[i][j] == 'p') {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}
