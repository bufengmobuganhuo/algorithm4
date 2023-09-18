package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex139_2 {

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(new Ex139_2().wordBreak(s, wordDict));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dic = new HashSet<>();
        int maxLenInDic = 0;
        for (String word : wordDict) {
            dic.add(word);
            maxLenInDic = Math.max(maxLenInDic, word.length());
        }
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i < len + 1; i++) {
            for (int j = i; j >= 0 && j >= i - maxLenInDic; j--) {
                if (dp[j] && dic.contains(s.substring(j, i))) {
                    dp[j] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}
