package com.mengyu.algs4.exercise.leetcode.count;

/**
 * @author yu zhang
 */
public class Ex1267 {
    public static void main(String[] args) {
        int[][] grid = {{1,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,1}};
        System.out.println(new Ex1267().countServers(grid));
    }

    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rowCount = new int[m], colCount = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // 统计每一行有几个服务器
                    rowCount[i]++;
                    // 统计每一列有几个服务器
                    colCount[j]++;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // rowCount[i] > 1：说明在第i行有其他的服务器，当前服务器可以通信
                // colCount[j] > 1：说明在第j行有其他的服务器，当前服务器可以通信
                if (grid[i][j] == 1 && (rowCount[i] > 1 || colCount[j] > 1)) {
                    count++;
                }
            }
        }
        return count;
    }
}
