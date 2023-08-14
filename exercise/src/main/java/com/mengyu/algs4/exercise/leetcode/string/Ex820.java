package com.mengyu.algs4.exercise.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex820 {

    public int minimumLengthEncoding(String[] words) {
        // 叶子节点对应的在words中索引
        Map<TrieNode, Integer> nodes = new HashMap<>();
        TrieNode root = new TrieNode();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode node = root;
            for (int j = word.length() - 1; j >= 0; j--) {
                node = node.getNode(word.charAt(j));
            }
            nodes.put(node, i);
        }

        int ans = 0;
        for (TrieNode node : nodes.keySet()) {
            if (node.childrenCnt == 0) {
                ans += words[nodes.get(node)].length() + 1;
            }
        }
        return ans;
    }

    private static class TrieNode {
        private int childrenCnt;

        private TrieNode[] next = new TrieNode[26];


        public TrieNode getNode(char chr) {
            if (next[chr - 'a'] == null) {
                next[chr - 'a'] = new TrieNode();
                childrenCnt++;
            }
            return next[chr - 'a'];
        }
    }
}
