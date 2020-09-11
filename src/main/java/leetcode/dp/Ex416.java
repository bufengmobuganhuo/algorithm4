package leetcode.dp;

/**
 * @author yuzhang
 * @date 2020/9/2 11:45 上午
 * TODO
 */
public class Ex416 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        Ex416 ex416 = new Ex416();
        System.out.println(ex416.canPartition(nums));
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 只有和为偶数，才能分成两半
        if (sum % 2 != 0) {
            return false;
        }
        // 问题可以转化为一个容量为sum/2的背包，物品的价值和重量都是nums，
        // 则取最大值，如果取得的最大值=sum/2，则说明可以分成两半
        int cap = sum / 2;
        int[] dp = new int[cap + 1];
        for (int num : nums) {
            for (int j = cap; j >= num; j--) {
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
        }
        return dp[cap] == cap;
    }
}
