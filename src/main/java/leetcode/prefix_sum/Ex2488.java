package leetcode.prefix_sum;

import java.util.HashMap;

/**
 * @author yu zhang
 */
public class Ex2488 {

    /**
     * 1. 数组由不同数字组成，那就说明k只出现一次
     * 2. 对于一个数组中的中位数，大于这个数的元素个数和小于这个数的元素个数相差为0或1
     * 3. 我们可以使用一个前缀和数组，如果遇到<k的数则记为-1，等于k的记为0，大于k的记为1
     * 4. 假设k对应的下标为kIdx，那么对于一个i >= kIdx的preSum[i]，只需要找到一个j < kIdx，使得preSum[i] - preSum[j] = 0或1
     * 5. 这个问题转化成了一个2sum问题，使用map来做
     */
    public int countSubarrays(int[] nums, int k) {
        int kIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                kIdx = i;
                break;
            }
        }
        HashMap<Integer, Integer> countMap = new HashMap<>();
        // 等于k的也算一个
        countMap.put(0, 1);
        int sum = 0, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += sign(nums[i] - k);
            if (i < kIdx) {
                // 记录kIdx前面的前缀和
                countMap.put(sum, countMap.getOrDefault(sum, 0) + 1);
            } else {
                ans += countMap.getOrDefault(sum, 0);
                ans += countMap.getOrDefault(sum - 1, 0);
            }
        }
        return ans;
    }

    private int sign(int num) {
        if (num == 0) {
            return 0;
        }
        return num < 0 ? -1 : 1;
    }
}
