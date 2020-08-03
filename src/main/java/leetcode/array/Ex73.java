package leetcode.array;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/7/16 9:04 上午
 * leetcode73：矩阵置零
 */
public class Ex73 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        Ex73 ex73 = new Ex73();
        ex73.setZeroes(matrix);
    }

    public void setZeroes(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        boolean[] rowsNeedSetZero = new boolean[matrix.length];
        boolean[] colsNeedSetZero = new boolean[matrix[0].length];
        // 标记出需要置零的行，列
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!rowsNeedSetZero[i]) {
                    rowsNeedSetZero[i] = matrix[i][j] == 0;
                }
                if (!colsNeedSetZero[j]){
                    colsNeedSetZero[j] = matrix[i][j] == 0;
                }
            }
        }
        // 行置零
        for (int i = 0; i < matrix.length; i++) {
            if (rowsNeedSetZero[i]) {
                Arrays.fill(matrix[i], 0);
            }
        }
        // 列置零
        for (int i = 0; i < matrix[0].length; i++) {
            if (colsNeedSetZero[i]) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }
}
