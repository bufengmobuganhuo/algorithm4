package leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex473 {
    public static void main(String[] args) {
        int[] matchticks = {1, 1, 2, 2, 2};
        System.out.println(new Ex473().makesquare2(matchticks));
    }
    /**
     * dp[s]：s的第i位=1，表示当前放置了火柴，dp[s]表示当放置情况是s时，目前未放满的边的当前长度
     * 1. dp[0] = 0，没有取火柴，则为长度为0
     * 2. dp[s]，对于s中的第i位（第i位=1）,找到第i位=0的s1，如果dp[s1] + matchticks[i] <= len，则说明可以放入这个火柴
     * dp[s] = (dp[s1] + matchticks[i]) % len，之所以取余数，是因为比如放好了一个边，开始放下一个边，此时这个边的长度应该是0
     */
    public boolean makesquare2(int[] matchsticks) {
        int sum = 0, n = matchsticks.length;
        for (int i = 0; i < n; i++) {
            sum += matchsticks[i];
        }
        if (sum % 4 != 0) {
            return false;
        }
        int len = sum / 4;
        // 有n个火柴，则有n位
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int s = 1; s < (1 << n); s++) {
            for (int i = 0; i < n; i++) {
                // s的第i位不是1，表示没有取火柴，也就没有计算的意义
                if ((s & (1 << i)) == 0) {
                    continue;
                }
                // 第i位=0
                int s1 = s & ~(1 << i);
                // s1处理过
                if (dp[s1] >= 0 && dp[s1] + matchsticks[i] <= len) {
                    dp[s] = (dp[s1] + matchsticks[i]) % len;
                    // 因为s是递增的，那么每次只需要处理第一次遇到的1, 并且可以放入火柴的状态就可以
                    break;
                }
            }
        }
        return dp[(1 << n) - 1] == 0;
    }

    // 回溯 O(4^n)
    public boolean makesquare(int[] matchsticks) {
        int sum = 0, len = matchsticks.length;
        for (int i = 0; i < len; i++) {
            sum += matchsticks[i];
        }
        if (sum % 4 != 0) {
            return false;
        }
        Arrays.sort(matchsticks);
        for (int i = 0, j = len - 1; i < j; i++, j--) {
            int tmp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = tmp;
        }
        len = sum / 4;
        int[] edges = new int[4];
        return backtracking(0, matchsticks, edges, len);
    }

    private boolean backtracking(int idx, int[] matchsticks, int[] edges, int len) {
        if (idx == matchsticks.length) {
            return true;
        }
        for (int i = 0; i < edges.length; i++) {
            edges[i] += matchsticks[idx];
            if (edges[idx] <= len && backtracking(idx + 1, matchsticks, edges, len)) {
                return true;
            }
            edges[i] -= matchsticks[idx];
        }
        return false;
    }
}
