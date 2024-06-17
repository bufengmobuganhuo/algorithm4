package com.mengyu.algs4.exercise.leetcode.dfs;

/**
 * @author yu zhang
 */
public class Ex419 {
    public static void main(String[] args) {
        System.out.println(new Ex419().countBattleships2(new char[][]{
                {'X','X','X','.'},
                {'.','.','.','.'},
                {'.','.','.','.'}
        }));
    }


    public int countBattleships2(char[][] board) {
        int m = board.length, n = board[0].length;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    int spaceCnt = 0;
                    if (j + 1 >= n || (j + 1 < n && board[i][j + 1] == '.')) {
                        spaceCnt++;
                    }
                    if (i + 1 >= m || (i + 1 < m && board[i + 1][j] == '.')) {
                        spaceCnt++;
                    }
                    cnt = spaceCnt == 2 ? cnt + 1 : cnt;
                }
            }
        }
        return cnt;
    }

    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    dfs(board, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private void dfs(char[][] board, int i, int j) {
        board[i][j] = '#';
        if (j + 1 < board[0].length && board[i][j + 1] == 'X') {
            dfs(board, i, j + 1);
        } else if (i + 1 < board.length && board[i + 1][j] == 'X') {
            dfs(board, i + 1, j);
        }
    }
}
