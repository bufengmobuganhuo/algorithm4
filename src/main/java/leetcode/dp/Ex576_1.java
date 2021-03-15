package leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/2/22 上午9:20
 * TODO
 */
public class Ex576_1 {
    public static void main(String[] args) {
        Ex576_1 ex576_1 = new Ex576_1();
        System.out.println(ex576_1.findPaths(2,2,2,0,0));
    }
    public int findPaths(int m, int n, int N, int i, int j) {
        if (N == 0) {
            return 0;
        }
        long[][][] dp = new long[m + 2][n + 2][N + 1];
        int mod = 1000000007;
        // 边界条件：从外围走0步到达外围的路经数=1
        for (int k = 0; k < n + 2; k++) {
            dp[0][k][0] = 1;
            dp[m + 1][k][0] = 1;
        }
        for (int k = 0; k < m + 2; k++) {
            dp[k][n+1][0] = 1;
            dp[k][n+1][0] = 1;
        }
        long ans = 0;
        for (int k = 1; k < N + 1; k++) {
            for (int row = 1; row < m + 1; row++) {
                for (int col = 1; col < n + 1; col++) {
                    dp[row][col][k] = (dp[row-1][col][k-1]
                            +dp[row+1][col][k-1]
                            +dp[row][col-1][k-1]
                            +dp[row][col+1][k-1])%mod;
                }
            }
            ans = (ans + dp[i+1][j+1][k])%mod;
        }
        return (int) ans;
    }
}
