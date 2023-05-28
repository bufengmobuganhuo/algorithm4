package leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2023/5/20 09:31
 * TODO
 */
public class Solution {

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            int num2 = target - num1;
            if (map.containsKey(num2)) {
                return new int[]{map.get(num2), i};
            }
            map.put(num1, i);
        }
        return null;
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int num2 = nums[j];
                if (num1 + num2 == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
