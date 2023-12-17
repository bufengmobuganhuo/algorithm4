package com.mengyu.algs4.exercise.leetcode.math;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex421 {

    private static final int HIGH_BIT = 30;

    public int findMaximumXOR(int[] nums) {
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            Set<Integer> seen = new HashSet<>();
            // 如果只想保留从最高位开始到第 k 个二进制位为止的部分
            // 只需将其右移 k 位
            for (int num : nums) {
                seen.add(num >> k);
            }
            // 目前 x 包含从最高位开始到第 k+1 个二进制位为止的部分
            // 我们将 x 的第 k 个二进制位置为 1，即为 x = x*2+1
            int xNext = 2 * x + 1;
            boolean found = false;
            for (int num : nums) {
                if (seen.contains(xNext ^ (num >> k))) {
                    found = true;
                    break;
                }
            }
            if (found) {
                x = xNext;
            } else {
                x = xNext - 1;
            }
        }
        return x;
    }
}
