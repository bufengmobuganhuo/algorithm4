package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex3132 {

    public static void main(String[] args) {
        int[] nums1 = {7,9,1,4}, nums2 = {0,8};
        System.out.println(new Ex3132().minimumAddedInteger(nums1, nums2));
    }

    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length, m = nums2.length;
        int leftPtr = 0;
        int ans = Integer.MAX_VALUE;
        while (n - leftPtr  >= m) {
            int diff = nums2[0] - nums1[leftPtr];
            int cnt = 0, i = 0, idx = leftPtr;
            for (; idx < n; idx++) {
                while (idx < n && nums2[i] - nums1[idx] != diff) {
                    idx++;
                    cnt++;
                }
                if (cnt > 2) {
                    break;
                } else if (idx < n && nums2[i] - nums1[idx] == diff && idx - cnt - leftPtr + 1 == m) {
                    break;
                }
                i++;
            }
            if (idx < n && nums2[i] - nums1[idx] == diff && idx - cnt - leftPtr + 1 == m) {
                ans = Math.min(ans, diff);
            }
            leftPtr++;
        }
        return ans;
    }
}
