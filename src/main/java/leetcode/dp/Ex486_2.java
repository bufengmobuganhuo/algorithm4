package leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex486_2 {
    public static void main(String[] args) {
        Ex486_2 ex486_2 = new Ex486_2();
        int[] nums = {1, 5, 2};
        System.out.println(ex486_2.PredictTheWinner2(nums));
    }
    public boolean PredictTheWinner2(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        System.arraycopy(nums, 0, dp, 0, len);
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[len - 1] >= 0;
    }

    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        // dp[i][j]: 当只剩下nums[i...j]时，当前玩家能获取的分数 - 另一个玩家获得的分数
        int[][] dp = new int[len][len];
        // 初始条件，如果
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                // 如果选nums[i]，另一个人只能选dp[i+1][j]，另一个同理
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] >= 0;
    }
}
