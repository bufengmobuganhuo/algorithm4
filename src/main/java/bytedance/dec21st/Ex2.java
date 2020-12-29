package bytedance.dec21st;

/**
 * @author yuzhang
 * @date 2020/12/21 上午9:09
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.numRollsToTarget(3, 6, 7));
    }

    private static final int MOD = 1000000007;

    /**
     * 1. dp[i][j]:取i个骰子，要达到j时，可能的结果数
     * @param d
     * @param f
     * @param target
     * @return
     */
    public int numRollsToTarget(int d, int f, int target) {
        int[][] dp = new int[d + 1][target + 1];
        // 初始条件
        for (int i = 1; i < target + 1 && i < f + 1; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i < d + 1; i++) {
            for (int j = i; j < target + 1; j++) {
                // 第i个骰子的值有多种情况
                for (int k = 1; j - k > 0 && k <= f; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                }
            }
        }
        return dp[d][target];
    }

}
