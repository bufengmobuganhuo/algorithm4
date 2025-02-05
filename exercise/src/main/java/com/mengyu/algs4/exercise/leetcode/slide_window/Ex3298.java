package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex3298 {
    public long validSubstringCount(String word1, String word2) {
        int[] diff = new int[26];
        int cntChr = 0;
        for (char chr : word2.toCharArray()) {
            diff[chr - 'a']--;
            if (diff[chr - 'a'] == -1) {
                cntChr++;
            }
        }
        int n = word1.length(), leftPtr = 0, rightPtr = 0;
        long ans = 0;
        while (leftPtr < n) {
            while (rightPtr < n && cntChr > 0) {
                cntChr += update(diff, word1.charAt(rightPtr), 1);
                rightPtr++;
            }
            if (cntChr == 0) {
                ans += n - rightPtr + 1;
            }
            cntChr += update(diff, word1.charAt(leftPtr), -1);
            leftPtr++;
        }
        return ans;
    }

    private int update(int[] diff, char chr, int val) {
        diff[chr - 'a'] += val;
        if (val > 0 && diff[chr - 'a'] == 0) {
            return -1;
        } else if (val < 0 && diff[chr - 'a'] == -1) {
            return 1;
        }
        return 0;
    }
}
