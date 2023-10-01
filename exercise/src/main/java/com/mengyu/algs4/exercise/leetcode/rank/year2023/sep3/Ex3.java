package com.mengyu.algs4.exercise.leetcode.rank.year2023.sep3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 2023/9/3 10:01
 * TODO
 */
public class Ex3 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 1, 9, 6);
        System.out.println(new Ex3().countInterestingSubarrays(list, 3, 0));
    }

    /**
     * 将nums[i] % modulo == k的记为1，否则记为0，则可以得到一个前缀和数组prefix
     * 我们的目标就变成了：从头到尾遍历prefix，对于prefix[r]，求有几个l使得：(prefix[r] - prefix[l])%modulo=k
     * 如果(prefix[r] - prefix[l])%modulo=k，则z*modulo + k = prefix[r] - prefix[l] -> prefix[r] - k = z*modulo + prefix[l]
     * 两边同时取余数得：(prefix[r] - k) % modulo = prefix[l] % modulo
     * 此时我们可以使用一个map来记录prefix[i]%modulo的个数，当遍历到prefix[r]时，可以知道 prefix[l]%modulo 的个数就是符合条件的子数组的个数
     * 而(prefix[r] - k) % modulo = prefix[l] % modulo，所以可以直接用prefix[r]来算出 prefix[l]%modulo
     */
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        long cnt = 0;
        Map<Long, Integer> map = new HashMap<>();
        // 因为是从左到右遍历，前缀和数组只依赖上一个元素，所以可以用一个数表示前缀和数组
        int prefixSum = 0;
        map.put(0L, 1);
        for (int num : nums) {
            int score = (num % modulo == k) ? 1 : 0;
            prefixSum += score;
            long prefixSumStartPoint = (prefixSum - k) % modulo;
            cnt += map.getOrDefault(prefixSumStartPoint, 0);
            long prefixSumEndPoint = prefixSum % modulo;
            map.put(prefixSumEndPoint, map.getOrDefault(prefixSumEndPoint, 0) + 1);
        }
        return cnt;
    }
}
