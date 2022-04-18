package leetcode.dfs;

/**
 * @author yu zhang
 */
public class Ex1020 {
    public static void main(String[] args) {
        int[][] grid = {{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
        System.out.println(new Ex1020().numEnclaves(grid));
    }

    private int[][] grid;

    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        this.grid = grid;
        int count = 0;
        // 从边界开始DFS，如果遇到1，则说明可以移动，改为0
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                dfs(i, 0, m, n);
            }
            if (grid[i][n - 1] == 1) {
                dfs(i, n - 1, m, n);
            }
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) {
                dfs(0, j, m, n);
            }
            if (grid[m - 1][j] == 1) {
                dfs(m - 1, j, m, n);
            }
        }
        // 剩下为1的就是不能移动出去的
        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                if (ints[j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, int m, int n) {
        if (i >= m || i < 0 || j >= n || j < 0 || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        dfs(i + 1, j, m, n);
        dfs(i - 1, j, m, n);
        dfs(i, j + 1, m, n);
        dfs(i, j - 1, m, n);
    }
}
