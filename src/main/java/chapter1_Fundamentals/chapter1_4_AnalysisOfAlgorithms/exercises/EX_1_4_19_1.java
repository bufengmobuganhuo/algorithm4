package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;

import javafx.scene.transform.MatrixType;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/5/25 9:06
 * 练习1.4.19：第一次尝试
 */
public class EX_1_4_19_1 {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 12, 30, 42, 62, 95},
                {52, 68, 82, 66, 98, 4},
                {54, 16, 18, 36, 73, 7},
                {72, 10, 97, 27, 11, 89},
                {49, 51, 79, 59, 78, 81},
                {22, 80, 31, 75, 74, 65},
        };
        EX_1_4_19_1 ex_1_4_19_1 = new EX_1_4_19_1();
        System.out.println(Arrays.toString(ex_1_4_19_1.runSolution1(matrix)));
        System.out.println(Arrays.toString(ex_1_4_19_1.runSolution2(matrix)));
    }

    public int[] runSolution2(int[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return null;
        }
        return solution2(matrix, 0, matrix.length - 1, 0, matrix.length - 1);
    }

    public int[] solution2(int[][] matrix, int startRow, int endRow, int startCol, int endCol) {
        if (startCol > endCol || startRow > endRow) {
            return null;
        }
        int midRowIdx = startRow + (endRow - startRow) / 2;
        int midColIdx = startCol + (endCol - startCol) / 2;
        int[] axisInQuadrant = findMinimumInQuadrant(matrix, midRowIdx, startCol, endCol, midColIdx, startRow, endRow);
        int minInQuadrant = matrix[axisInQuadrant[0]][axisInQuadrant[1]];
        int minRowIdx = axisInQuadrant[0];
        int minColIdx = axisInQuadrant[1];
        int up = minRowIdx - 1 < 0 ? Integer.MAX_VALUE : matrix[minRowIdx - 1][minColIdx];
        int down = minRowIdx + 1 < matrix.length ? matrix[minRowIdx + 1][minColIdx] : Integer.MAX_VALUE;
        int left = minColIdx - 1 < 0 ? Integer.MAX_VALUE : matrix[minRowIdx][minColIdx - 1];
        int right = minColIdx + 1 < matrix.length ? matrix[minRowIdx][minColIdx + 1] : Integer.MAX_VALUE;
        if (up > minInQuadrant && down > minInQuadrant && left > minInQuadrant && right > minInQuadrant) {
            return new int[]{minRowIdx, minColIdx};
        }
        if (minInQuadrant > up) {
            return minInQuadrant > left
                    ? solution2(matrix, startRow, minRowIdx, startCol, minColIdx)
                    : solution2(matrix, startRow, minRowIdx, minColIdx, endCol);
        }
        if (minInQuadrant > down) {
            return minInQuadrant > left
                    ? solution2(matrix, minRowIdx, endRow, startCol, minColIdx)
                    : solution2(matrix, minRowIdx, endRow, minColIdx, endCol);
        }
        return null;
    }

    /**
     * @param matrix
     * @param rowIdx   在哪一行的横坐标查找
     * @param startCol
     * @param endCol
     * @param colIdx   在哪一列的纵坐标查找
     * @param startRow
     * @param endRow
     * @return
     */
    private int[] findMinimumInQuadrant(int[][] matrix, int rowIdx, int startCol, int endCol, int colIdx, int startRow, int endRow) {
        int min = Integer.MAX_VALUE;
        int minColIdx = -1;
        int minRowIdx = -1;
        for (int i = startCol; i <= endCol; i++) {
            if (min > matrix[rowIdx][i]) {
                min = matrix[rowIdx][i];
                minColIdx = i;
                minRowIdx = rowIdx;
            }
        }
        for (int i = startRow; i <= endRow; i++) {
            if (min > matrix[i][colIdx]) {
                min = matrix[i][colIdx];
                minColIdx = colIdx;
                minRowIdx = i;
            }
        }
        return new int[]{minRowIdx, minColIdx};
    }

    public int[] runSolution1(int[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return null;
        }
        return solution1(matrix, 0, matrix.length - 1);
    }

    /**
     * @param matrix
     * @param startCol
     * @param endCol
     * @return 使用递归方式：时间复杂度O(nlogn)
     */
    public int[] solution1(int[][] matrix, int startCol, int endCol) {
        if (startCol > endCol) {
            return null;
        }
        //在中间列中找到最小值，则上下满足
        int minimumInMidColRowIdx = findMinimumInCol(matrix, startCol, endCol);
        int miniMumInMidColColIdx = startCol + (endCol - startCol) / 2;
        int mid = matrix[minimumInMidColRowIdx][miniMumInMidColColIdx];
        int right = miniMumInMidColColIdx + 1 < matrix.length ? matrix[minimumInMidColRowIdx][miniMumInMidColColIdx + 1] : Integer.MAX_VALUE;
        int left = miniMumInMidColColIdx - 1 < 0 ? Integer.MAX_VALUE : matrix[minimumInMidColRowIdx][miniMumInMidColColIdx - 1];
        if (right > mid && left > mid) {
            return new int[]{minimumInMidColRowIdx, miniMumInMidColColIdx};
        }
        if (mid > left) {
            return solution1(matrix, startCol, miniMumInMidColColIdx);
        }
        if (mid > right) {
            return solution1(matrix, miniMumInMidColColIdx, endCol);
        }
        return null;
    }

    private int findMinimumInCol(int[][] matrix, int startCol, int endCol) {
        int midCol = startCol + (endCol - startCol) / 2;
        int min = Integer.MAX_VALUE;
        int minRowIdx = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (min > matrix[i][midCol]) {
                min = matrix[i][midCol];
                minRowIdx = i;
            }
        }
        return minRowIdx;
    }
}
