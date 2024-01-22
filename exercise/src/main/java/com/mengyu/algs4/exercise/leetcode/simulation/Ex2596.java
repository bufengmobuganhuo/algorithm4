package com.mengyu.algs4.exercise.leetcode.simulation;

/**
 * @author yu zhang
 */
public class Ex2596 {

    public static void main(String[] args) {
        int[][] grid = {{0,3,6},{5,8,1},{2,7,4}};
        System.out.println(new Ex2596().checkValidGrid(grid));
    }

    private int[][] directions = {
            {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}
    };

    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.length;
        int x = 0, y = 0;
        int track = 1;
        while (true) {
            boolean found = false;
            for (int[] direct : directions) {
                int nextX = x + direct[0], nextY = y + direct[1];
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && grid[nextX][nextY] == track) {
                    x = nextX;
                    y = nextY;
                    track++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            } else if ((track - 1) == n * n - 1) {
                return true;
            }
        }
    }
}
