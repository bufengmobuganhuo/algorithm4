package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2707 {

    public static void main(String[] args) {
        System.out.println(new Ex2707().minExtraChar2("leetscode", new String[]{"leet", "code"}));
    }

    /**
     * 使用字典树优化查找过程：
     * 之前使用哈希表查找时，每次都是截取一个完整的字符串，效率低
     * 我们每次截取的过程为：s[j], s[j-1:j], s[j-2:j]，每次都是在之前的基础上多加一个字符串
     * 所以可以把字典的逆序放入字典树，这样每次查找时，只是在之前的基础上在字典树多找一层
     */
    public int minExtraChar2(String s, String[] dictionary) {
        int n = s.length();
        Trie trie = new Trie(false);
        for (String word : dictionary) {
            trie.insert(new StringBuilder(word).reverse().toString());
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            dp[i] = dp[i - 1] + 1;
            Trie node = trie;
            for (int j = i - 1; j >= 0; j--) {
                if (node != null) {
                    node = node.track(s.charAt(j));
                    if (node != null && node.isEnd) {
                        dp[i] = Math.min(dp[i], dp[j]);
                    }
                }
            }
        }
        return dp[n];
    }

    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        // dp[i] 表示分割 s 的前 i 个字符剩余的最少字符数
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        Set<String> set = new HashSet<>(Arrays.asList(dictionary));
        for (int i = 1; i < n + 1; i++) {
            // 把s[i]当做额外字符
            dp[i] = dp[i - 1] + 1;
            for (int j = i - 1; j >= 0; j--) {
                // 如果s[j:i]在字典中，那么dp[i]就等于dp[j]
                if (set.contains(s.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }
        return dp[n];
    }

    private static class Trie {
        private Trie[] next;

        private boolean isEnd;

        private Trie track(char chr) {
            Trie node = this;
            if (node == null || node.next[chr - 'a'] == null) {
                return null;
            }
            node = node.next[chr - 'a'];
            return node;
        }

        private void insert(String word) {
            Trie node = this;
            for (char chr : word.toCharArray()) {
                if (node.next[chr - 'a'] == null) {
                    node.next[chr - 'a'] = new Trie(false);
                }
                node = node.next[chr - 'a'];
            }
            node.isEnd = true;
        }

        public Trie(boolean isEnd) {
            next = new Trie[26];
            this.isEnd = isEnd;
        }
    }
}
