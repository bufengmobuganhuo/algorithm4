package leetcode.array;

/**
 * @author yuzhang
 * @date 2020/7/16 10:05 上午
 * leetcode74
 * 如果把矩阵拉成一维数组，那么这个数组是有序的，可以使用二分查找
 * 并且二者之间索引是有对应关系的idx=i(行索引)*n(列数)+j(列索引)
 */
public class Ex74 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        Ex74 ex74 = new Ex74();
        ex74.searchMatrix(matrix, 3);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        int leftPtr = 0;
        int rightPtr = rowNum * colNum - 1;
        while (leftPtr <= rightPtr) {
            int mid = leftPtr + (rightPtr - leftPtr) / 2;
            int row = mid / colNum;
            int col = mid % colNum;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                rightPtr = mid - 1;
            } else {
                leftPtr = mid + 1;
            }
        }
        return false;
    }
}
