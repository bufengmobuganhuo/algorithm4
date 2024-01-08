package com.mengyu.algs4.exercise.leetcode.bit;

/**
 * @author yu zhang
 */
public class Ex2397 {
    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length, n = matrix[0].length;
        int[] mask = new int[m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mask[i] += matrix[i][j] << (n - j - 1);
            }
        }

        int ans = 0;
        int cur = 0, limit = 1 << n;
        // 选中哪一列，就让二进制对应的那一位为1，则所有的情况就是从0到limit-1
        while (++cur < limit) {
            // 选中的列 != numSelect
            if (Integer.bitCount(cur) != numSelect) {
                continue;
            }
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                // 说明全覆盖了
                if ((cur & mask[i]) == mask[i]) {
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
