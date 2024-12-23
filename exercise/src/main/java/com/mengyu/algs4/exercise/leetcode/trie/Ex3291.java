package com.mengyu.algs4.exercise.leetcode.trie;

/**
 * @author yu zhang
 */
public class Ex3291 {

    public static void main(String[] args) {
        String[] words = {"abc","aaaaa","bcdef"};
        System.out.println(new Ex3291().minValidStrings(words, "aabcdabc"));
    }

    private String target;

    private Trie root;

    private Integer[] dp;

    private int inf = 1 << 30;

    /**
     * 1. 令dp[i]：从target[i]开始做连接操作，需要连接的最少字符串数量
     * 2. 当我们有一个长度为j的前缀作为从i开始的第一个前缀匹配，dp[i] = dp[i + j] + 1。这是一个递归的过程
     * 3. 将words的所有字符串放入字典树中，当我们遇到一个和target[i...i + j]完全匹配的合法前缀时，就找到了第2步中的长度为j的前缀
     */
    public int minValidStrings(String[] words, String target) {
        int n = target.length();
        dp = new Integer[n];
        this.target = target;
        this.root = new Trie();
        for (String word : words) {
            this.root.insert(word);
        }
        int ans = dfs(0);
        return ans == inf ? -1 : ans;
    }

    private int dfs(int i) {
        if (i >= target.length()) {
            return 0;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        Trie node = root;
        dp[i] = inf;
        for (int j = i; j < target.length(); j++) {
            int idx = target.charAt(j) - 'a';
            if (node.children[idx] == null) {
                break;
            }
            dp[i] = Math.min(dp[i], dfs(j + 1) + 1);
            node = node.children[idx];
        }
        return dp[i];
    }

    private static class Trie {
        private Trie[] children = new Trie[26];

        private void insert(String str) {
            Trie node = this;
            for (int i = 0; i < str.length(); i++) {
                int idx = str.charAt(i) - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new Trie();
                }
                node = node.children[idx];
            }
        }
    }
}
