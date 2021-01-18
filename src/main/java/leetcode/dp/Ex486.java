package leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/1/13 下午4:19
 * TODO
 */
public class Ex486 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 233, 7};
        Ex486 ex486 = new Ex486();
        System.out.println(ex486.PredictTheWinner(nums));
    }

    /**
     * 存储空间优化
     * @param nums
     * @return
     */
    public boolean PredictTheWinner2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int[] dp = new int[nums.length];
        System.arraycopy(nums, 0, dp, 0, nums.length);
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                dp[j] = Math.max(nums[i]-dp[j],nums[j]-dp[j-1]);
            }
        }
        return dp[nums.length - 1] >= 0;
    }

    /**
     * 1. dp[i][j]: 当剩下nums[i...j]范围的数字时，当前玩家（不一定是先手，是指当前要选数字的玩家）
     * 能取得的分数与另一位玩家分数的最大差值
     * 2. 当 i = j时，只有一个数字，那当前玩家肯定选这个值，并且另一位玩家得分为0，故dp[i][i]=nums[i]
     * 3. 当 i < j时，当前玩家有两种选择：
     * （1）选nums[i]，则另一位玩家会从[i+1...j]的范围取，则差值=nums[i]-dp[i+1][j]
     * （2）选nums[j]，则另一位玩家会从[i...j-1]的范围取，则差值=nums[j]-dp[i][j-1]
     * 二者取较大值
     */
    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }
}
