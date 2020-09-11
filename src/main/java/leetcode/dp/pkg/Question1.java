package leetcode.dp.pkg;

/**
 * @author yuzhang
 * @date 2020/9/3 11:22 上午
 * 背包九讲 之 第一讲：基本的背包问题
 */
public class Question1 {
    public static void main(String[] args) {
        int[] values = {3000, 2000, 5000};
        int[] weights = {4, 2, 1};
        System.out.println(solution1(weights, values, 4));
        System.out.println(solution2(weights, values, 4));
    }

    /**
     * 空间优化：使用一维数组实现
     *
     * @param weights
     * @param values
     * @param cap
     * @return
     */
    public static int solution2(int[] weights, int[] values, int cap) {
        if (weights == null || weights.length == 0) {
            return 0;
        }
        int[] dp = new int[cap + 1];
        for (int i = 1; i < weights.length + 1; i++) {
            // 容量递减，那么就可以保存住F[i-1,v-Ci]
            for (int j = cap; j >= weights[i-1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
            }
        }
        return dp[cap];
    }

    /**
     * 基础解法:使用二维数组
     *
     * @param weights 各个物品的重量
     * @param values  各个物品的价值
     * @param cap     背包的容量
     * @return
     */
    public static int solution1(int[] weights, int[] values, int cap) {
        if (weights == null || weights.length == 0) {
            return 0;
        }
        // 基础解法使用二维数组实现
        int[][] dp = new int[weights.length + 1][cap + 1];
        for (int i = 1; i < weights.length + 1; i++) {
            for (int j = 1; j < cap + 1; j++) {
                // 当背包的容量为j，并且可以放下第i件商品时才有比较的必要
                if (j - weights[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                    // 如果背包的容量为j，但是放不下第i件商品，则无法放
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[weights.length][cap];
    }
}
