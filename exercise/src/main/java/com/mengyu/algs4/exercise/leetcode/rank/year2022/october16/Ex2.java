package com.mengyu.algs4.exercise.leetcode.rank.year2022.october16;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2022/10/16 10:08
 * TODO
 */
public class Ex2 {
    public int countDistinctIntegers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
            set.add(reverse(num));
        }
        return set.size();
    }

    private int reverse(int num) {
        int ans = 0;
        while (num != 0) {
            int mod = num % 10;
            ans = ans * 10 + mod;
            num /= 10;
        }
        return ans;
    }
}
