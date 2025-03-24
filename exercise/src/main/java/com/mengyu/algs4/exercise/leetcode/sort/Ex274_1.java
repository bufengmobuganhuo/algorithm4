package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex274_1 {

    public static void main(String[] args) {
        int[] citations = {1, 3, 1};
        System.out.println(new Ex274_1().hIndex2(citations));
    }

    public int hIndex2(int[] citations) {
        int n = citations.length;
        int[] counter = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int citation = Math.min(citations[i], n);
            counter[citation]++;
        }
        int totalCnt = 0;
        for (int i = n; i >= 0; i--) {
            totalCnt += counter[i];
            if (totalCnt >= i) {
                return i;
            }
        }
        return 0;
    }

    public int hIndex(int[] citations) {
        if (citations.length == 0) {
            return 0;
        }
        Arrays.sort(citations);
        int ans = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] <= citations.length - i) {
                ans = Math.max(ans, citations[i]);
            } else {
                ans = Math.max(ans, citations.length - i);
            }
        }
        return ans;
    }
}
