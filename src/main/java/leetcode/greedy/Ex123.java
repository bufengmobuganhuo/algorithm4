package leetcode.greedy;

/**
 * @author yuzhang
 * @date 2021/1/19 上午9:38
 * TODO
 */
public class Ex123 {
    /**
     * 1. 当一个交易日结束时，在他和他之前的交易日可能会执行如下五种操作：
     * （1）从未交易过
     * 获得的利润永远为0
     * （2）第一次交易，并且进行了一次买入，记为buy1，前一天的记为buy1'，下同
     * buy1 = max(当前进行买入操作；今天未操作，之前操作了) = max(0-prices[i], buy1')
     * dp[i][1] = max(dp[i-1])
     * （3）第一次交易，并且进行了一次卖出，记为sell1
     * sell1 = max(当前进行了卖出操作；今天未操作，之前操作了) = max(buy1'+prices[i], sell1')
     * （4）第二次交易，并且进行了一次买入，记为buy2
     * buy2 = max(当前进行了买入操作；今天未操作，之前操作了) = max(sell1'-prices[i], buy2')
     * （5）第二次交易，并且进行了一次卖出，记为sell2
     * sell2 = max(当前进行了卖出操作；今天未操作，之前操作了) = max(buy2'+prices[i], sell2')
     * 2. 有如下一个共识：当天进行买入卖出操作，获得的利润为0，不会对最终利润造成影响，此时可以修改状态转移方程为
     * buy1 = max(-prices[i], buy1)
     * sell1 = max(buy1+prices[i], sell1)
     * ...
     * 对于sell1, 他多考虑了"当天买入的情况（对于buy1+prices[i]，根据上面的状态转移方程，考虑了当天买入的情况）"，
     * 但是他也考虑了"当天卖出的情况（对于sell1, 根据上面的状态转移方程，考虑了当天卖出的情况）"
     * 3. 基于2的分析，那么初始条件可以确定：
     * 第0天buy1:只能是当天买入，则获利=-prices[0]
     * 第0天sell1:只能是当天买入，当天卖出，获利=0
     * 第0天buy2：只能是当天买入，当天卖出，然后再买入，获利=-prices[0]
     * 第0天sell2：只能是天天买入，当天卖出；再买入，再卖出，获利=0
     * 4. 因为"不超过两笔交易"，则最终的结果可能在0，sell1，sell2中获得
     * 但是在边界条件中，sell1和sell2都设置成了0，后面对sell1，sell2的更新也是+一个数，所以这两个值肯定>=0
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int buy1, buy2, sell1 = 0, sell2 = 0;
        // 初始条件
        buy1 = -prices[0];
        buy2 = -prices[0];
        for (int i = 1; i < len; i++) {
            buy1 = Math.max(-prices[i], buy1);
            sell1 = Math.max(buy1 + prices[i], sell1);
            buy2 = Math.max(sell1 - prices[i], buy2);
            sell2 = Math.max(buy2 + prices[i], sell2);
        }
        return Math.max(sell1, sell2);
    }
}
