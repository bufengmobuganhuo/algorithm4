package leetcode.rank.year2021.junly18;

/**
 * @author yuzhang
 * @date 2021/7/18 上午10:57
 * https://leetcode-cn.com/problems/maximum-number-of-points-with-cost/solution/omndong-tai-gui-hua-by-seiei-5dm2/
 */
public class Ex3 {
    public static void main(String[] args) {
        int[][] points = {
                {1,2,3},
                {1,5,1},
                {3,1,1},
        };
        Ex3 ex3 = new Ex3();
        System.out.println(ex3.maxPoints(points));
    }
    public long maxPoints(int[][] points) {
        int m = points.length;
        int n = points[0].length;
        long[] dp = new long[n];
        for (int i = 0; i < m; i++) {
            // cur[j]：对于第j个位置，其左右两边及上边能取到的最大值
            long[] cur = new long[n + 1];
            long lmax = 0;
            for (int j = 0; j < n; j++) {
                // 假设之前lmax = dp[0]（0...j-1的最大值）, 现在遍历到了j，
                // 那么相对于lmax来说, dp[j]向右移动了一位(距离lmax更远了)，lmax-1
                lmax = Math.max(lmax - 1, dp[j]);
                cur[j] = lmax;
            }
            long rmax = 0;
            for (int j = n - 1; j >= 0; j--) {
                // 假设中以前rmax = dp[n-1](j-1...n-1的最大值),现在遍历到了j
                // 那么相对于rmax来说，dp[j]向左移动了一位（距离rmax更远了），rmax-1
                rmax = Math.max(rmax - 1, dp[j]);
                cur[j] = Math.max(cur[j], rmax);
            }
            for (int j = 0; j < n; j++) {
                dp[j] = cur[j] + points[i][j];
            }
        }
        long ans = 0;
        for (int j = 0; j < n; j++) {
            ans = Math.max(ans, dp[j]);
        }
        return ans;
    }
}
