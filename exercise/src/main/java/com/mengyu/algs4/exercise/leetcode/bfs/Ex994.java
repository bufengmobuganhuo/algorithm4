package com.mengyu.algs4.exercise.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex994 {

    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(new Ex994().orangesRotting(grid));
    }

    public int orangesRotting(int[][] grid) {
        int freshCnt = 0, minutes = -1;
        int m = grid.length, n = grid[0].length;
        Queue<int[]> que = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshCnt++;
                } else if (grid[i][j] == 2) {
                    freshCnt++;
                    que.offer(new int[]{i, j});
                }
            }
        }

        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int[] orange = que.poll();
                int x = orange[0], y = orange[1];
                freshCnt--;
                if (y < n - 1 && grid[x][y + 1] != 2 && grid[x][y + 1] != 0) {
                    que.offer(new int[]{x, y + 1});
                    grid[x][y + 1] = 2;
                }
                if (x < m - 1 && grid[x + 1][y] != 2 && grid[x + 1][y] != 0) {
                    que.offer(new int[]{x + 1, y});
                    grid[x + 1][y] = 2;
                }
                if (y > 0 && grid[x][y - 1] != 2 && grid[x][y - 1] != 0) {
                    que.offer(new int[]{x, y - 1});
                    grid[x][y - 1] = 2;
                }
                if (x > 0 && grid[x - 1][y] != 2 && grid[x - 1][y] != 0) {
                    que.offer(new int[]{x - 1, y});
                    grid[x - 1][y] = 2;
                }
            }
            minutes++;
        }
        return freshCnt == 0 ? Math.max(0, minutes) : -1;
    }
}
