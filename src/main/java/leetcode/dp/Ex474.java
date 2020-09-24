package leetcode.dp;

/**
 * @author yuzhang
 * @date 2020/9/18 9:01 上午
 * TODO
 */
public class Ex474 {
    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        Ex474 ex474 = new Ex474();
        System.out.println(ex474.findMaxForm(strs, 5, 3));
    }

    /**
     * 实际上是二维费用的背包问题
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            // 每个字符串消耗0的费用
            int zeroWeight = 0;
            // 每个字符串消耗1的费用
            int oneWeight = 0;
            // 先计算第i个字符串的费用
            for (int j = 0; j < strs[i].length(); j++) {
                char chr = strs[i].charAt(j);
                if (chr == '0') {
                    zeroWeight++;
                } else {
                    oneWeight++;
                }
            }
            for (int j = m; j >= zeroWeight; j--) {
                for (int k = n; k >= oneWeight; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeroWeight][k - oneWeight] + 1);
                }
            }
        }
        return dp[m][n];
    }

}
