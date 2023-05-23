package com.mengyu.algs4.exercise.leetcode.rank.year2021.jun20;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex3 {
    public static void main(String[] args) {
        int[][] grid1 = {
            {1,1,1,1,0,0},
            {1,1,0,1,0,0},
            {1,0,0,1,1,1},
            {1,1,1,0,0,1},
            {1,1,1,1,1,0},
            {1,0,1,0,1,0},
            {0,1,1,1,0,1},
            {1,0,0,0,1,1},
            {1,0,0,0,1,0},
            {1,1,1,1,1,0}};
        int[][] grid2 = {
            {1,1,1,1,0,1},
            {0,0,1,0,1,0},
            {1,1,1,1,1,1},
            {0,1,1,1,1,1},
            {1,1,1,0,1,0},
            {0,1,1,1,1,1},
            {1,1,0,1,1,1},
            {1,0,0,1,0,1},
            {1,1,1,1,1,1},
            {1,0,0,1,0,0}};
        Ex3 ex3 = new Ex3();
        System.out.println(ex3.countSubIslands(grid1,grid2));
    }
    private Set<Integer> marked;
    private int m, n;

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int ans = 0;
        m = grid1.length;
        n = grid1[0].length;
        marked = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int id = i * n + j;
                if (grid2[i][j] == 1 && !marked.contains(id) && dfs(grid1, grid2, id)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int start) {
        marked.add(start);
        int i = start / n, j = start % n;
        boolean flag1 = true, flag2 = true, flag3 = true, flag4 = true;
        if (j < n - 1 && grid2[i][j + 1] == 1 && !marked.contains(i * n + j + 1)) {
            flag1 = dfs(grid1, grid2, i * n + j + 1);
        }
        if (j > 0 && grid2[i][j - 1] == 1 && !marked.contains(i * n + j - 1)) {
            flag2 = dfs(grid1, grid2, i * n + j - 1);
        }
        if (i > 0 && grid2[i - 1][j] == 1 && !marked.contains((i - 1) * n + j)) {
            flag3 = dfs(grid1, grid2, (i - 1) * n + j);
        }
        if (i < m - 1 && grid2[i + 1][j] == 1 && !marked.contains((i + 1) * n + j)) {
            flag4 = dfs(grid1, grid2, (i + 1) * n + j);
        }
        if (grid1[i][j] != 1) {
            return false;
        }
        return flag1 && flag2 && flag3 && flag4;
    }
}
