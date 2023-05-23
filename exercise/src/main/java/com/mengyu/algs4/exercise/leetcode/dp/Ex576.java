package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yuzhang
 * @date 2020/9/20 10:32 上午
 * 题解：https://leetcode-cn.com/problems/out-of-boundary-paths/solution/zhuang-tai-ji-du-shi-zhuang-tai-ji-by-christmas_wa/
 */
public class Ex576 {
    public static void main(String[] args) {
        Ex576 ex576 = new Ex576();
        System.out.println(ex576.findPaths2(1, 3, 3, 0, 1));
    }

    /**
     * 优化:由于dp[x][y][k]只依赖于第k-1步的结果，则可以只使用两个二维数组来分别保存k-1步的结果和第k步的结果
     * @param m
     * @param n
     * @param N
     * @param i
     * @param j
     * @return
     */
    public int findPaths2(int m, int n, int N, int i, int j) {
        long[][] dp = new long[m+2][n+2];
        long[][] lastDp = new long[m+2][n+2];
        int mod = 1000000007;
        long ans = 0;
        // 初始条件
        for (int k = 0; k < m + 2; k++) {
            lastDp[k][0]=1;
            lastDp[k][n+1]=1;
        }
        for (int k = 0; k < n + 2; k++) {
            lastDp[0][k]=1;
            lastDp[m+1][k]=1;
        }
        for (int k = 1; k < N+1; k++) {
            for (int row = 1; row < m + 1; row++) {
                for (int col = 1; col < n + 1; col++) {
                    dp[row][col]=(lastDp[row-1][col]+
                            lastDp[row+1][col]+
                            lastDp[row][col-1]+
                            lastDp[row][col+1])%mod;
                }
            }
            ans=(ans+dp[i+1][j+1])%mod;
            lastDp = dp;
            dp = new long[m+2][n+2];
        }
        return (int) ans;
    }
    /**
     * 将思想反过来，求从外界出发，到达(i,j)的路径总数
     * 1. dp[x][y][k]:从"外界"（指从矩阵的外围）出发，当走第k步时，到达(x,y)时的路径总数
     * 2. dp[x][y][k]=dp[x-1][y][k-1]+dp[x+1][y][k-1]+dp[x][y-1][k-1]+dp[x][y+1][k-1]
     * 3. 求到达(i,j)的总的路径数，即(走1步的路径总数+走2步的路径总数+。。。+走N步的路径总数)
     * @param m
     * @param n
     * @param N
     * @param i
     * @param j
     * @return
     */
    public int findPaths(int m, int n, int N, int i, int j) {
        long[][][] dp = new long[m + 2][n + 2][N + 1];
        int mod = 1000000007;
        if (N == 0) {
            return 0;
        }
        // 外界的边界条件：走第0步时，到达外围的路径总数
        for (int k = 0; k < m+2; k++) {
            // 初试时在外围，所以有一个路径
            dp[k][0][0]=1;
            dp[k][n+1][0]=1;
        }
        for (int k = 0; k < n+2; k++) {
            dp[0][k][0]=1;
            dp[m+1][k][0]=1;
        }
        for (int k = 1; k < N + 1; k++) {
            for (int row = 1; row < m + 1; row++) {
                for (int col = 1; col < n+1; col++) {
                    dp[row][col][k]=(dp[row-1][col][k-1]
                            +dp[row+1][col][k-1]
                            +dp[row][col-1][k-1]
                            +dp[row][col+1][k-1])%mod;
                }
            }
        }
        long ans = 0;
        for (int k = 1; k < N + 1; k++) {
            ans=(ans+dp[i+1][j+1][k])%mod;
        }
        return (int) ans;
    }
}
