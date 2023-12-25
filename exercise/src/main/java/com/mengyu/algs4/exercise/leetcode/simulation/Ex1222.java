package com.mengyu.algs4.exercise.leetcode.simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex1222 {

    public static void main(String[] args) {
        int[][] queens = {{0,1},{1,0},{4,0},{0,4},{3,3},{2,4}};
        int[] king = {0,0};
        System.out.println(new Ex1222().queensAttacktheKing(queens, king));
    }

    private int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        int[][] matrix = new int[8][8];
        for (int[] queen : queens) {
            matrix[queen[0]][queen[1]] = 1;
        }
        int x = king[0], y = king[1];
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] direction : directions) {
            for (int i = 1; i < 8; i++) {
                int nx = x + i * direction[0];
                int ny = y + i * direction[1];
                if (nx >= 8 || ny >= 8 || nx < 0 || ny < 0) {
                    break;
                }
                if (matrix[nx][ny] == 1) {
                    ans.add(Arrays.asList(nx, ny));
                    break;
                }
            }
        }
        return ans;
    }
}
