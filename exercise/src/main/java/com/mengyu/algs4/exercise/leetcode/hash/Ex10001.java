package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex10001 {
    // 对于坐标(x,y)，对应能照亮的行是x，能照亮的列是y, 能照亮的对角线为(x+y)和(y-x)，取和x轴y轴的交点
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        // 能照亮的行 -> 开启灯的个数
        Map<Integer, Integer> rowMap = new HashMap<>();
        Map<Integer, Integer> colMap = new HashMap<>();
        Map<Integer, Integer> diagonalMap1 = new HashMap<>();
        Map<Integer, Integer> diagonalMap2 = new HashMap<>();
        // lamps中的灯可能重复，用于去重
        Set<Long> lampSet = new HashSet<>();
        for (int[] lamp : lamps) {
            // 加入不成功，说明之前存在，重复
            if (!lampSet.add(hash(lamp[0], lamp[1], n))) {
                continue;
            }
            rowMap.put(lamp[0], rowMap.getOrDefault(lamp[0], 0) + 1);
            colMap.put(lamp[1], colMap.getOrDefault(lamp[1], 0) + 1);
            diagonalMap1.put(lamp[0] + lamp[1], diagonalMap1.getOrDefault(lamp[0] + lamp[1], 0) + 1);
            diagonalMap2.put(lamp[1] - lamp[0], diagonalMap2.getOrDefault(lamp[1] - lamp[0], 0) + 1);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int row = queries[i][0], col = queries[i][1];
            // 看对应的行，列，对角线上是否有照亮
            if (rowMap.containsKey(row)) {
                ans[i] = 1;
            } else if (colMap.containsKey(col)) {
                ans[i] = 1;
            } else if (diagonalMap1.containsKey(row + col)) {
                ans[i] = 1;
            } else if (diagonalMap2.containsKey(col - row)) {
                ans[i] = 1;
            }
            for (int x = row - 1; x < row + 2; x++) {
                for (int y = col - 1; y < col + 2; y++) {
                    if (x < 0 || y < 0 || x >= n || y >= n) {
                        continue;
                    }
                    // 删除成功，说明是灯
                    if (!lampSet.remove(hash(x, y, n))) {
                        continue;
                    }
                    rowMap.put(x , rowMap.getOrDefault(x, 0) - 1);
                    if (rowMap.get(x) <= 0) {
                        rowMap.remove(x);
                    }
                    colMap.put(y, colMap.getOrDefault(y, 0) - 1);
                    if (colMap.get(y) <= 0) {
                        colMap.remove(y);
                    }
                    diagonalMap1.put(x + y, diagonalMap1.getOrDefault(x + y, 0) - 1);
                    if (diagonalMap1.get(x + y) <= 0) {
                        diagonalMap1.remove(x + y);
                    }
                    diagonalMap2.put(y - x, diagonalMap2.getOrDefault(y - x, 0) - 1);
                    if (diagonalMap2.get(y - x) <= 0) {
                        diagonalMap2.remove(y - x);
                    }
                }
            }
        }
        return ans;
    }

    private long hash(int x, int y, int n) {
        return x * n + y;
    }

}
