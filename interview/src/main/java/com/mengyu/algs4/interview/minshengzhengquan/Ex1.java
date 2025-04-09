package com.mengyu.algs4.interview.minshengzhengquan;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @date 2025/3/12 18:10
 *
 * 题目1：给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了不止一次。找出那个只出现了一次的元素。
 * 示例 :
 * 输入: [2,2,3,2,4,5,4,5,5]
 * 输出: 3
 * class Solution {
 * public int singleNumber(int[] nums) {
 * }
 * }
 *
 * TODO
 */
public class Ex1 {

    public static void main(String[] args) {
        int[] nums = {2,2,3,2,4,5,4,5,5};
        System.out.println(new Ex1().singleNumber(nums));
    }

    Map<Integer, Integer> cntMap = new ConcurrentHashMap<>();

    private Object lock = new Object();

    public int singleNumber(int[] nums) {

        if (cntMap.get(1) == 1) {
            // do...
        }

        for (int num : nums) {
            synchronized (lock) {
                cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : cntMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
