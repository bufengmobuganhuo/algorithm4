package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yuzhang
 * @date 2020/7/7 9:43 下午
 * leetcode 794：有效的井字游戏
 * 因为x先下，o后下，那么可能发生的情况是：
 * 1. x胜，则x的个数-o的个数=1
 * 2. o胜，则x的个数-o的个数=0
 * 3. 同时获胜，不可以出现
 */
public class Ex794 {
    public boolean validTicTacToe(String[] board) {
        int oCount = 0, xCount = 0;
        // 统计x,o出现次数
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'O') {
                    oCount++;
                } else if (board[i].charAt(j) == 'X') {
                    xCount++;
                }
            }
        }
        // 如果x != o，说明是x获胜,则xcount-ocount=1
        if (oCount != xCount && xCount - 1 != oCount) {
            return false;
        }
        // 如果是x获胜，则xcount-1=ocount
        if (win(board, 'X') && xCount - 1 != oCount) {
            return false;
        }
        // 如果是o获胜，则xcount=ocount
        if (win(board, 'O') && xCount != oCount) {
            return false;
        }
        return true;
    }

    // 判断x 或 o是否胜利
    private boolean win(String[] board, char player) {
        for (int i = 0; i < 3; i++) {
            // 校验行
            if (board[i].charAt(0) == player && board[i].charAt(1) == player && board[i].charAt(2) == player) {
                return true;
            }
            // 校验列
            if (board[0].charAt(i) == player && board[1].charAt(i) == player && board[2].charAt(i) == player) {
                return true;
            }
        }
        // 校验对角线
        if (board[0].charAt(0) == player && board[1].charAt(1) == player && board[2].charAt(2) == player) {
            return true;
        } else if (board[0].charAt(2) == player && board[1].charAt(1) == player && board[2].charAt(0) == player) {
            return true;
        }
        return false;
    }
}
