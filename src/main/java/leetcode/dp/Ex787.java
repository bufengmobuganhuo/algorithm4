package leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex787 {
    public static void main(String[] args) {
        int[][] edges = {
            {0, 1, 1},
            {0, 2, 5},
            {1, 2, 1},
            {2, 3, 1},
        };
        Ex787 ex787_ = new Ex787();
        System.out.println(ex787_.findCheapestPrice(4, edges, 0, 3, 1));
    }



    /**
     * 1. dp[t][i]: 经过t次中转到达i所花费的最小费用
     * 2. 状态转移方程：
     * dp[t][i] = min{dp[t-1][j] + cost(j,i)}  -> 经过t-1次中转到达j的最小费用 + 从j到达i的费用
     * 3. 初始条件：dp[0][src] = 0
     * 4. 最终的结果：min{dp[j][dst]}
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 防止溢出
        int INF = 10000 * 101 + 1;
        // 中转0次：src -> src，中转第k+1次：vertex -> dst（总共中转了k站）
        int[][] dp = new int[k + 2][n];
        for (int i = 0; i < k + 2; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][src] = 0;
        for (int t = 1; t < k + 2; t++) {
            for (int[] flight : flights) {
                int from = flight[0], to = flight[1], price = flight[2];
                dp[t][to] = Math.min(dp[t][to], dp[t - 1][from] + price);
            }
        }
        int ans = INF;
        for (int i = 1; i < k + 2; i++) {
            ans = Math.min(ans, dp[i][dst]);
        }
        return ans == INF ? -1 : ans;
    }
}
