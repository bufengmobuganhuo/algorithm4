package com.mengyu.algs4.exercise.leetcode.rank.year2023.december10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @date 2023/12/10 10:47
 * TODO
 */
public class Ex3 {

    public static void main(String[] args) {
        int[] nums = {61, 23, 38, 23, 56, 40, 82, 56, 82, 82, 82, 70, 8, 69, 8, 7, 19, 14, 58, 42, 82, 10, 82, 78, 15
                , 82};
        System.out.println(new Ex3().countSubarrays(nums, 2));
    }

    public long countSubarrays(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, cnt = 0; i < nums.length; i++) {
            if (max == nums[i]) {
                cnt++;
            }
            map.putIfAbsent(cnt, i);
        }
        long res = 0;
        for (int i = 0, cnt = 0; i < nums.length; i++) {
            if (nums[i] == max) {
                cnt++;
            }
            if (cnt - k + 1 > 0) {
                res += map.get(cnt - k + 1) + 1;
            }
        }
        return res;
    }
}
