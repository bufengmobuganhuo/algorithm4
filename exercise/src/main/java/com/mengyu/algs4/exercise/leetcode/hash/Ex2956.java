package com.mengyu.algs4.exercise.leetcode.hash;

/**
 * @author yu zhang
 */
public class Ex2956 {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] cnt1 = new int[101];
        int[] cnt2 = new int[101];
        for (int num : nums1) {
            cnt1[num]++;
        }
        for (int num : nums2) {
            cnt2[num]++;
        }
        int answer1 = 0, answer2 = 0;
        for (int i = 0; i < 101; i++) {
            if (cnt2[i] != 0 && cnt1[i] != 0) {
                answer1 += cnt1[i];
                answer2 += cnt2[i];
            }
        }
        return new int[]{answer1, answer2};
    }
}
