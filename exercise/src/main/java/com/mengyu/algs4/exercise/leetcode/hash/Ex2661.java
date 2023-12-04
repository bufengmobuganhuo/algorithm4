package com.mengyu.algs4.exercise.leetcode.hash;

/**
 * @author yu zhang
 */
public class Ex2661 {

    public static void main(String[] args) {
        int[] arr = {8, 2, 4, 9, 3, 5, 7, 10, 1, 6};
        int[][] mat = {{8, 2, 9, 10, 4}, {1, 7, 6, 3, 5}};
        System.out.println(new Ex2661().firstCompleteIndex(arr, mat));
    }

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] locMap = new int[m * n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = mat[i][j];
                locMap[num] = i * n + j;
            }
        }
        int[] cntMap = new int[m + n];
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int id = locMap[num];
            int row = id / n;
            int col = id % n;
            cntMap[row]++;
            cntMap[col + m]++;
            if (cntMap[row] == n || cntMap[col + m] == m) {
                return i;
            }
        }
        return -1;
    }
}
