package leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex122 {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        Ex122 ex122 = new Ex122();
        System.out.println(ex122.maxProfit(prices));
    }
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // dp，到第i手上有股票和没有股票的获利
        int have = -prices[0], notHave = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = have;
            have = Math.max(notHave - prices[i], have);
            notHave = Math.max(tmp + prices[i], notHave);
        }
        return Math.max(have, notHave);
    }

    // 贪心，获取到所有的"上坡"
    public int maxProfix2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += (prices[i] - prices[i - 1]);
            }
        }
        return res;
    }
}
