package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex274 {

    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println(new Ex274().hIndex(citations));
    }

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int ans = 0, n = citations.length;
        for (int i = n - 1; i >= 0; i--) {
            ans = Math.max(ans, Math.min(citations[i], n - i));
        }
        return ans;
    }
}
