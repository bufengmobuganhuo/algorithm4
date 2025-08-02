package com.mengyu.algs4.exercise.leetcode.simulation;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex289 {

    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        new Ex289().gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
    }

    private final int[][] directions = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}
    };

    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                for (int[] direction : directions) {
                    int r = direction[0] + i, c = direction[1] + j;
                    if (r >= 0 && r < m && c >= 0 && c < n) {
                        if (board[r][c] == 1 || board[r][c] == 2) {
                            cnt++;
                        }
                        if (direction[0] == -1 && direction[1] == -1) {
                            if (board[r][c] == 2) {
                                board[r][c] = 0;
                            } else if (board[r][c] == 3) {
                                board[r][c] = 1;
                            }
                        }
                    }
                }
                if (cnt < 2) {
                    if (board[i][j] == 1) {
                        board[i][j] = 2;
                    }
                } else if (cnt == 3 && board[i][j] == 0) {
                    board[i][j] = 3;
                } else if (cnt > 3) {
                    if (board[i][j] == 1) {
                        board[i][j] = 2;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                } else if (board[i][j] == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }
}
