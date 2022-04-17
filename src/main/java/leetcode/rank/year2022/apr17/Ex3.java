package leetcode.rank.year2022.apr17;

/**
 * @author yuzhang
 * @date 2022/4/17 11:11 AM
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[][] grid = {{899,727,165,249,531,300,542,890},{981,587,565,943,875,498,582,672},{106,902,524,725,699,778,365,220}};
        System.out.println(new Ex3().maxTrailingZeros(grid));
    }

    private int[][] grid;

    private int m, n;

    public int maxTrailingZeros(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        // 找到每个单元格数中，包含的5, 2的个数
        int[][] num5 = count(5);
        int[][] num2 = count(2);
        // 从4个方向到达grid[i][j]的5，2的个数
        int[][][] count5 = count(num5);
        int[][][] count2 = count(num2);
        int ans = 0;
        // 计算到达每个点，能获取的0的个数
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = search(i, j, count2, num2, count5, num5);
                ans = Math.max(count, ans);
            }
        }
        return ans;
    }

    private int search(int i, int j, int[][][] count2, int[][] countNum2, int[][][] count5, int[][] countNum5) {
        int max = 0;
        for (int k = 0; k < 4; k++) {
            /**
             * (0, 1)：
             *   ｜
             *   p
             *   ｜
             * (0, 2):
             *    ｜
             * —— p
             * (0, 3):
             *    ｜
             *    p ——
             * (1, 0): 同(0, 1)
             * 其他同理
             */
            for (int l = k + 1; l < 4; l++) {
                // 加过一次本身了，去掉
                int num2 = count2[i][j][k] + count2[i][j][l] - countNum2[i][j];
                int num5 = count5[i][j][k] + count5[i][j][l] - countNum5[i][j];
                // 2和5最小的个数，就是到达grid[i][j]时的0的个数
                max = Math.max(max, Math.min(num2, num5));
            }
        }
        return max;
    }

    private int[][][] count(int[][] countNum) {
        int[][][] count = new int[m][n][4];
        // 0: 一直从上到下，列从上到下, 2: 一直从左到右，行从左到右
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    count[i][j][0] = countNum[i][j];
                } else {
                    count[i][j][0] = countNum[i][j] + count[i - 1][j][0];
                }
                if (j == 0) {
                    count[i][j][2] = countNum[i][j];
                } else {
                    count[i][j][2] = countNum[i][j] + count[i][j - 1][2];
                }
            }
        }
        // 1: 一直从下到上，列从下到上, 3: 一直从右到左，行从右到左
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j > -1; j--) {
                if (i == m - 1) {
                    count[i][j][1] = countNum[i][j];
                } else {
                    count[i][j][1] = countNum[i][j] + count[i + 1][j][1];
                }
                if (j == n - 1) {
                    count[i][j][3] = countNum[i][j];
                } else {
                    count[i][j][3] = countNum[i][j] + count[i][j + 1][3];
                }
            }
        }
        return count;
    }

    private int[][] count(int num) {
        int[][] count = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                int x = grid[i][j];
                while (x > 0 && x % num == 0) {
                    cnt++;
                    x /= num;
                }
                count[i][j] = cnt;
            }
        }
        return count;
    }
}
