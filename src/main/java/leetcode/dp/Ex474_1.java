package leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/3/9 上午8:56
 * TODO
 */
public class Ex474_1 {
    public int findMaxForm(String[] strs, int m, int n) {
        int prdNum = strs.length;
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < prdNum; i++) {
            int[] cost = cost(strs[i]);
            for (int j = m; j >= cost[0]; j--) {
                for (int k = n; k >= cost[1]; k--) {
                    dp[j][k] = Math.max(dp[j][k],dp[j-cost[0]][k-cost[1]]+1);
                }
            }
        }
        return dp[m][n];
    }

    private int[] cost(String str){
        int zeros = 0, ones = 0;
        for (char chr : str.toCharArray()) {
            if (chr=='0'){
                zeros++;
            }else {
                ones++;
            }
        }
        return new int[]{zeros,ones};
    }
}
