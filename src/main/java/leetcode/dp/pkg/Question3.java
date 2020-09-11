package leetcode.dp.pkg;

/**
 * @author yuzhang
 * @date 2020/9/5 6:50 下午
 * TODO
 */
public class Question3 {
    public static void main(String[] args) {
        int[] values = {1000};
        int[] weights = {1};
        int[] pick = {1};
        System.out.println(solution2(weights, values, pick, 14));
    }

    /**
     * 二进制思想的解法
     * @param weights
     * @param values
     * @param pick
     * @param cap
     * @return
     */
    public static int solution2(int[] weights, int[] values, int[] pick, int cap) {
        if (weights == null || weights.length == 0) {
            return 0;
        }
        int prdNum = weights.length;
        int[] dp = new int[cap + 1];
        for (int i = 0; i < prdNum; i++) {
            // 物品i能被选择的最大次数
            int pickNum = Math.min(pick[i], cap / weights[i]);
            // k = k * 2，变化过程为：1，2，4，6
            for (int k = 1; pickNum > 0; k <<= 1) {
                // 如果剩余次数<k,则取小值，否则超过能选择的次数
                if (k > pickNum) {
                    k = pickNum;
                }
                // 去除掉已经选过的次数
                pickNum -= k;
                for (int j = cap; j >= weights[i] * k; j--) {
                    dp[j] = Math.max(dp[j], dp[j - k * weights[i]] + k * values[i]);
                }
            }
        }
        return dp[cap];
    }

    /**
     * 基本解法
     *
     * @param weights
     * @param values
     * @param pick    能选择的次数
     * @param cap
     * @return
     */
    public static int solution1(int[] weights, int[] values, int[] pick, int cap) {
        if (weights == null || weights.length == 0) {
            return 0;
        }
        int[] dp = new int[cap + 1];
        for (int i = 1; i < weights.length + 1; i++) {
            for (int j = cap; j >= 1; j--) {
                int k = 0;
                // 选择多次，直到不满足条件为止
                while (k <= pick[i - 1] && j - k * weights[i - 1] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - k * weights[i - 1]] + k * values[i - 1]);
                    k++;
                }
            }
        }
        return dp[cap];
    }
}
