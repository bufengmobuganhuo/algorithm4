package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex2182 {
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] hash = new int[26];
        for (char chr : s.toCharArray()) {
            hash[chr - 'a']++;
        }
        StringBuilder ans = new StringBuilder();
        int repeatedCnt = 0;
        // i指向字典序最大的，j指向字典序次大的
        for (int i = 25, j = 24; i >= 0 && j >= 0;) {
            // i用完了，重置
            if (hash[i] == 0) {
                i--;
                repeatedCnt = 0;
            } else if (repeatedCnt < repeatLimit) {
                // i没用完，并且还符合repeatLimit，继续使用i
                ans.append((char) (i + 'a'));
                hash[i]--;
                repeatedCnt++;
            } else if (j >= i || hash[j] == 0){
                // j不再指向次大，或者j用完了
                j--;
            } else {
                // 使用j
                ans.append((char) (j + 'a'));
                hash[j]--;
                repeatedCnt = 0;
            }
        }
        return ans.toString();
    }
}
