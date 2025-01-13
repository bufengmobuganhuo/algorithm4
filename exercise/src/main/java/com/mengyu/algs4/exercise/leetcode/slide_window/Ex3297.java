package com.mengyu.algs4.exercise.leetcode.slide_window;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex3297 {

    public static void main(String[] args) {
        System.out.println(new Ex3297().validSubstringCount("eeffffddfeeefd", "ded"));
    }

    public long validSubstringCount(String word1, String word2) {
        // 统计word2中，每个字符需要多少个
        int[] diff = new int[26];
        for (char chr : word2.toCharArray()) {
            diff[chr - 'a']--;
        }
        long ans = 0;
        int cnt = (int) Arrays.stream(diff).filter(d -> d < 0).count();
        int n = word1.length(), leftPtr = 0, rightPtr = 0;
        while (leftPtr < n) {
            while (rightPtr < n && cnt > 0) {
                // 向右找
                cnt += update(diff, word1.charAt(rightPtr) - 'a', cnt, 1);
                rightPtr++;
            }
            if (cnt == 0) {
                ans += word1.length() - rightPtr + 1;
            }
            cnt += update(diff, word1.charAt(leftPtr) - 'a', cnt, -1);
            leftPtr++;
        }
        return ans;
    }

    private int update(int[] diff, int chr, int cnt, int val) {
        diff[chr] += val;
        if (val > 0 && diff[chr] == 0) {
            // 说明diff从-1变成0，右指针找到了一个word2中的字符
            // （找到的一定是word2这种的字符，因为不在word2中的字符最多会被减到0）
            return -1;
        } else if (val < 0 && diff[chr] == -1) {
            // 说明diff从0变成了-1，左指针向右移动了一位
            // 同时也说明字符在word2中，因为diff无论是什么字符都会+1，而不在word2中的字符最多会被减到0
            return 1;
        }
        return 0;
    }
}
