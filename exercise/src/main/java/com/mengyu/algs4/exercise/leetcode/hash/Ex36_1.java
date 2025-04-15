package com.mengyu.algs4.exercise.leetcode.hash;

/**
 * @author yu zhang
 */
public class Ex36_1 {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] colMap = new boolean[9][10];
        boolean[][] rowMap = new boolean[9][10];
        boolean[][] boardMap = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char chr = board[i][j];
                if (chr == '.') {
                    continue;
                }
                int num = chr - '0';
                if (colMap[j][num]) {
                    return false;
                }
                colMap[j][num] = true;
                if (rowMap[i][num]) {
                    return false;
                }
                rowMap[i][num] = true;
                int idx = (i / 3) * 3 + (j / 3);
                if (boardMap[idx][num]) {
                    return false;
                }
                boardMap[idx][num] = true;
            }
        }
        return true;
    }
}
