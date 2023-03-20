package leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex1605 {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length, n = colSum.length;
        int[][] grid = new int[m][n];
        int i = 0, j = 0;
        while (i < m && j < n) {
            int val = Math.min(rowSum[i], colSum[j]);
            grid[i][j] = val;
            rowSum[i] -= val;
            colSum[i] -= val;
            if (rowSum[i] == 0) {
                i++;
            }
            if (colSum[j] == 0) {
                j++;
            }
        }
        return grid;
    }
}
