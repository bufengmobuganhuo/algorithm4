package leetcode.dp;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/5/27 上午8:19
 * TODO
 */
public class Ex152 {
    /**
     * 对于nums[i]:
     * 1. 当nums[i]>0，则希望以nums[i-1]结尾的某个段的乘积为正数，且尽可能大
     * 2. 当nums[i]<0，则希望以nums[i-1]结尾的某个段的乘积为负数，且尽可能小
     * 则维护两个数组：
     * Fmax(i) = max(Fmax(i-1)*nums[i],Fmin(i-1)*nums[i],nums[i]):
     * Fmin(i) = min(Fmax(i-1)*nums[i],Fmin(i-1)*nums[i],nums[i])
     * 因为是子数组，必须连续，所以如果前两项都不是最大的，则要取一个单独的nums[i]
     * 3. 最后的结果就是max(Fmax)，以某个数结尾的子数组
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] maxF = new int[nums.length];
        int[] minF = new int[nums.length];
        maxF[0] = nums[0];
        minF[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(minF[i - 1] * nums[i], nums[i]));
            minF[i] = Math.min(maxF[i - 1] * nums[i], Math.min(minF[i - 1] * nums[i], nums[i]));
        }
        return Arrays.stream(maxF).max().getAsInt();
    }

    // 空间优化
    public int maxProduct2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxF = nums[0];
        int minF = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mx = maxF, mn = minF;
            maxF = Math.max(maxF * nums[i], Math.max(minF * nums[i], nums[i]));
            minF = Math.min(maxF * nums[i], Math.min(minF * nums[i], nums[i]));
            ans = Math.max(ans, maxF);
        }
        return ans;
    }
}
