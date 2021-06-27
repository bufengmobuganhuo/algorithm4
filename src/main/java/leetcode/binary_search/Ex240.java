package leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex240 {
    public static void main(String[] args) {
        Ex240 ex240 = new Ex240();
        int[][] matrix = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        System.out.println(ex240.searchMatrix(matrix, 5));
    }

    /**
     * 1. 从左下角开始找，当matrix[row][col]>target时，向上移动一行
     * 2. 当matrix[row][col]<target时，向右移动一列，直到找到为止
     * 时间复杂度：O(m+n)
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int row = m - 1, col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                row--;
            }else {
                col++;
            }
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix[0].length;
        // 在第一列找到>=target的最大值
        int idx = findCeilInCol(matrix, target);
        if (idx == -1) {
            return false;
        }
        idx = matrix[idx][0] > target ? idx - 1 : idx;
        // 一行一行向上找，直到找到为止
        while (idx >= 0 && matrix[idx][0] <= target && matrix[idx][n - 1] >= target) {
            int leftPtr = 0, rightPtr = n;
            while (leftPtr <= rightPtr) {
                int mid = leftPtr + (rightPtr - leftPtr) / 2;
                if (matrix[idx][mid] == target) {
                    return true;
                } else if (matrix[idx][mid] < target) {
                    leftPtr = mid + 1;
                } else {
                    rightPtr = mid - 1;
                }
            }
            idx--;
        }
        return false;
    }

    private int findCeilInCol(int[][] matrix, int target) {
        int m = matrix.length;
        int leftPtr = 0, rightPtr = m - 1;
        while (leftPtr < rightPtr) {
            int mid = leftPtr + (rightPtr - leftPtr) / 2;
            if (matrix[mid][0] <= target) {
                leftPtr = mid + 1;
            } else {
                rightPtr = mid;
            }
        }
        if (rightPtr - 1 >= 0 && matrix[rightPtr - 1][0] == target) {
            return rightPtr - 1;
        }
        return rightPtr;
    }

}
