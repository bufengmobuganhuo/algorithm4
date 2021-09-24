package leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex594_2 {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num ,count + 1);
            if (map.containsKey(num - 1)) {
                res = Math.max(res, map.get(num - 1) + count + 1);
            }
            if (map.containsKey(num + 1)) {
                res = Math.max(res, map.get(num + 1) + count + 1);
            }
        }
        return res;
    }
}
