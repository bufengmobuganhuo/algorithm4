package com.mengyu.algs4.exercise.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex51_1 {

    private List<List<String>> ans;

    private String plan;

    private int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        this.ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        this.plan = sb.toString();
        backtracking(0, new ArrayList<>(), 0, 0, 0);
        return ans;
    }

    private void backtracking(int row, List<String> board, int cols, int diaLeft, int diaRight) {
        if (row == n) {
            ans.add(new ArrayList<>(board));
            return;
        }
        StringBuilder sb = new StringBuilder(plan);
        for (int i = 0; i < n; i++) {
            if ((cols & (1 << i)) > 0) {
                continue;
            }
            if ((diaLeft & (1 << (row - i + n))) > 0) {
                continue;
            }
            if ((diaRight & (1 << (row + i))) > 0) {
                continue;
            }
            sb.setCharAt(i, 'Q');
            board.add(sb.toString());
            cols = cols | (1 << i);
            diaLeft = diaLeft | (1 << (row - i + n));
            diaRight = diaRight | (1 << (row + i));

            backtracking(row + 1, board, cols, diaLeft, diaRight);

            sb.setCharAt(i, '.');
            board.remove(board.size() - 1);
            cols = cols ^ (1 << i);
            diaLeft = diaLeft ^ (1 << (row - i + n));
            diaRight = diaRight ^ (1 << (row + i));
        }
    }

    private void backtracking(int row, List<String> board, Set<Integer> cols, Set<Integer> diaLeft,
                              Set<Integer> diaRight) {
        if (row == n) {
            ans.add(new ArrayList<>(board));
            return;
        }
        StringBuilder sb = new StringBuilder(plan);
        for (int i = 0; i < n; i++) {
            if (cols.contains(i)) {
                continue;
            }
            if (diaLeft.contains(row - i)) {
                continue;
            }
            if (diaRight.contains(row + i)) {
                continue;
            }
            sb.setCharAt(i, 'Q');
            board.add(sb.toString());
            cols.add(i);
            diaLeft.add(row - i);
            diaRight.add(row + i);

            backtracking(row + 1, board, cols, diaLeft, diaRight);

            sb.setCharAt(i, '.');
            board.remove(board.size() - 1);
            cols.remove(i);
            diaLeft.remove(row - i);
            diaRight.remove(row + i);
        }
    }

    private void backtracking(int row, List<String> board) {
        if (row == n) {
            ans.add(new ArrayList<>(board));
            return;
        }
        StringBuilder sb = new StringBuilder(plan);
        for (int i = 0; i < n; i++) {
            if (isValid(row, i, board)) {
                sb.setCharAt(i, 'Q');
                board.add(sb.toString());
                backtracking(row + 1, board);
                sb.setCharAt(i, '.');
                board.remove(board.size() - 1);
            }
        }
    }

    private boolean isValid(int row, int col, List<String> board) {
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }
}
