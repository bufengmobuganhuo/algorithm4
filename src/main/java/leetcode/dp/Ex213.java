package leetcode.dp;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2021/1/22 下午3:19
 * TODO
 */
public class Ex213 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        Ex213 ex213 = new Ex213();
        System.out.println(ex213.rob(nums));
    }

    /**
     * len = nums.length
     * 因为是连成一个环，所以只有两种情况：
     * 1. 从nums[0]开始，偷到nums[len-2]
     * 2. 从nums[1]开始，偷到nums[len-1]
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int len = nums.length;
        return Math.max(rob(nums, 0, len - 2), rob(nums, 1, len - 1));
    }

    /**
     * 1. dp[i]：从头开始偷，偷到当前位置时能取到的最大值
     * 2. 对于dp[i]：
     *  当偷当前位置时，则能取到的最大值是dp[i-2]+nums[i]
     *  当不偷当前位置，则能取到的最大值是dp[i-2]
     */
    private int rob(int[] nums, int begin, int end) {
        int len = end - begin + 1;
        int[] dp = new int[len];
        dp[0] = nums[begin];
        dp[1] = Math.max(nums[begin], nums[begin + 1]);
        for (int i = begin + 2; i < end + 1; i++) {
            dp[i - begin] = Math.max(dp[i - 2 - begin] + nums[i], dp[i - 1 - begin]);
        }
        return dp[len - 1];
    }
}
