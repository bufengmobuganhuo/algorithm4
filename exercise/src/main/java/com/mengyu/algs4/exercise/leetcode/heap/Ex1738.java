package com.mengyu.algs4.exercise.leetcode.heap;


import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex1738 {

    public static void main(String[] args) {
        int[][] matrix = {{5,2},{1,6}};
        System.out.println(new Ex1738().kthLargestValue(matrix, 2));
    }

    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            int xor = 0;
            for (int j = 0; j < n; j++) {
                int num = matrix[i][j];
                matrix[i][j] = xor ^ num ^ (i > 0 ? matrix[i - 1][j] : 0);
                if (que.size() < k) {
                    que.offer(matrix[i][j]);
                } else if (que.peek() < matrix[i][j]) {
                    que.poll();
                    que.offer(matrix[i][j]);
                }
                xor ^= num;
            }
        }
        return que.peek();
    }
}
