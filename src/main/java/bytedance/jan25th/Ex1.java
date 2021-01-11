package bytedance.jan25th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/1/25 上午8:42
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.shiftGrid2(grid, 2));
    }


    public List<List<Integer>> shiftGrid2(int[][] grid, int k) {
        if (grid == null || grid.length == 0) {
            return new ArrayList<>();
        }
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(grid[i][j]);
            }
            res.add(row);
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int row = (i + k / n) % m;
                int col = (j + k % n);
                if (col >= n) {
                    row++;
                    col %= n;
                }
                if (row >= m) {
                    row = 0;
                }
                List<Integer> rowList = res.get(row);
                rowList.set(col, grid[i][j]);
            }
        }
        return res;
    }


    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        if (grid == null || grid.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int[] ints : grid) {
            List<Integer> row = new ArrayList<>();
            for (int anInt : ints) {
                row.add(anInt);
            }
            res.add(row);

        }
        for (int i = 0; i < k; i++) {
            res = convert(res);
        }
        return res;
    }

    private List<List<Integer>> convert(List<List<Integer>> grid) {
        List<List<Integer>> res = new ArrayList<>();
        int m = grid.size(), n = grid.get(0).size();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            if (i > 0) {
                row.add(grid.get(i - 1).get(n - 1));
            } else {
                row.add(grid.get(m - 1).get(n - 1));
            }
            for (int j = 0; j < n - 1; j++) {
                row.add(grid.get(i).get(j));
            }
            res.add(row);
        }
        return res;
    }
}
