package com.mengyu.algs4.exercise.leetcode.rank.year2022.september18;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex4 {
    public static void main(String[] args) {
        String[] words = {"abc","ab","bc","b"};
        System.out.println(Arrays.toString(new Ex4().sumPrefixScores(words)));
    }

    private TstNode root;

    private int count;

    public int[] sumPrefixScores(String[] words) {
        for (String word : words) {
            root = put(word, root, 0);
        }
        int[] ans = new int[words.length];
        for (int i = 0; i < ans.length; i++) {
            String word = words[i];
            count=0;
            get(word, root, 0);
            ans[i] += count;
        }
        return ans;
    }

    private TstNode get(String word, TstNode node, int idx) {
        if (node == null) {
            return null;
        }
        char chr = word.charAt(idx);
        if (chr < node.chr) {
            return get(word, node.left, idx);
        } else if (chr > node.chr) {
            return get(word, node.right, idx);
        } else if (idx < word.length() - 1) {
            count += node.count;
            return get(word, node.mid, idx + 1);
        }
        count += node.count;
        return node;
    }

    private TstNode put(String word, TstNode node, int idx) {
        char chr = word.charAt(idx);
        if (node == null) {
            node = new TstNode(chr, 0);
        }
        if (chr < node.chr) {
            node.left = put(word, node.left, idx);
        } else if (chr > node.chr) {
            node.right = put(word, node.right, idx);
        } else if (idx < word.length() -1) {
            node.count++;
            node.mid = put(word, node.mid, idx + 1);
        } else {
            node.count++;
        }
        return node;
    }

    static class TstNode {
        char chr;
        // 从根节点开始到当前节点为止的字符串的前缀有几个
        int count;

        TstNode left;
        TstNode mid;
        TstNode right;

        public TstNode(char chr, int count) {
            this.chr = chr;
            this.count = count;
        }
    }
}
