package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @date 2023/10/4 17:45
 * TODO
 */
public class Ex983 {

    private int[] days;

    private int[] costs;

    private Integer[] memo;

    private static int[] durations = {1, 7, 30};

    /**
     * dp[i]：完成从days[i]开始的履行的最小花费
     * dp[i]这一天肯定是要买票的，因为它是开始，那选择就是买哪种票
     * 1. 如果买为期一天的：
     * total cost = costs[0] + dp[days[i]开始后，第一个需要买票的花费所对应的days的下标]
     * 比如需要旅行的天数是1, 4, 9。那如果在第1天买了票，这个票有效期1天，则第4天还需再买，dp[0] = costs[0] + dp[1]
     * 2. 如果买为期7天的：
     * 同样的例子下，第四天不需要买票，则dp[0] = costs[1] + dp[2]
     * 3. 如果买为期30天的
     * 都不需要买票，则dp[0] = costs[2]
     *
     * 三者取最小值
     */
    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        memo = new Integer[days.length];
        return dp(0);
    }

    // 完成从days[i]开始的全年旅行需要的最小花费
    private int dp(int i) {
        if (i >= days.length) {
            return 0;
        }
        if (memo[i] != null) {
            return memo[i];
        }
        memo[i] = Integer.MAX_VALUE;
        int j = i;
        for (int k = 0; k < 3; k++) {
            int duration = durations[k];
            // 找到days[j]中，如果在第days[i]买了票，需要在days[j]失效的最小j
            // days[j] < duration + days[i]: days[i]买了票后，duration + days[i]不需要再买票
            while (j < days.length && days[j] < duration + days[i]) {
                j++;
            }
            memo[i] = Math.min(memo[i], dp(j) + costs[k]);
        }
        return memo[i];
    }
}
