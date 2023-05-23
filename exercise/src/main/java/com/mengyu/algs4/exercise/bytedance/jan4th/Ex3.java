package com.mengyu.algs4.exercise.bytedance.jan4th;

/**
 * @author yuzhang
 * @date 2021/1/4 上午9:04
 * TODO
 */
public class Ex3 {
    public int closedIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int res = 0;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (grid[i][j] == 0) {
                    if (dfs(grid, i, j)) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * @param grid
     * @param i    待检查的"周边"
     * @param j    待检查的"周边"
     * @return
     */
    private boolean dfs(int[][] grid, int i, int j) {
        int row = grid.length;
        int col = grid[0].length;
        // 因为递归的关系，这里没有取"="
        if (i < 0 || j < 0 || i >= row || j >= col) {
            return false;
        }
        if (grid[i][j] == 1) {
            return true;
        }
        // 能走到这一步说明之前是陆地（0），这里将其变为水域，不影响
        grid[i][j] = 1;
        // 检查(i,j)的周边是否是水域
        boolean up = dfs(grid, i - 1, j);
        boolean down = dfs(grid, i + 1, j);
        boolean left = dfs(grid, i, j - 1);
        boolean right = dfs(grid, i, j + 1);
        return up && down && left && right;
    }

}
