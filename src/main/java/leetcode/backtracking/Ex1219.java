package leetcode.backtracking;

/**
 * @author yu zhang
 */
public class Ex1219 {
    public static void main(String[] args) {
        int[][] grid = {
            {0, 6, 0},
            {5, 8, 7},
            {0, 9, 0}
        };
        Ex1219 ex1219 = new Ex1219();
        System.out.println(ex1219.getMaximumGold(grid));
    }

    private int res = 0;

    public int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                res = Math.max(res, backtracking(grid, i, j, 0));
            }
        }
        return res;
    }

    private int backtracking(int[][] grid, int i, int j, int own) {
        if (cantReach(grid, i, j)) {
            return own + grid[i][j];
        }
        int tmp = grid[i][j];
        own += tmp;
        grid[i][j] = 0;
        int max = 0;
        if (i > 0 && grid[i - 1][j] > 0) {
            max = Math.max(backtracking(grid, i - 1, j, own), max);
        }
        if (i < grid.length - 1 && grid[i + 1][j] > 0) {
            max = Math.max(backtracking(grid, i + 1, j, own), max);
        }
        if (j > 0 && grid[i][j - 1] > 0) {
            max = Math.max(backtracking(grid, i, j - 1, own), max);
        }
        if (j < grid[0].length - 1 && grid[i][j + 1] > 0) {
            max = Math.max(backtracking(grid, i, j + 1, own), max);
        }
        grid[i][j] = tmp;
        return max;
    }

    private boolean cantReach(int[][] grid, int i, int j) {
        int up = i > 0 ? grid[i - 1][j] : 0;
        int down = i < grid.length - 1 ? grid[i + 1][j] : 0;
        int left = j > 0 ? grid[i][j - 1] : 0;
        int right = j < grid[0].length - 1 ? grid[i][j + 1] : 0;
        return up == 0 && down == 0 && left == 0 && right == 0;
    }
}
