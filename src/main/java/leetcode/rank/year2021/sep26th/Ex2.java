package leetcode.rank.year2021.sep26th;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/12/26 10:37 上午
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        int[] startPos = {0, 1};
        System.out.println(Arrays.toString(ex2.executeInstructions(3, startPos, "RRDDLU")));
    }
    public int[] executeInstructions(int n, int[] startPos, String s) {
        int[] ans = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ans[i] = executeInstructions(n , startPos[0], startPos[1], s, 0, i);
        }
        return ans;
    }

    public int executeInstructions(int n, int row, int col, String s, int count, int idx) {
        if (idx >= s.length()) {
            return count;
        }
        if (s.charAt(idx) == 'D' && row + 1 < n) {
            return executeInstructions(n, row + 1, col, s, count + 1, idx + 1);
        } else if (s.charAt(idx) == 'U' && row - 1 >= 0) {
            return executeInstructions(n, row - 1, col, s, count + 1, idx + 1);
        } else if (s.charAt(idx) == 'L' && col - 1 >= 0) {
            return executeInstructions(n, row, col - 1, s, count + 1, idx + 1);
        } else if (s.charAt(idx) == 'R' && col + 1 < n){
            return executeInstructions(n, row, col + 1, s, count + 1, idx + 1);
        }
        return count;
    }

}
