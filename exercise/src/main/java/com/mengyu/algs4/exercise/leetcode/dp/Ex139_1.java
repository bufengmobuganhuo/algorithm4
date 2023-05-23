package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex139_1 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDic = new HashSet<>();
        // 字典中最长字符串的长度
        int maxLen = 0;
        for (String word : wordDict) {
            wordDic.add(word);
            maxLen = Math.max(maxLen, word.length());
        }
        boolean[] dp = new boolean[s.length() + 1];
        // 空字符串为true
        dp[0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            // s[0....maxLen](最长合法字符串) 和 s[j...i]
            for (int j = i; j >= 0 && j >= i - maxLen; j--) {
                if (dp[j] && wordDic.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
