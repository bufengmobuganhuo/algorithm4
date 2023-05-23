package com.mengyu.algs4.exercise.leetcode.rank.year2022.september4;

/**
 * @author yuzhang
 * @date 2022/9/4 10:42
 * TODO
 */
public class Ex2 {

    public static void main(String[] args) {
        System.out.println(new Ex2().numberOfWays(2, 5, 10));
    }

    private static final long MOD = (long) (Math.pow(10, 9) + 7);

    public int numberOfWays(int startPos, int endPos, int k) {
        int rightEnd = startPos + k, leftEnd = startPos - k;
        int rangeLen = rightEnd - leftEnd + 1;
        int idx = endPos - startPos + k;
        if (idx < 0 && idx >= rangeLen) {
            return 0;
        }
        long[][] dp = new long[k + 1][rangeLen];
        dp[0][k] = 1;
        for (int i = 1; i < k + 1; i++) {
            for (int j = 0; j < rangeLen; j++) {
                long left = j > 0 ? dp[i - 1][j-1] : 0;
                long right = j < rangeLen - 1 ? dp[i - 1][j + 1] : 0;
                dp[i][j] = (left + right) % MOD;
            }
        }

        return (int) dp[k][idx];
    }

}
