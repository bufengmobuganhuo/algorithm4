package com.mengyu.algs4.exercise.leetcode.bit;

import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex2859 {

    public static void main(String[] args) {
        System.out.println(new Ex2859().sumIndicesWithKSetBits(Arrays.asList(5, 10, 1, 5, 2), 1));
    }

    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            int cnt = 0, num = i;
            while (num != 0) {
                if ((num & 1) == 1) {
                    cnt++;
                }
                num >>= 1;
            }
            ans = ans + (cnt == k ? nums.get(i) : 0);
        }
        return ans;
    }
}
