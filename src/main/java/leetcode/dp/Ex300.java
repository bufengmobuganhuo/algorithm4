package leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex300 {
    public static void main(String[] args) {
        Ex300 ex300 = new Ex300();
        int[] nums = {3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};
        System.out.println(ex300.lengthOfLIS2(nums));
    }

    /**
     * 1. dp[i]: 以nums[i]结尾的最长递增子序列长度
     * 2. dp[i] = max{dp[j]} + 1, 0<j<i, 且nums[j]<nums[i]
     * 3. 时间复杂度O(n^2)
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int maxLen = 1;
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            // 最短可以是1
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            maxLen = Math.max(dp[i], maxLen);
        }
        return maxLen;
    }

    /**
     * 1. 最平常的贪心：每次递增时，让递增的数尽可能小，则子序列会更长
     * 2. dp[i]：长度为i的递增子序列末尾元素的最小值
     * 3. 在遍历的过程中: 记当前长度为len，遍历到第i个元素
     * (1) 如果nums[i] > dp[len]，则len++, dp[len]=nums[i]
     * (2) 如果nums[i] <= dp[len]，则使用二分查找（dp是有序的，因为长度越来越大，那么结尾数也肯定越来越大），
     * 找到d[j - 1] < nums[i] < dp[j]的dp[j]，并插入dp[j+1] = nums[i]
     */
    public int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[1] = nums[0];
        int maxLen = 1;
        for (int i = 1; i < len; i++) {
            if (dp[maxLen] < nums[i]) {
                dp[++maxLen] = nums[i];
            } else {
                int leftPtr = 0, rightPtr = maxLen;
                // 找到<=nums[i]的最大值
                while (leftPtr < rightPtr) {
                    int mid = leftPtr + (rightPtr - leftPtr + 1) / 2;
                    if (dp[mid] < nums[i]) {
                        leftPtr = mid;
                    } else {
                        rightPtr = mid - 1;
                    }
                }
                // 如果精确命中，则说明nums[i]并不能让递增序列更长
                if (leftPtr < maxLen && dp[leftPtr + 1] == nums[i]) {
                }else {
                    // dp[leftPtr] < nums[i] < dp[leftPtr+1]
                    dp[leftPtr + 1] = nums[i];
                }
            }
        }
        return maxLen;
    }
}
