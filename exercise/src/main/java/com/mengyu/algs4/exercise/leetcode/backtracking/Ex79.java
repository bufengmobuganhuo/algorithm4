package com.mengyu.algs4.exercise.leetcode.backtracking;

/**
 * @author yu zhang
 */
public class Ex79 {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (find(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean find(char[][] board, String word, int i, int j, int pos) {
        if (pos == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i == board.length || j == board[0].length) {
            return false;
        }
        if (word.charAt(pos) != board[i][j]) {
            return false;
        }
        char tmp = board[i][j];
        // 访问过的位置不可再被访问
        board[i][j] = ' ';
        pos++;
        boolean exists = find(board, word, i - 1, j, pos) || find(board, word, i + 1, j, pos) ||
            find(board, word, i, j - 1, pos) || find(board, word, i, j + 1, pos);
        board[i][j] = tmp;
        return exists;
    }
}
