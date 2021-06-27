package leetcode.rank.jun27;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1 {
    public int maxProductDifference(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        Arrays.sort(nums);
        return nums[len - 1] * nums[len - 2] - nums[0] * nums[1];
    }
}
