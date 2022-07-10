package leetcode.rank.year2022.july3;



/**
 * @author yuzhang
 * @date 2022/7/3 10:23
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        System.out.println(new Ex2().spiralMatrix(5, 6, null));
    }
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] matrix = new int[m][n];
        int rowCount = 0, colCount = 0;
        int row = 0, col = 0;
        boolean north = true, east = false, south = false, west = false;
        while (row >= rowCount && row < m - rowCount && col >= colCount && col < n - colCount) {
            if (head == null) {
                matrix[row][col] = -1;
            } else {
                matrix[row][col] = head.val;
                head = head.next;
            }
            if (north) {
                if (col + 1 < n - colCount) {
                    col++;
                } else {
                    north = false;
                    east = true;
                    row = row + 1;
                }
            } else if (east) {
                if (row + 1 < m - rowCount) {
                    row++;
                } else {
                    east = false;
                    south = true;
                    col = col - 1;
                }
            } else if (south) {
                if (col - 1 >= colCount) {
                    col--;
                } else {
                    south = false;
                    west = true;
                    rowCount++;
                    row--;
                }
            } else if (west) {
                if (row - 1 >= rowCount) {
                    row--;
                } else {
                    west = false;
                    north = true;
                    colCount++;
                    col++;
                }
            }
        }
        return matrix;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

