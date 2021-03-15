package leetcode.array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2021/3/24 上午11:01
 * TODO
 */
public class Ex378 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        Ex378 ex378 = new Ex378();
        System.out.println(ex378.kthSmallest(matrix, 8));
    }

    public int kthSmallest(int[][] matrix, int k) {
        return solution2(matrix, k);
    }

    private int solution2(int[][] matrix, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (heap.size() < k) {
                    heap.offer(matrix[i][j]);
                    continue;
                }
                if (matrix[i][j] < heap.peek()) {
                    if (heap.size() == k) {
                        heap.poll();
                    }
                    heap.offer(matrix[i][j]);
                }
            }
        }
        return heap.peek();
    }

    private int solution1(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(matrix, mid, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 检查小于mid的元素个数是否超过k
    private boolean check(int[][] matrix, int mid, int k) {
        int n = matrix.length, i = n - 1, j = 0, num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }
}
