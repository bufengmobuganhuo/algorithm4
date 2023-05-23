package com.mengyu.algs4.exercise.leetcode.rank.year2022.may22;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yuzhang
 * @date 2022/5/22 10:38
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[][] stockPrices = {{1, 1}, {499999999, 2}, {999999998, 3}, {1000000000, 5}};
        System.out.println(new Ex3().minimumLines(stockPrices));
    }

    public int minimumLines(int[][] stockPrices) {
        int count = 0, len = stockPrices.length;
        Arrays.sort(stockPrices, Comparator.comparingInt(o -> o[0]));
        long x1 = -1, y1 = -1;
        for (int i = 0; i < len; ) {
            int[] stockPrice = stockPrices[i];
            long x2 = stockPrice[0], y2 = stockPrice[1];
            if (x1 == -1) {
                x1 = x2;
                y1 = y2;
                i++;
            }  else {
                count++;
                i++;
                while (i < len && fit(x1, y1, x2, y2, stockPrices[i][0], stockPrices[i][1])) {
                    i++;
                }
                x1 = stockPrices[i - 1][0];
                y1 = stockPrices[i - 1][1];
            }
        }
        return count;
    }

    private boolean fit(long x1, long y1, long x2, long y2, long x3, long y3) {
        // 斜率相等，但是因为除法精度的问题，转化成乘法
        return (y1 - y2) * (x3 - x2) == (x1 - x2) * (y3 - y2);
    }
}
