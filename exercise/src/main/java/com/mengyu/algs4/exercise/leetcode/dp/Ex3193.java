package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex3193 {

    private static final int MOD = 1000000007;
    private Map<Integer, Integer> reqMap = new HashMap<Integer, Integer>();
    private int[][] dp;

    public int numberOfPermutations(int n, int[][] requirements) {
        int maxCnt = 0;
        for (int[] requirement : requirements) {
            maxCnt = Math.max(maxCnt, requirement[1]);
            reqMap.put(requirement[0], requirement[1]);
        }
        // 0没办法和别的数组成逆序对，因此如果有对0有要求，则返回-1
        if (reqMap.getOrDefault(0, 0) != 0) {
            return -1;
        }
        dp = new int[n][maxCnt + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(n - 1, reqMap.get(n - 1));
    }

    private int dfs(int end, int cnt) {
        // 0只能有一个逆序对
        if (end == 0) {
            return 1;
        }
        if (dp[end][cnt] != -1) {
            return dp[end][cnt];
        }
        // 如果对end - 1有要求
        if (reqMap.containsKey(end - 1)) {
            int r = reqMap.get(end - 1);
            // end -1能组成r个逆序对，在此基础上，end的只能和前面的数字组成 cnt - r个逆序对
            // 因为end - 1有r个逆序对，那以end结尾的也至少有r个，因此cnt - r >= 0
            // 把end放到最开始，end最多能组成end个逆序对，因此 cnt - r <= end
            if (r <= cnt && cnt <= r + end) {
                // 如果对end-1和end都有要求，假如end-1的要求被满足了，那么end只能放到最后一个位置，因为如果放到前面的话就会多出来逆序对，
                // end-1的要求无法满足，因此end的数量就是end-1的数量
                return dp[end][cnt] = dfs(end - 1, r);
            } else {
                return dp[end][cnt] = 0;
            }
        } else {
            int sum = 0;
            for (int i = 0; i < Math.min(end, cnt); i++) {
                sum = (sum + dfs(end - 1, cnt - i)) % MOD;
            }
            return dp[end][cnt] = sum;
        }
    }
}
