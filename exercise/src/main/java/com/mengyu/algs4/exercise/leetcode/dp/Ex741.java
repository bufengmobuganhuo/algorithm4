package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex741 {
    /**
     * 1. 一来一回可以等效为两个人从相同地点，以相同速度同时出发，但是需要保证两个人不能同时拿同一个单元格内的樱桃
     * 2. 在同一时刻，两个人走的步数是相同的，假定为k，假设A向下走的步数为y1，则他向右走的步数为k-y1，并且我们可以定位到他目前所在的位置为(y1, k-y1)，
     * 另一人同理。并且如果y1=y2，则可以判断出他们到达了同一个格子
     * 3. 状态为dp[k][y1][y2]：当总共走了k步，并且A向右走了y1步，B向右走了y2步时，能拿到的樱桃数
     * 4. 初始情况是：
     * (1) 当grid[y1][k-y1]或grid[y2][k-y2]是荆棘时，dp[k][y1][y2]=负无穷。
     * (2) 当k < y1或k < y2时，dp[k][y1][y2]=负无穷
     * (3) 因为二人走的路径总会有高有低，我们规定A在上面，B在下面，即y1 <= y2
     * 5. dp[k][y1][y2]可能由如下几种情况得到:
     * (1) 二人都向右走：从dp[k-1][y1][y2]转移过来
     * (2) A向右，B向下：从dp[k-1][y1][y2-1]转移过来
     * (3) A向下，B向右：从dp[k-1][y1-1][y2]转移过来
     * (4) 都向下：dp[k-1][y1-1][y2-1]转移过来
     * 这4种情况取最大值，并且 + grid[y1][k-y1] + grid[y2][k-y2]即为dp[k][y1][y2]
     * 6. 2个人的总步数=2*n-2，这是k的最大值.最终的结果=dp[2n-2][n-1][n-1]
     */
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[2 * n - 1][n][n];
        // 初始认为都是荆棘
        for (int i = 0; i < 2 * n - 1; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        // 还没开始走
        dp[0][0][0] = grid[0][0];
        for (int k = 1; k < 2 * n - 1; k++) {
            // y1是纵坐标，最大不能超过n-1，如果已经走了k步，那他的初始值应该就是k-(n-1),同时y1也不能<0
            // y1是纵坐标，最大不能超过n-1，并且因为已经规定了走了k步，则也不能超过k
            for (int y1 = Math.max(0, k - (n - 1)); y1 <= Math.min(k, n - 1); y1++) {
                int x1 = k - y1;
                // A无法走到这个单元格，则整个路径就是不合法的
                if (grid[y1][x1] == -1) {
                    continue;
                }
                for (int y2 = y1; y2 <= Math.min(k, n - 1); y2++) {
                    int x2 = k - y2;
                    // 同样的，整个路径不合法
                    if (grid[y2][x2] == -1) {
                        continue;
                    }
                    // 都向右走
                    int max = dp[k - 1][y1][y2];
                    // A向右，B向下
                    if (y2 > 0) {
                        max = Math.max(max, dp[k - 1][y1][y2 - 1]);
                    }
                    // A向下，B向右
                    if (y1 > 0) {
                        max = Math.max(max, dp[k - 1][y1 - 1][y2]);
                    }
                    // 都向下
                    if (y1 > 0 && y2 > 0) {
                        max = Math.max(max, dp[k - 1][y1 - 1][y2 - 1]);
                    }
                    max += grid[y1][x1];
                    // 同一个格子不能拿2次
                    if (x1 != x2) {
                        max += grid[y2][x2];
                    }
                    dp[k][y1][y2] = max;
                }
            }
        }
        // 负数就是什么都拿不到
        return Math.max(dp[2 * n - 2][n - 1][n - 1], 0);
    }

    // 空间优化
    public int cherryPickup2(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];
        // 初始认为都是荆棘
        for (int j = 0; j < n; j++) {
            Arrays.fill(dp[j], Integer.MIN_VALUE);
        }
        // 还没开始走
        dp[0][0] = grid[0][0];
        for (int k = 1; k < 2 * n - 1; k++) {
            // y1是纵坐标，最大不能超过n-1，如果已经走了k步，那他的初始值应该就是k-(n-1),同时y1也不能<0
            // y1是纵坐标，最大不能超过n-1，并且因为已经规定了走了k步，则也不能超过k
            for (int y1 = Math.min(k, n - 1) ; y1 >= Math.max(0, k - (n - 1)); y1--) {
                int x1 = k - y1;
                // A无法走到这个单元格，则整个路径就是不合法的
                for (int y2 = Math.min(k, n - 1); y2 >= y1; y2--) {
                    int x2 = k - y2;
                    // 同样的，整个路径不合法
                    if (grid[y2][x2] == -1 || grid[y1][x1] == -1) {
                        dp[y1][y2] = Integer.MIN_VALUE;
                        continue;
                    }
                    // 都向右走
                    int max = dp[y1][y2];
                    // A向右，B向下
                    if (y2 > 0) {
                        max = Math.max(max, dp[y1][y2 - 1]);
                    }
                    // A向下，B向右
                    if (y1 > 0) {
                        max = Math.max(max, dp[y1 - 1][y2]);
                    }
                    // 都向下
                    if (y1 > 0 && y2 > 0) {
                        max = Math.max(max, dp[y1 - 1][y2 - 1]);
                    }
                    max += grid[y1][x1];
                    // 同一个格子不能拿2次
                    if (x1 != x2) {
                        max += grid[y2][x2];
                    }
                    dp[y1][y2] = max;
                }
            }
        }
        // 负数就是什么都拿不到
        return Math.max(dp[n - 1][n - 1], 0);
    }
}
