package com.mengyu.algs4.exercise.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/12/7 下午2:28
 * TODO
 */
public class Ex1679 {
    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 4, 3};
        Ex1679 ex1679 = new Ex1679();
        System.out.println(ex1679.maxOperations(nums, 6));
    }

    public int maxOperations(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(nums);
        if (nums[0] > k) {
            return 0;
        }
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int idx = 0;
        while (idx < nums.length && nums[idx] < k) {
            int num1 = nums[idx];
            int num2 = k - num1;
            if (!map.containsKey(num2)) {
                idx++;
                continue;
            }
            if (num1 == num2) {
                int sub = map.getOrDefault(num1, 0);
                if (sub / 2 > 0) {
                    map.put(num1, sub - 2 * (sub / 2));
                    count += sub / 2;
                }
            } else {
                int sub1 = map.getOrDefault(num1, 0);
                int sub2 = map.getOrDefault(num2, 0);
                int sub = Math.min(sub1, sub2);
                count += sub;
                map.put(num1, sub1 - sub);
                map.put(num2, sub2 - sub);
            }
            idx++;
        }
        return count;
    }
}
