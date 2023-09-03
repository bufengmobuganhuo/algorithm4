package com.mengyu.algs4.exercise.leetcode.rank.year2023.august20;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2023/5/28 10:29
 * TODO
 */
public class Ex2 {
    public int minimumSum(int n, int k) {
        Set<Integer> set = new HashSet<>();
        int num = 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            while (set.contains(num)) {
                num++;
            }
            if (num >= k) {
                return sum + sum(num, n - i);
            } else {
                sum += num;
                set.add(k - num);
            }
            num++;
        }
        return sum;
    }

    private int sum(int start, int n) {
        return (int) (start * n + 0.5 * (n * n - n));
    }
}
