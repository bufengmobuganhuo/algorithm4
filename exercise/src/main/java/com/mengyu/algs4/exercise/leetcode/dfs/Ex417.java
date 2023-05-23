package com.mengyu.algs4.exercise.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex417 {
    private int[][] dirs = {
            {0, -1},
            {0, 1},
            {1, 0},
            {-1, 0},
    };

    private int m, n;

    private int[][] heights;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        this.heights = heights;
        boolean[][] pacific = new boolean[m][n];
        // 从左上角开始找
        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacific);
        }
        for (int j = 1; j < n; j++) {
            dfs(0, j, pacific);
        }
        // 从右下角开始找
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(i, n - 1, atlantic);
        }
        for (int j = 0; j < n; j++) {
            dfs(m - 1, j, atlantic);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> point = new ArrayList<>();
                    point.add(i);
                    point.add(j);
                    ans.add(point);
                }
            }
        }
        return ans;
    }

    private void dfs(int row, int col, boolean[][] ocean) {
        if (ocean[row][col]) {
            return;
        }
        ocean[row][col] = true;
        for (int[] dir : dirs) {
            int newRow = row + dir[0], newCol = col + dir[1];
            // 如果可以从边界向内流
            if (newRow < m && newRow >= 0 && newCol < n && newCol >= 0 && heights[row][col] <= heights[newRow][newCol]) {
                dfs(newRow, newCol, ocean);
            }
        }
    }
}
