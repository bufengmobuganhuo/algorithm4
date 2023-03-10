package leetcode.array;

/**
 * @author yu zhang
 */
public class Ex2373 {
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] matrix = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                int row = i + 1, col = j + 1;
                int max = Math.max(grid[row - 1][col - 1], grid[row - 1][col]);
                max = Math.max(max, grid[row - 1][col + 1]);
                max = Math.max(max, grid[row][col - 1]);
                max = Math.max(max, grid[row][col + 1]);
                max = Math.max(max, grid[row][col]);
                max = Math.max(max, grid[row + 1][col - 1]);
                max = Math.max(max, grid[row + 1][col]);
                max = Math.max(max, grid[row + 1][col + 1]);
                matrix[i][j] = max;
            }
        }
        return matrix;
    }
}
