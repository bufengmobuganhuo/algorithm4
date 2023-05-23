package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;

import org.omg.CORBA.INTERNAL;

/**
 * @author yuzhang
 * @date 2020/10/12 11:05 上午
 * TODO
 */
public class EX_1_4_19_2 {
    public static void main(String[] args) {
        int[][] matrix = {
                {5, 12, 30, 42, 62, 95},
                {52, 68, 82, 66, 98, 4},
                {54, 16, 18, 36, 73, 7},
                {72, 10, 97, 27, 11, 89},
                {49, 51, 79, 59, 78, 81},
                {22, 80, 31, 75, 74, 65},
        };
        System.out.println(solution(matrix));
    }

    private static int solution(int[][] matrix) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        return solution(matrix, 0, 0, rowNum - 1, colNum - 1);
    }

    private static int solution(int[][] matrix, int startRow, int startCol, int endRow, int endCol) {
        int midRow = startRow + (endRow - startRow) / 2;
        int midCol = startCol + (endCol - startCol) / 2;
        int[] minInQuadrant = findMinimumInQuadrant(matrix, startCol, endCol, midRow, midCol, startRow, endRow);
        int minRow = minInQuadrant[0];
        int minCol = minInQuadrant[1];
        int minItem = matrix[minRow][minCol];
        int up = minRow > 0 ? matrix[minRow - 1][minCol] : Integer.MAX_VALUE;
        int down = minRow < matrix.length - 1 ? matrix[minRow + 1][minCol] : Integer.MAX_VALUE;
        int left = minCol > 0 ? matrix[minRow][minCol - 1] : Integer.MAX_VALUE;
        int right = minCol < matrix[0].length - 1 ? matrix[minRow][minCol + 1] : Integer.MAX_VALUE;
        if (up < minItem) {
            return minCol < midCol ? solution(matrix, startRow, startCol, midRow - 1, minCol - 1)
                    : solution(matrix, startRow, midCol + 1, midRow - 1, endCol);
        }
        if (down < minItem) {
            return minCol < midCol ? solution(matrix, midRow + 1, startCol, endRow, minCol - 1)
                    : solution(matrix, midRow + 1, midCol + 1, endRow, endCol);
        }
        if (left < minItem) {
            return minRow < midRow ? solution(matrix, startRow, startCol, midRow - 1, midCol - 1)
                    : solution(matrix, midRow + 1, startCol, endRow, midCol - 1);
        }
        if (right < minItem) {
            return minRow < midRow ? solution(matrix, startRow, midCol + 1, midRow - 1, endCol)
                    : solution(matrix, midRow + 1, midCol + 1, endRow, endCol);
        }
        return minItem;
    }

    private static int[] findMinimumInQuadrant(int[][] matrix, int startCol, int endCol, int midRow, int midCol, int startRow, int endRow) {
        int[] res = new int[2];
        int min = Integer.MAX_VALUE;
        for (int row = startRow; row < endRow + 1; row++) {
            if (min > matrix[row][midCol]) {
                min = matrix[row][midCol];
                res[0] = row;
                res[1] = midCol;
            }
        }
        for (int col = startCol; col < endCol + 1; col++) {
            if (min > matrix[midRow][col]) {
                min = matrix[midRow][col];
                res[0] = midRow;
                res[1] = col;
            }
        }
        return res;
    }

}
