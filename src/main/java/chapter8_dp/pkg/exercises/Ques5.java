package chapter8_dp.pkg.exercises;

/**
 * @author yuzhang
 * @date 2021/3/8 上午11:05
 * 二维费用问题
 */
public class Ques5 {
    private static int solution(int[] weights1, int[] weights2, int[] values, int cap1, int cap2) {
        int prdNum = values.length;
        int[][] dp = new int[cap1 + 1][cap2 + 1];
        for (int i = 0; i < prdNum; i++) {
            for (int j = cap1; j >= weights1[i]; j--) {
                for (int k = cap2; k >= weights2[i]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - weights1[i]][k - weights2[i]] + values[i]);
                }
            }
        }
        return dp[cap1][cap2];
    }
}
