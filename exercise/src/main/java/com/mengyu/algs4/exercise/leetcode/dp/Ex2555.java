package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex2555 {
    /**
     * 两个线段不重叠时的收益最大
     * 1. 对于第2个线段，如果我们确定了右端点prizePositions[right2]，则其左端点prizePositions[left2]一定满足：
     * prizePositions[right2] - prizePositions[left2] <= k，此时这个线段覆盖的奖品数量=right2-left2+1
     * 2. 对于第1个线段，它的右端点一定在第二个线段左端点的左边，即prizePositions[right1] < prizePositions[left2]
     * 3. dp[right]：表示位置不超过prizePositions[right]的线段能覆盖的奖品最大数量
     * (1) 如果不选择prizePositions[right]处的奖品，则dp[right] = dp[right - 1]
     * (2) 如果选择prizePositions[right]处的奖品，则需要以prizePositions[right]为右端点，
     * 直到prizePositions[left]满足prizePositions[right] - 满足prizePositions[left] <= k，
     * 此时获取到的奖品数量为dp[right] = right -left + 1
     * 4. 有了dp后，我们可以依次枚举第2个线段的右端点，则第2个线段的奖品数量=right - left + 1。第1个线段的奖品数量=dp[left-1]
     * ans = right - left + 1 + dp[left - 1]
     */
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        // 为了防止数组越界，dp[right + 1]表示的线段右端点为prizePositions[right]，
        // 则假定右端点在prizePositions中的索引为i，则对应的dp中的索引为i-1
        int[] dp = new int[n + 1];
        int ans = 0;
        // 枚举第2个线段的右端点
        for (int left2 = 0, right2 = 0; right2 < n; right2++) {
            // 线段太长，不满足条件
            while (prizePositions[right2] - prizePositions[left2] > k) {
                left2++;
            }
            ans = Math.max(ans, right2 - left2 + 1 + dp[left2]);
            dp[right2 + 1] = Math.max(right2 - left2 + 1, dp[right2]);
        }
        return ans;
    }

    public int maximizeWin2(int[] prizePositions, int k) {
        int n = prizePositions.length;
        int[] dp = new int[n];
        int ans = 0;
        // 枚举第2个线段的右端点
        for (int left2 = 0, right2 = 0; right2 < n; right2++) {
            // 线段太长，不满足条件
            while (prizePositions[right2] - prizePositions[left2] > k) {
                left2++;
            }
            ans = Math.max(ans, right2 - left2 + 1 + (left2 > 0 ? dp[left2 - 1] : 0));
            dp[right2] = Math.max(right2 - left2 + 1, right2 > 0 ? dp[right2 - 1] : 0);
        }
        return ans;
    }
}
