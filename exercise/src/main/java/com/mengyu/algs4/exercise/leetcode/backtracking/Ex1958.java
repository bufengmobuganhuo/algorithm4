package com.mengyu.algs4.exercise.leetcode.backtracking;

/**
 * @author yu zhang
 */
public class Ex1958 {

    public static void main(String[] args) {
        char[][] board = {
                {'B','.','.','W','W','B','.','.'},
                {'.','.','.','W','B','W','.','B'},
                {'.','W','W','.','B','B','.','B'},
                {'W','B','B','W','W','.','.','B'},
                {'B','.','W','W','.','.','W','B'},
                {'.','B','W','B','.','B','.','W'},
                {'W','W','.','.','.','.','B','W'},
                {'W','B','B','.','B','.','W','B'}};
        System.out.println(new Ex1958().checkMove(board, 4, 4, 'W'));
    }

    private char[][] board;

    private char color;

    private int[][] directions = {
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1}
    };

    public boolean checkMove2(char[][] board, int rMove, int cMove, char color) {
        for (int[] direct : directions) {
            if (check(board, rMove, cMove, color, direct[0], direct[1])) {
                return true;
            }
        }
        return false;
    }

    private boolean check(char[][] board, int rMove, int cMove, char color, int xDirect, int yDirect) {
        int len = 0;
        int x = rMove + xDirect, y = cMove + yDirect;
        while (x < 8 && x >= 0 && y < 8 && y >= 0) {
            if (len == 0) {
                if (board[x][y] == '.' || board[x][y] == color) {
                    return false;
                }
            } else {
                if (board[x][y] == '.') {
                    return false;
                }
                if (board[x][y] == color) {
                    return true;
                }
            }
            len++;
            x += xDirect;
            y += yDirect;
        }
        return false;
    }

    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        this.board = board;
        this.color = color;
        return dfs(rMove, cMove, -1, 0, 0, false) || dfs(rMove, cMove, -1, 1, 0, false) ||
                dfs(rMove, cMove, 0, 1, 0, false) || dfs(rMove, cMove, 1, 1, 0, false) ||
                dfs(rMove, cMove, 1, 0, 0, false) || dfs(rMove, cMove, 1, -1, 0, false) ||
                dfs(rMove, cMove, 0, -1, 0, false) || dfs(rMove, cMove, -1, -1, 0 ,false);
    }

    private boolean dfs(int x, int y, int xDirect, int yDirect, int len, boolean changed) {
        if (y >= board[0].length || x >= board.length || y < 0 || x < 0) {
            return false;
        }
        if (board[x][y] == color) {
            return len >= 1;
        } else if (board[x][y] == '.'  && !changed) {
            return dfs(x + xDirect, y + yDirect, xDirect, yDirect, 0, true);
        } else if (board[x][y] != '.'){
            len++;
            return dfs(x + xDirect, y + yDirect, xDirect, yDirect, len, changed);
        }
        return false;
    }
}
