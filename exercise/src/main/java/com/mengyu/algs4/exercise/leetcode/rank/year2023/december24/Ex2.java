package com.mengyu.algs4.exercise.leetcode.rank.year2023.december24;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @date 2023/12/24 10:09
 * TODO
 */
public class Ex2 {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Set<Integer> h = spacing(hFences, m);
        Set<Integer> v = spacing(vFences, n);
        int maxSpacing = Integer.MIN_VALUE;
        for (int H : h) {
            if (v.contains(H)) {
                maxSpacing = Math.max(maxSpacing, H);
            }
        }
        return maxSpacing == Integer.MIN_VALUE ? -1 :
                (((maxSpacing % 1_000_000_007) *  (maxSpacing % 1_000_000_007)) % 1_000_000_007);
    }

    private Set<Integer> spacing(int[] fences, int m) {
        int[] copied = new int[fences.length + 2];
        System.arraycopy(fences, 0, copied, 1, fences.length);
        copied[0] = 1;
        copied[copied.length - 1] = m;
        Arrays.sort(copied);
        Set<Integer> spacingSet = new HashSet<>();
        for (int i = 0; i < copied.length; i++) {
            for (int j = i + 1; j < copied.length; j++) {
                spacingSet.add(copied[j] - copied[i]);
            }
        }
        return spacingSet;
    }
}
