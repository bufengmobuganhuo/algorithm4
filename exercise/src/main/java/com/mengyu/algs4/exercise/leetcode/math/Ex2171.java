package com.mengyu.algs4.exercise.leetcode.math;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2171 {
    /**
     * 假设最终每个袋子剩下的豆子数为x，则需要移除的豆子数 = total - 剩余豆子数 = total - x * (豆子数 >= x的袋子数)
     * 证明x就是beans[]中的一个
     */
    public long minimumRemoval(int[] beans) {
        Arrays.sort(beans);
        long total = 0L;
        for (int bean : beans) {
            total += bean;
        }
        long res = total;
        int n = beans.length;
        for (int i = 0; i < n; i++) {
            // 排序后，>= beans[i]的数量 = n - i
            res = Math.min(res, total - (long) beans[i] * (n - i));
        }
        return res;
    }
}
