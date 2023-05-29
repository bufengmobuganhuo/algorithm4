package com.mengyu.algs4.interview.bytedance.jan21st;

/**
 * @author yuzhang
 * @date 2021/1/21 上午9:01
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 3}};
        Ex2 ex2 = new Ex2();
        ex2.searchMatrix(matrix,3);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int i = mid / n;
            int j = mid % n;
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
