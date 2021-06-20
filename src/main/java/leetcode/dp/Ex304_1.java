package leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex304_1 {
    public static void main(String[] args) {
        int[][] matrix = {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };
        Ex304_1 ex3041 = new Ex304_1(matrix);
        System.out.println(ex3041.sumRegion(1, 1, 2, 2));
    }

    /**
     *
     */
    private int[][] dp;

    public Ex304_1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 加哨兵
        this.dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = dp[i][j + 1] + dp[i + 1][j] - dp[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}
