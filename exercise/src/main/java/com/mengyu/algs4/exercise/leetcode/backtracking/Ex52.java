package com.mengyu.algs4.exercise.leetcode.backtracking;

/**
 * @author yu zhang
 */
public class Ex52 {

    private int n;

    private int cnt;

    public int totalNQueens(int n) {
        this.n = n;
        this.cnt = 0;
        backtracking(0, 0, 0, 0);
        return cnt;
    }

    private void backtracking(int row, int colsMarked, int diaLeftMarked, int diaRightMarked) {
        if (row == n) {
            cnt++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if ((colsMarked & (1 << i)) > 0) {
                continue;
            }
            if ((diaLeftMarked & (1 << (row - i + n))) > 0) {
                continue;
            }
            if ((diaRightMarked & (1 << (row + i))) > 0) {
                continue;
            }
            colsMarked = colsMarked | (1 << i);
            diaLeftMarked = diaLeftMarked | (1 << (row - i + n));
            diaRightMarked = diaRightMarked | (1 << (row + i));

            backtracking(row + 1, colsMarked, diaLeftMarked, diaRightMarked);

            colsMarked = colsMarked ^ (1 << i);
            diaLeftMarked = diaLeftMarked ^ (1 << (row - i + n));
            diaRightMarked = diaRightMarked ^ (1 << (row + i));
        }
    }
}
