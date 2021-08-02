package leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex300_1 {
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        Ex300_1 ex300_1 = new Ex300_1();
        System.out.println(ex300_1.lengthOfLIS3(nums));
    }

    /**
     * 也是使用上述算法，只是用List来实现，但是速度较慢
     */
    public int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        List<Integer> dp = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int idx = floor(dp, nums[i]);
            // 说明dp中没有比nums[i]大的，直接插入
            if (dp.size() == 0 || idx + 1 == dp.size()) {
                dp.add(nums[i]);
                // 找到了，则替换
            } else {
                dp.set(idx + 1, nums[i]);
            }
        }
        return dp.size();
    }

    private int floor(List<Integer> dp, int target) {
        // 优化
        if (dp.size() == 0 || dp.get(dp.size()-1) < target){
            return dp.size() - 1;
        }
        int leftPtr = -1, rightPtr = dp.size() - 1;
        while (leftPtr < rightPtr) {
            int mid = leftPtr + (rightPtr - leftPtr + 1) / 2;
            if (dp.get(mid) >= target) {
                rightPtr = mid - 1;
            } else {
                leftPtr = mid;
            }
        }
        return leftPtr;
    }

    /**
     * 方法二：贪心，在构建递增子序列时，让他增长的尽可能小，那么就可以构建出一个最小的递增子序列
     * dp[i]: 长度为i时，递增子序列的最后一个元素
     * 在遍历的过程中维护一个maxLen, 初始为1：
     * 1. 如果nums[i] > dp[maxLen] -> maxLen++, dp[maxLen] = nums[i]
     * 2. 如果nums[i] <= dp[maxLen] -> 在dp[0...i-1]做二分查找,找到一个dp[j-1] > nums[i] > dp[j]，更新dp[j]=nums[i]
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length + 2];
        dp[1] = nums[0];
        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > dp[maxLen]) {
                dp[++maxLen] = nums[i];
            } else {
                int leftPtr = 0, rightPtr = maxLen;
                while (leftPtr < rightPtr) {
                    int mid = leftPtr + (rightPtr - leftPtr + 1) / 2;
                    if (dp[mid] >= nums[i]) {
                        rightPtr = mid - 1;
                    } else {
                        leftPtr = mid;
                    }
                }
                // 如果精确命中，不需要更新
                if (leftPtr + 1 < maxLen && dp[leftPtr + 1] == nums[i]) {
                    continue;
                }
                dp[leftPtr + 1] = nums[i];
            }
        }
        return maxLen;
    }

    /**
     * 方法一：使用动态规划
     * 1. dp[i]: 以nums[i]结尾的最长递增子序列长度
     * 2. 状态转移方程：dp[i] = max{dp[j]}+1  j<i && nums[j] < nums[i]
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
