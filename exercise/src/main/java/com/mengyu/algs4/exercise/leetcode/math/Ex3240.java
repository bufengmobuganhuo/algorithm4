package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex3240 {
    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0, cnt1 = 0;
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                // 先看对称的位置
                cnt1 = grid[i][j] + grid[m - i - 1][j] + grid[i][n - j - 1] + grid[m - i - 1][n - j - 1];
                // 将四个位置都变成0，或者都变成1，都可以满足4的倍数
                ans += Math.min(cnt1, 4- cnt1);
            }
        }
        cnt1 = 0;
        int diff = 0;
        // 有奇数个行，则中间的那一行
        if (m % 2 != 0) {
            for (int j = 0; j < n / 2; j++) {
                if ((grid[m / 2][j] ^ grid[m / 2][n - j - 1]) != 0) {
                    diff++;
                } else {
                    cnt1 += grid[m / 2][j] * 2;
                }
            }
        }
        // 奇数个列，中间的那一列
        if (n % 2 != 0) {
            for (int i = 0; i < m / 2; i++) {
                if ((grid[i][n / 2] ^ grid[m - i - 1][n / 2]) != 0) {
                    diff++;
                } else {
                    cnt1 += grid[i][n / 2] * 2;
                }
            }
        }
        if (m % 2 != 0 && n % 2 != 0) {
            // 如果行列都是奇数，那中间的必须要为0，否则无法达到4的倍数
            ans += grid[m / 2][n / 2];
        }
        if (diff > 0) {
            // 如果有不同的，则无论如何都要翻转，翻转成0或1可以根据是否为4的倍数灵活变化
            ans += diff;
        } else {
            // 如果没有需要翻转的，其他对称位置都已经保证了4的倍数，但是中间的奇数列和奇数行还没有保证
            ans += cnt1 % 4;
        }
        return ans;
    }
}
