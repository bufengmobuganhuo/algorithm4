package leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1223 {

    private static final int MOD = (int) (Math.pow(10, 9) + 7);

    /**
     * 我们把数字改成0-5
     * 1. dp[i][j][k]：完成了第i次掷骰子，第i次掷出来数字是j，并且已经连续掷了k次数字j的合法序列个数
     * 2. 初始状态：第一次掷骰子时，掷出来数字[0， 5]，并且出现1次的序列个数是1, 即dp[1][0...5][1] = 1
     * 3. 状态转移方程:
     * 假设第i-1次掷出来的是j，并且j连续出现的次数为k，此时枚举第i次的结果
     * (1)dp[i][p][1] = dp[i][p][1] + dp[i-1][j][k]，p != j && p -> [0, 5]，k -> [1, rollMax[j]]
     * 就是上一次是不等于p的j，这一次是p，那么p连续出现的次数就是1
     * (2)dp[i][p][k+1] = dp[i][p][k+1] + dp[i-1][p][k], p = j, k + 1 -> [1, rollMax[p]]
     * 就是上一次是p，这一次也是p，那么这个p的次数就得合法
     * 4. 最终的结果就是dp[n][j][k], n -> [0, 5], k -> [1, rollMax[j]]的累加
     */
    public int dieSimulator(int n, int[] rollMax) {
        int[][][] dp = new int[n + 1][6][16];
        for (int j = 0; j < 6; j++) {
            dp[1][j][1] = 1;
        }
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    for (int p = 0; p < 6; p++) {
                        if (p != j) {
                            dp[i][p][1] = (dp[i][p][1] + dp[i-1][j][k]) % MOD;
                        } else if (k + 1 <= rollMax[p]){
                            dp[i][p][k+1] = (dp[i][p][k+1] + dp[i-1][p][k]) % MOD;
                        }
                    }
                }
            }
        }
        long ans = 0;
        for (int j = 0; j < 6; j++) {
            for (int k = 1; k <= rollMax[j]; k++) {
                ans = (ans + dp[n][j][k]) % MOD;
            }
        }
        return (int) ans;
    }

    // 空间优化
    public int dieSimulator2(int n, int[] rollMax) {
        int[][][] dp = new int[2][6][16];
        for (int j = 0; j < 6; j++) {
            dp[1][j][1] = 1;
        }
        for (int i = 2; i < n + 1; i++) {
            int idx = i & 1;
            // 清空当前数据
            for (int j = 0; j < 6; j++) {
                Arrays.fill(dp[idx][j], 0);
            }
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    for (int p = 0; p < 6; p++) {
                        if (p != j) {
                            dp[idx][p][1] = (dp[idx][p][1] + dp[idx ^ 1][j][k]) % MOD;
                        } else if (k + 1 <= rollMax[p]){
                            dp[idx][p][k+1] = (dp[idx][p][k+1] + dp[idx ^ 1][p][k]) % MOD;
                        }
                    }
                }
            }
        }
        long ans = 0;
        for (int j = 0; j < 6; j++) {
            for (int k = 1; k <= rollMax[j]; k++) {
                ans = (ans + dp[n & 1][j][k]) % MOD;
            }
        }
        return (int) ans;
    }
}
