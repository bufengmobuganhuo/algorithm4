package leetcode.greedy;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/1/19 上午11:11
 * TODO
 */
public class Ex188 {

    /**
     * 仿照Ex123，只是2次变成了k次
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        // 将买卖分成两个二维数组进行处理，则在prices中买卖各占一半
        k = Math.min(k, len / 2);
        // sells[i][j]：当前是第j次交易，并且当前手上不持有股票获得的最大收益
        int[][] sells = new int[len][k + 1];
        // buys[i][j]：当前是第j次交易，并且当前手上持有一只股票获得的最大收益
        int[][] buys = new int[len][k + 1];
        // 初始条件：第0天只能进行买入，无法卖出，其他都是非法状态，无法操作
        buys[0][0] = -prices[0];
        sells[0][0] = 0;
        for (int i = 1; i < k + 1; i++) {
            buys[0][i] = Integer.MIN_VALUE / 2;
            sells[0][i] = Integer.MIN_VALUE / 2;
        }
        for (int i = 1; i < len; i++) {
            // 对于第0次交易，max(当天不操作，上一天就持有了一只股票；上一天不持有股票，然后当天买入)
            buys[i][0] = Math.max(buys[i - 1][0], sells[i - 1][0] - prices[i]);
            for (int j = 1; j < k + 1; j++) {
                // 对于第j次交易，max(当天不操作，上一天就持有了一直股票；上一天不持有股票，然后当天买入)
                buys[i][j] = Math.max(buys[i - 1][j], sells[i - 1][j] - prices[i]);
                // 对于第j次交易，max(当天不操作，上一天也不持有股票；上一天的第j-1次交易持有了一个股票，然后在第j次交易的当天卖出)
                sells[i][j] = Math.max(sells[i - 1][j], buys[i - 1][j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sells[len - 1]).max().getAsInt();
    }

    /**
     * 使用一维数组代替
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit2(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        // 将买卖分成两个二维数组进行处理，则在prices中买卖各占一半
        k = Math.min(k, len / 2);
        int[] sells = new int[k + 1];
        int[] buys = new int[k + 1];
        sells[0] = 0;
        buys[0] = -prices[0];
        for (int i = 1; i < k + 1; i++) {
            sells[i] = Integer.MIN_VALUE / 2;
            buys[i] = Integer.MIN_VALUE / 2;
        }
        for (int i = 1; i < len; i++) {
            buys[0] = Math.max(buys[0], sells[0]-prices[i]);
            for (int j = 1; j < k + 1; j++) {
                buys[j] = Math.max(buys[j], sells[j] - prices[i]);
                sells[j] = Math.max(sells[j], buys[j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sells).max().getAsInt();
    }
}
