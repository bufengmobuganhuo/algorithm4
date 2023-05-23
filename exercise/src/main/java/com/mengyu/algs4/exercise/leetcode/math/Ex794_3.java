package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yuzhang
 * @date 2021/7/24 下午3:27
 * TODO
 */
public class Ex794_3 {
    public boolean validTicTacToe(String[] board) {
        int oCount = 0, xCount = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'X') {
                    xCount++;
                } else if (board[i].charAt(j) == 'O') {
                    oCount++;
                }
            }
        }
        if (win(board, 'X') && xCount - oCount != 1) {
            return false;
        }
        if (win(board, 'O') && xCount != oCount) {
            return false;
        }
        if (oCount > xCount) {
            return false;
        }
        if (xCount - oCount > 1){
            return false;
        }
        return true;
    }

    private boolean win(String[] board, char chr) {
        // 校验行
        for (int i = 0; i < board.length; i++) {
            if (board[i].charAt(0) == chr && board[i].charAt(1) == chr && board[i].charAt(2) == chr) {
                return true;
            }
        }
        // 校验列
        for (int i = 0; i < board[0].length(); i++) {
            if (board[0].charAt(i) == chr && board[1].charAt(i) == chr && board[2].charAt(i) == chr) {
                return true;
            }
        }
        // 校验对角线
        if (board[0].charAt(0) == chr && board[1].charAt(1) == chr && board[2].charAt(2) == chr) {
            return true;
        }
        if (board[0].charAt(2) == chr && board[1].charAt(1) == chr && board[2].charAt(0) == chr) {
            return true;
        }
        return false;
    }
}
