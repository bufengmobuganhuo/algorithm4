package leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/3/18 下午2:49
 * TODO
 */
public class Ex594_1 {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
            if (map.containsKey(num - 1)) {
                res = Math.max(res, count + map.get(num - 1) + 1);
            }
            if (map.containsKey(num + 1)) {
                res = Math.max(res, count + map.get(num + 1) + 1);
            }
        }
        return res;
    }
}
