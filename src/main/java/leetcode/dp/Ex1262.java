package leetcode.dp;

import sun.tools.jinfo.JInfo;

import java.util.Arrays;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/11/11 8:33 上午
 * TODO
 */
public class Ex1262 {
    public static void main(String[] args) {
        int[] nums = {3, 6, 5, 1, 8};
        Ex1262 ex1262 = new Ex1262();
        System.out.println(ex1262.maxSumDivThree(nums));
    }

    public int maxSumDivThree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        solution1(nums);
        System.out.println(solution3(nums, 3));
        return solution2(nums, 3);
    }

    private int solution1(int[] nums) {
        int[][] dp = new int[nums.length + 1][3];
        // 初始条件
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length + 1; i++) {
            int mod = nums[i - 1] % 3;
            if (mod == 0) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][0] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][2] + nums[i - 1]);
            } else if (mod == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + nums[i - 1]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + nums[i - 1]);
            }
        }
        print(dp);
        return dp[nums.length][0];
    }

    private int solution2(int[] nums, int k) {
        int[][] dp = new int[nums.length + 1][k];
        // 初始条件
        Arrays.fill(dp[0], Integer.MIN_VALUE);
        dp[0][0] = 0;
        for (int i = 1; i < nums.length + 1; i++) {
            int mod = nums[i - 1] % k;
            for (int j = 0; j < k; j++) {
                if (j >= mod) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - mod] + nums[i - 1]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j + k - mod] + nums[i - 1]);
                }
            }
        }
        print(dp);
        return dp[nums.length][0];
    }

    private int solution3(int[] nums, int k) {
        int[] dp = new int[k];
        int[] lastDp = new int[k];
        // 初始条件
        Arrays.fill(lastDp, Integer.MIN_VALUE);
        lastDp[0] = 0;
        for (int i = 1; i < nums.length + 1; i++) {
            int mod = nums[i - 1] % k;
            for (int j = 0; j < k; j++) {
                if (j >= mod) {
                    dp[j] = Math.max(lastDp[j], lastDp[j - mod] + nums[i - 1]);
                } else {
                    dp[j] = Math.max(lastDp[j], lastDp[j + k - mod] + nums[i - 1]);
                }
            }
            int[] tmp = lastDp;
            lastDp = dp;
            dp = tmp;
        }
        return lastDp[0];
    }

    private void print(int[][] dp) {
        for (int[] tmp : dp) {
            System.out.println(Arrays.toString(tmp));
        }
    }
}
