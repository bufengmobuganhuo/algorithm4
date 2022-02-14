package leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1984 {
    public int minimumDifference(int[] nums, int k) {
        if (k == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int leftPtr = 0, rightPtr = k - 1;
        int ans = Integer.MAX_VALUE;
        while (rightPtr < nums.length) {
            ans = Math.min(ans, nums[rightPtr] - nums[leftPtr]);
            rightPtr++;
            leftPtr++;
        }
        return ans;
    }
}
