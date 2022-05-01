package leetcode.math;

/**
 * @author yu zhang
 */
public class Ex883 {
    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int xyArea = 0, xzArea = 0, yzArea = 0;
        for (int i = 0; i < n; i++) {
            int yHeight = 0, xHeight = 0;
            for (int j = 0; j < n; j++) {
                xyArea += grid[i][j] > 0 ? 1 : 0;
                yHeight = Math.max(yHeight, grid[i][j]);
                xHeight = Math.max(xHeight, grid[j][i]);
            }
            xzArea += xHeight;
            yzArea += yHeight;
        }
        return xyArea + xzArea + yzArea;
    }
}
