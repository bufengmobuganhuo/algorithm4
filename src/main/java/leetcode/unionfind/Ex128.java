package leetcode.unionfind;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/5/25 上午8:10
 * TODO
 */
public class Ex128 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        // 可以以O(1)的复杂度判断一个数是否存在，由于需要的是连续序列，所以重复数字算一个
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 0;
        for (int num : set) {
            // 不存在比他小的数，则说明num是序列的开头
            if (!set.contains(num - 1)) {
                int currentLen = 1;
                // 判断是否包含连续的第二个数
                while (set.contains(num + 1)) {
                    currentLen++;
                    longest = Math.max(longest, currentLen);
                    num += 1;
                }
            }
        }
        return longest;
    }
}
