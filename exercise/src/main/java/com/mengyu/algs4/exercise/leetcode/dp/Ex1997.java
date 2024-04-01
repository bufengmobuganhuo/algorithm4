package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1997 {

    final long MOD = 1_000_000_007;

    /**
     * https://leetcode.cn/problems/first-day-where-you-have-been-in-all-the-rooms/solutions/979221/qian-zhui-he-you-hua-dp-by-endlesscheng-j10b
     *
     * 1. 当第一次访问到房间i时，则[0, i-1]一定已经访问了偶数次，不然到不了下一个
     * 2. 当第一次访问到房间i时，下一次只能到达nextVisit[i] = j <= i。在这个时候[j, i - 1]范围内的房间都处于偶数次访问。
     * 如果我们要从j再回到i，那至少要访问一遍[j, i - 1]，这个时候它们的访问次数变成了奇数，所以这些房间也需要再回访
     * 3. 从「访问到房间i且次数为奇数」到「访问到房间i且次数为偶数」是一个完整的周期，适合作为状态，
     * 即定义 f[i] 表示从「访问到房间i且次数为奇数」到「访问到房间i且次数为偶数」所需要的天数
     * 4. 要想"访问到房间i且次数为偶数"，[j, i - 1]的房间都需要回访，并且房间i被访问了2次，则f[i] = 2 + sum{f[k]}, j<= k < i
     * 5. 使用前缀和优化，定义s[0] = 0, s[i+1] = sum{f[k]} 0<= k <= i，则f[i] = 2 + s[i] - s[j]
     * 6. 同时f[i] = s[i+1] - s[i]，则s[i+1] - s[i] = 2 + s[i] - s[j] --> s[i+1] = 2s[i] - s[j] + 2
     * 7. 如果要求访问最后一个房间的天数，则ans = f[0] + f[1] + ... + f[n - 2] + 1 = s[n - 1] + 1
     * 8. 但是我们要的是从0开始的编号，所以天数-1，即ans = s[n - 1] + 1 - 1 = s[n-1]
     * */
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        long[] dp = new long[n];
        for (int i = 0; i < n - 1; i++) {
            int j = nextVisit[i];
            // + MOD，避免出现负数
            dp[i + 1] = (2 * dp[i] - dp[j] + 2 + MOD) % MOD;
        }
        return (int) dp[n - 1];
    }
}
