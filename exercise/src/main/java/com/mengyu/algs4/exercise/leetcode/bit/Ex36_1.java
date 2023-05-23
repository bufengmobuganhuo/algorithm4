package com.mengyu.algs4.exercise.leetcode.bit;

/**
 * @author yu zhang
 */
public class Ex36_1 {
    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[9];
        int[] col = new int[9];
        int[] box = new int[9];
        int shift = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                shift = 1 << (board[i][j] - 'a');
                int boxIdx = (i / 3) * 3 + j / 3;
                if ((shift & row[i]) > 0 || (shift & col[j]) > 0 || (shift & box[boxIdx]) > 0) {
                    return false;
                }
                row[i] |= shift;
                col[j] |= shift;
                box[boxIdx] |= shift;
            }
        }
        return true;
    }
}
