package leetcode.dp;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/9/18 10:48 上午
 * TODO
 */
public class Ex494 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        Ex494 ex494 = new Ex494();
        System.out.println(ex494.findTargetSumWays2(nums, 3));
    }

    public int findTargetSumWays2(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum < S || -sum > S) {
            return 0;
        }
        int[] lastDp = new int[sum * 2 + 1];
        lastDp[sum - nums[0]]++;
        lastDp[sum + nums[0]]++;
        int[] dp = new int[sum * 2 + 1];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            for (int j = -sum; j < sum + 1; j++) {
                int left = j - num >= -sum ? lastDp[j - num + sum] : 0;
                int right = j + num <= sum ? lastDp[j + num + sum] : 0;
                dp[j + sum] = left + right;
            }
            int[] tmp = lastDp;
            lastDp = dp;
            dp = tmp;
        }
        return lastDp[sum + S];
    }

    /**
     * 定义状态：dp[i][j]为当选择第i个数字，并且组成的和为j时的个数
     * 状态转移方程：假设第i个元素为num，则dp[i][j]=dp[i-1][j-num]+dp[i-1][j+num]（有"+"和"-"两种情况）
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum < S || -sum > S) {
            return 0;
        }
        int len = nums.length;
        int[][] dp = new int[len][2 * sum + 1];
        dp[0][sum + nums[0]]++;
        dp[0][sum - nums[0]]++;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            for (int j = -sum; j < sum + 1; j++) {
                int left = j - num >= -sum ? dp[i - 1][j - num + sum] : 0;
                int right = j + num <= sum ? dp[i - 1][j + num + sum] : 0;
                dp[i][j + sum] = left + right;
            }
        }
        return dp[nums.length - 1][sum + S];
    }
}
