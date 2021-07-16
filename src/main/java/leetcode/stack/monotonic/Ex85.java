package leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex85 {
    public static void main(String[] args) {
        char[][] matrix = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        Ex85 ex85 = new Ex85();
        System.out.println(ex85.maximalRectangle(matrix));
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] heights = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    heights[i][j] = 0;
                } else if (i == 0) {
                    heights[i][j] = matrix[i][j] - '0';
                } else {
                    heights[i][j] += heights[i - 1][j] + (matrix[i][j] - '0');
                }
            }
        }
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[n], right = new int[n];
        int ans = 0;
        // 每一行统计能组成的最大矩形,使用leetcode84的解法
        for (int i = 0; i < m; i++) {
            stack.clear();
            int s = 0;
            Arrays.fill(right, n);
            for (int j = 0; j < n; j++) {
                while (!stack.isEmpty() && heights[i][j] <= heights[i][stack.peek()]) {
                    right[stack.pop()] = j;
                }
                left[j] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(j);
            }
            for (int j = 0; j < n; j++) {
                int width = right[j] - left[j] - 1;
                int height = heights[i][j];
                s = Math.max(s, width * height);
            }
            ans = Math.max(s, ans);
        }
        return ans;
    }


}
