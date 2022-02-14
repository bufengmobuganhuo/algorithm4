package leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex2006 {
    public int countKDifference(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += map.getOrDefault(nums[i] - k, 0);
            count += map.getOrDefault(nums[i] + k, 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return count;
    }
}
