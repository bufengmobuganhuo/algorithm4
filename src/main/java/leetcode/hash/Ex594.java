package leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/12/22 上午10:17
 * TODO
 */
public class Ex594 {
    public int findLHS(int[] nums) {
        int res = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
            /**
             * 获取哈希映射中 x - 1, x, x + 1 三者出现的次数 u, v, w，
             * 那么 u + v 即为 x - 1, x 组成的和谐子序列的长度，v + w 即为 x, x + 1 组成的和谐子序列的长度。
             * 假设数组中最长的和谐子序列的最后一个元素在数组中的位置为 i，那么在扫描到 nums[i] 时，u + v 和 v + w 中一定有一个就是答案
             */
            if (map.containsKey(num + 1)) {
                res = Math.max(res, map.get(num + 1) + count);
            }
            if (map.containsKey(num - 1)) {
                res = Math.max(res, map.get(num - 1) + count);
            }
        }
        return res;
    }
}
