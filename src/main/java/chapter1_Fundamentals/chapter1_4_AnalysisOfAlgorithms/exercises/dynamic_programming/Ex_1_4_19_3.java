package chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises.dynamic_programming;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/2/3 上午8:42
 * TODO
 */
public class Ex_1_4_19_3 {
    public static void main(String[] args) {
        int[][] arr = {
                {0, 12, 30, 42, 62, 95},
                {52, 68, 82, 66, 98, 4},
                {54, 16, 18, 36, 73, 7},
                {72, 10, 97, 27, 11, 89},
                {49, 51, 79, 59, 78, 81},
                {22, 80, 31, 75, 74, 65},
        };
        System.out.println(Arrays.toString(solution2(arr)));
    }

    public static int[] solution2(int[][] matrix) {
        int rowLen = matrix.length, colLen = matrix[0].length;
        return solution2(matrix, 0, rowLen - 1, 0, colLen - 1);
    }

    /**
     * 使用象限四等分做，o(n)
     */
    private static int[] solution2(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd) {
        int rowMid = rowStart + (rowEnd - rowStart) / 2;
        int colMid = colStart + (colEnd - colStart) / 2;
        int[] minInAxisIdx = findMinInAxis(matrix, colMid, rowStart, rowEnd, rowMid, colStart, colEnd);
        int minInAxisRow = minInAxisIdx[0];
        int minInAxisCol = minInAxisIdx[1];
        int minInAxis = matrix[minInAxisRow][minInAxisCol];
        if (minInAxisRow == rowMid) {
            int up = minInAxisRow > 0 ? matrix[minInAxisRow - 1][minInAxisCol] : Integer.MAX_VALUE;
            int down = minInAxisRow < matrix.length - 1 ? matrix[minInAxisRow + 1][minInAxisCol] : Integer.MAX_VALUE;
            if (minInAxis < up && minInAxis < down) {
                return new int[]{minInAxisRow, minInAxisCol};
            } else if (up < minInAxis) {
                return solution2(matrix, rowStart, minInAxisRow - 1, colStart, colEnd);
            } else {
                return solution2(matrix, minInAxisRow + 1, rowEnd, colStart, colEnd);
            }
        } else {
            int left = minInAxisCol > 0 ? matrix[minInAxisRow][minInAxisCol - 1] : Integer.MAX_VALUE;
            int right = minInAxisCol + 1 < matrix[0].length ? matrix[minInAxisRow][minInAxisCol + 1] : Integer.MAX_VALUE;
            if (minInAxis < left && minInAxis < right) {
                return new int[]{minInAxisRow, minInAxisCol};
            } else if (left < minInAxis) {
                return solution2(matrix, rowStart, rowEnd, colStart, minInAxisCol - 1);
            } else {
                return solution2(matrix, rowStart, rowEnd, minInAxisCol + 1, colEnd);
            }
        }
    }

    private static int[] findMinInAxis(int[][] matrix, int col, int rowStart, int rowEnd, int row, int colStart, int colEnd) {
        int min = Integer.MAX_VALUE;
        int minRow = 0, minCol = 0;
        for (int i = rowStart; i <= rowEnd; i++) {
            if (min > matrix[i][col]) {
                min = matrix[i][col];
                minRow = i;
                minCol = col;
            }
        }
        for (int i = colStart; i <= colEnd; i++) {
            if (min > matrix[row][i]) {
                min = matrix[row][i];
                minRow = row;
                minCol = i;
            }
        }
        return new int[]{minRow, minCol};
    }

    /**
     * 基于二分法做，nlogn
     */
    public static int[] solution1(int[][] matrix) {
        int colStart = 0, colEnd = matrix[0].length, rowLen = matrix.length, colLen = matrix[0].length;
        while (colStart < colEnd) {
            int colMid = colStart + (colEnd - colStart) / 2;
            int[] minInColumnIdx = findMinInColumn(matrix, colMid, 0, rowLen - 1);
            int minInColumn = matrix[minInColumnIdx[0]][minInColumnIdx[1]];
            int left = minInColumnIdx[1] > 0 ? matrix[minInColumnIdx[0]][minInColumnIdx[1] - 1] : Integer.MAX_VALUE;
            int right = minInColumnIdx[1] < colLen - 1 ? matrix[minInColumnIdx[0]][minInColumnIdx[1] + 1] : Integer.MAX_VALUE;
            if (left > minInColumn && right > minInColumn) {
                return minInColumnIdx;
            }
            if (left < minInColumn) {
                colEnd = minInColumnIdx[1] - 1;
            } else {
                colStart = minInColumnIdx[0] + 1;
            }
        }
        return new int[2];
    }

    private static int[] findMinInColumn(int[][] matrix, int col, int rowStart, int rowEnd) {
        int min = Integer.MAX_VALUE;
        int rowIdx = 0, colIdx = 0;
        for (int i = rowStart; i <= rowEnd; i++) {
            if (min > matrix[i][col]) {
                min = matrix[i][col];
                rowIdx = i;
                colIdx = col;
            }
        }
        return new int[]{rowIdx, colIdx};
    }
}
