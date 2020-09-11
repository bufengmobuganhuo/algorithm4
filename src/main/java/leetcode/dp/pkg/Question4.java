package leetcode.dp.pkg;

/**
 * @author yuzhang
 * @date 2020/9/8 9:35 上午
 * TODO
 */
public class Question4 {
    public static void main(String[] args) {
        int[] values = {7000, 6000, 1500};
        int[] weights = {4, 3, 1};
        int[] pick = {1, 2, 0};
        System.out.println(solution(weights, values, pick, 5));
    }

    /**
     * @param weight
     * @param value
     * @param pick : 0代表可以取无限次
     * @param cap
     * @return
     */
    public static int solution(int[] weight, int[] value, int[] pick, int cap) {
        if (weight == null || weight.length == 0) {
            return 0;
        }
        int prdNum = weight.length;
        int[] dp = new int[cap + 1];
        for (int i = 0; i < prdNum; i++) {
            // 可以取无限次的情况
            if (pick[i] == 0) {
                for (int j = weight[i]; j < cap + 1; j++) {
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            } else {
                // 最多能选取多少个物品
                int pickNum = Math.min(cap / weight[i], pick[i]);
                for (int k = 1; pickNum > 0; k <<= 1) {
                    if (k > pickNum) {
                        k = pickNum;
                    }
                    pickNum -= k;
                    for (int j = cap; j >= k * weight[i]; j--) {
                        dp[j] = Math.max(dp[j], dp[j - k * weight[i]] + k * value[i]);
                    }
                }
            }
        }
        return dp[cap];
    }
}
