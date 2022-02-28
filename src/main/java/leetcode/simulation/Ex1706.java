package leetcode.simulation;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1706 {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, -1, -1},
                {1, 1, 1, -1, -1},
                {-1, -1, -1, 1, 1},
                {1, 1, 1, 1, -1},
                {-1, -1, -1, -1, -1}
        };
        System.out.println(Arrays.toString(new Ex1706().findBall(grid)));
    }

    public int[] findBall(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] ans = new int[n];
        for (int j = 0; j < n; j++) {
            int col = j;
            for (int[] row : grid) {
                int dir = row[col];
                col += dir;
                // 到达边界，或V型
                if (col < 0 || col == n || row[col] != dir) {
                    col = -1;
                    break;
                }
            }
            ans[j] = col;
        }
        return ans;
    }
}
