package leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/2/22 上午9:52
 * TODO
 */
public class Ex213_1 {
    public static void main(String[] args) {
        Ex213_1 ex213_1 = new Ex213_1();
        int[] nums = {1, 2, 3, 1};
        System.out.println(ex213_1.rob(nums));
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int x = rob(nums, 0, nums.length - 2);
        int y = rob(nums, 1, nums.length - 1);
        return Math.max(x, y);
    }

    private int rob(int[] nums, int start, int end) {
        int len = nums.length - 1;
        int[] dp = new int[len];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);
        for (int i = 2; i < end - start + 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[start + i], dp[i - 1]);
        }
        return dp[len - 1];
    }
}
