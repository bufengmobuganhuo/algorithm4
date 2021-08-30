package leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/6/7 上午7:57
 * TODO
 */
public class Ex221 {
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        int m = matrix.length, n = matrix[0].length;
        // dp[i][j]:以matrix[i][j]作为右下角元素时的最大正方形边长
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前方块为0，则不能组成正方形
                if (matrix[i][j] == '0') {
                    continue;
                }
                // 边界条件
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    // https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/solution/tong-ji-quan-wei-1-de-zheng-fang-xing-zi-ju-zhen-2/
                    // dp[i][j]的值取决于他左边，上边，左上方块的值
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
                maxSide = Math.max(dp[i][j], maxSide);
            }
        }
        return maxSide * maxSide;
    }
}
