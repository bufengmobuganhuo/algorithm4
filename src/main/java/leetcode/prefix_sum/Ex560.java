package leetcode.prefix_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex560 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        Ex560 ex560 = new Ex560();
        System.out.println(ex560.subarraySum(nums, 2));
    }

    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // <前缀和 -> 出现这个和的个数>
        Map<Integer, Integer> map = new HashMap<>();
        // 前缀和,表示到nums[i]时的和
        int prefixSum = 0;
        int count = 0;
        // 初始条件，和为0的个数为1，什么都不选
        map.put(0, 1);
        for (int num : nums) {
            prefixSum += num;
            count += map.getOrDefault(prefixSum - k, 0);
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
