package leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex309 {
    /**
     * 1. dp[i]:第i天结束时，能获取的最大利润，可知有三种状态：
     * (1)dp[i][0]：持有股票
     * (2)dp[i][1]: 不持有股票，不在冷冻期
     * (3)dp[i][2]: 不持有股票，在冷冻期
     * 2. 状态转移方程:
     * (1)dp[i][0] = max{前一天就持有股票；前一天不持有股票，不冷冻，当天买入}=max{dp[i-1][0],dp[i-1][1]-prices[i]}
     * (2)dp[i][1] = max{前一天不持有股票，不再冷冻期；前一天不持有股票，在冷冻期}=max{dp[i-1][1],dp[i-1][2]}：今天没有进行任何操作
     * (3)dp[i][2] = max{前一天持有} = max{dp[i-1][0]+prices[i]}:今天做了卖出操作
     * 最后结果max{dp[n-1][1],dp[i-1][2]}：最后一天持有股票没意义
     * 3. 初始条件；第0天只能买入：dp[0][0] = -prices[0]
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int[][] dp = new int[len][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][2] = dp[i - 1][0] + prices[i];
        }
        return Math.max(dp[len - 1][1], dp[len - 1][2]);
    }

    /**
     * 空间优化
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int[] dp = new int[3];
        dp[0] = -prices[0];
        for (int i = 1; i < len; i++) {
            int state0 = dp[0], state1 = dp[1], state2 = dp[2];
            dp[0] = Math.max(state0, state1 - prices[i]);
            dp[1] = Math.max(state1, state2);
            dp[2] = state0 + prices[i];
        }
        return Math.max(dp[1], dp[2]);
    }
}
