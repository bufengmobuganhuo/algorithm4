package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1092 {
    public static void main(String[] args) {
        System.out.println(new Ex1092().shortestCommonSupersequence("abac", "cab"));
    }

    /**
     * 1. dp[i][j]: 以str1[i:n]和str2[j:m]作为子序列的最短字符串的长度
     * 2. 边界条件：
     * (1) 当i = n, j < m 时，为了把str2都包含进去，则dp[n][j] = m - j
     * (2) 当i < n, j = m 时，同上，dp[i][m] = n - i
     * 3. 状态转移方程：
     * (1) 当str1[i] == str2[j]时，可以取str1[i]组成超字符串，则dp[i][j] = dp[i+1][j+1] + 1
     * (2) 当str1[i] != str2[j]时，则两个都得取，
     * 则dp[i][j] = min{取str1[i]和str2[j:m], 取str2[j]和str1[i:n]} + 1 = min{dp[i+1][j], dp[i][j+1]} + 1
     * 4. 构造字符串: 采用双指针t1, t2
     * (1)当t1 = n || t2 = m，则需要把另一个字符串直接append到后面
     * (2)当str1[t1] == str2[t2]时，只需要取其中一个即可，并且t1++, t2++
     * (3)当str1[t1] != str2[t2]时，那就要看在dp时取得是哪个：
     * 【1】 如果取的是str1[t1]，即dp[t1][t2] = dp[t1+1][j] + 1，则t1++
     * 【2】 否则是t2++
     *
     * @param str1
     * @param str2
     * @return
     */
    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        // 边界条件
        for (int i = 0; i < n; i++) {
            dp[i][m] = n - i;
        }
        for (int j = 0; j < m; j++) {
            dp[n][j] = m - j;
        }
        for (int i = n - 1; i > -1; i--) {
            for (int j = m - 1; j > -1; j--) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
                }
            }
        }
        int t1 = 0, t2 = 0;
        StringBuilder ans = new StringBuilder();
        while (t1 < n && t2 < m) {
            if (str1.charAt(t1) == str2.charAt(t2)) {
                ans.append(str1.charAt(t1));
                t1++;
                t2++;
            } else if (dp[t1][t2] == dp[t1+1][t2] + 1) {
                ans.append(str1.charAt(t1));
                t1++;
            } else {
                ans.append(str2.charAt(t2));
                t2++;
            }
        }
        if (t1 < n) {
            ans.append(str1.substring(t1));
        }
        if (t2 < m) {
            ans.append(str2.substring(t2));
        }
        return ans.toString();
    }
}
