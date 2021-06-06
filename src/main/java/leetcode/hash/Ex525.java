package leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/6/7 上午7:58
 * TODO
 */
public class Ex525 {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        map.put(0, -1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                counter--;
            } else {
                counter++;
            }
            if (map.containsKey(counter)) {
                int preIdx = map.get(counter);
                res = Math.max(res, i - preIdx);
            } else {
                map.put(counter, i);
            }
        }
        return res;
    }
}
