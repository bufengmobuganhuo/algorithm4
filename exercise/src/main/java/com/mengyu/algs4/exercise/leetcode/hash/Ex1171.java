package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1171 {
    public static void main(String[] args) {
        int[] deliciousness = {1, 3, 5, 7, 9};

    }
    private static final int MOD = 1000000007;
    /**
     * 1. 假设数组中最大值为maxVal，则数组中两个元素的和不会超过maxVal，则可以知道数组中（两个元素和满足是2的幂）的上限（这个幂的上限）
     * 2. 那么对于对于数组中的一个元素val，可以在知道上限的情况下枚举和，转变成了一个2-sum的问题
     */
    public int countPairs(int[] deliciousness) {
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(val, maxVal);
        }
        long count = 0;
        Map<Integer, Integer> valCountMap = new HashMap<>();
        for (int val : deliciousness) {
            int sum = 1;
            while (sum < val) {
                sum <<= 1;
            }
            for (; sum <= maxVal * 2; sum<<=1) {
                int valCount = valCountMap.getOrDefault(sum - val, 0);
                count = (count + valCount) % MOD;
            }
            valCountMap.put(val, valCountMap.getOrDefault(val, 0) + 1);
        }
        return (int) count;
    }
}
