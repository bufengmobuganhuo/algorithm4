package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex3144 {

    public static void main(String[] args) {
        System.out.println(new Ex3144().minimumSubstringsInPartition("fabccddg"));
    }

    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + 1;
            int maxCnt = 1;
            int[] cntMap = new int[26];
            cntMap[s.charAt(i) - 'a'] = 1;
            int size = 1;
            for (int j = i - 1; j >= 0; j--) {
                int cnt = cntMap[s.charAt(j) - 'a'];
                cntMap[s.charAt(j) - 'a'] = cnt + 1;
                maxCnt = Math.max(maxCnt, cnt + 1);
                if (cnt == 0) {
                    size++;
                }
                if (maxCnt * size == i - j + 1) {
                    dp[i] = Math.min(dp[i], (j > 0 ? dp[j - 1] : 0) + 1);
                }
            }
        }
        return dp[n - 1];
    }
}
