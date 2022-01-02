package leetcode.rank.year2021.may29;

import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2021/5/29 下午11:36
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[][] grid = {
                {3, 4, 5, 1, 3},
                {3, 3, 4, 2, 3},
                {20, 30, 200, 40, 10},
                {1, 5, 5, 4, 1},
                {4, 3, 2, 2, 5}
        };
        Ex3 ex3 = new Ex3();
        System.out.println(ex3.getBiggestThree(grid));
    }

    private PriorityQueue<Integer> priorityQueue;

    public int[] getBiggestThree(int[][] grid) {
        priorityQueue = new PriorityQueue<>();
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!canBe(m, n, i, j)) {
                    continue;
                }
                add(sum(grid, i, j));
            }
        }
        int[] res = new int[priorityQueue.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }

    private int sum(int[][] grid, int i, int j) {
        if (j == 0) {
            return grid[i][j];
        }
        if (j == grid[0].length - 1) {
            return grid[i][j];
        }
        if (i == grid.length - 1) {
            return grid[i][j];
        }
        int sum = 0;
        int count = 0;
        while (j != 0) {
            sum += grid[i][j] + grid[i + j * 2][j];
            if (i != 0) {
                sum += grid[i][j + count * 2] + grid[i + j * 2][j + count * 2];
            }
            j--;
            i++;
            count++;
        }
        sum += grid[i][0] + grid[i][count * 2];
        return sum;
    }


    private boolean canBe(int m, int n, int i, int j) {
        if (i == 0 || i == m - 1) {
            return true;
        }
        if (j == 0 || j == n - 1) {
            return true;
        }
        if (i + j * 2 > m - 1) {
            return false;
        }
        if (j * 2 > n - 1) {
            return false;
        }
        return true;
    }

    private void add(int val) {
        if (priorityQueue.size() < 3) {
            priorityQueue.offer(val);
        } else if (val > priorityQueue.peek()) {
            priorityQueue.poll();
            priorityQueue.offer(val);
        }
    }
}
