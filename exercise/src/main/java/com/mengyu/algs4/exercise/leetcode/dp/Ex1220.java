package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1220 {
    public static void main(String[] args) {
        System.out.println(new Ex1220().countVowelPermutation2(10));
    }

    private static final int mod = 1000000007;

    public int countVowelPermutation(int n) {
        if (n == 1) {
            return 5;
        } else if (n == 2) {
            return 10;
        }
        long[] dp = new long[5];
        long[] ndp = new long[5];
        if (n >= 2) {
            dp = new long[]{3, 2, 2, 1, 2};
        }
        for (int i = 3; i < n + 1; i++) {
            ndp[0] = (dp[1] + dp[2] + dp[4]) % mod;
            ndp[1] = (dp[0] + dp[2]) % mod;
            ndp[2] = (dp[1] + dp[3]) % mod;
            ndp[3] = (dp[2]) % mod;
            ndp[4] = (dp[2] + dp[3]) % mod;
            System.arraycopy(ndp, 0, dp, 0, 5);
        }
        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + ndp[i]) % mod;
        }
        return (int)ans;
    }

    /**
     * 可以转化成两个矩阵相乘
     * [0 1 1 0 1]             [1]
     * [1 0 1 0 0]             [1]
     * [0 1 0 1 0](n-1次方) *  [1]
     * [0 0 1 0 0]            [1]
     * [0 0 1 1 0]            [1]
     * 但是因为列向量不好弄，所以转置
     *               [0 1 0 0 0]
     *               [1 0 1 0 0]
     * [1 1 1 1 1] * [1 1 0 1 1](n - 1次方)
     *               [0 0 1 0 1]
     *               [1 0 0 0 0]
     */
    public int countVowelPermutation2(int n) {
        if (n == 1) {
            return 5;
        } else if (n == 2) {
            return 10;
        }
        long[][] factor = {
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0}
        };
        // n - 1=0的话不支持，所以上面需要过滤
        long[][] res = pow(factor, n - 1);
        long ans = 0 ;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = (ans + res[i][j]) % mod;
            }
        }
        return (int) ans;
    }

    private long[][] pow(long[][] a, int b) {
        if (b == 1) {
            return a;
        }
        if (b % 2 == 1) {
            return multi(a, pow(a, b - 1));
        }
        long[][] c = pow(a, b / 2);
        return multi(c, c);
    }

    private long[][] multi(long[][] a, long[][] b) {
        int row = a.length, col = b[0].length;
        long[][] res = new long[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[i][j] = 0;
                for (int k = 0; k < a[i].length; k++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return res;
    }
}
