package com.mengyu.algs4.exercise.leetcode.tree;

/**
 * @author yuzhang
 * @date 2021/6/1 上午8:03
 * TODO
 */
public class Ex208 {
    public static void main(String[] args) {
        Ex208 ex208 = new Ex208();
        ex208.insert("apple");
        System.out.println(ex208.search("apple"));
        System.out.println(ex208.search("app"));
        System.out.println(ex208.startsWith("app"));
        ex208.insert("app");
        ex208.search("app");
    }

    private TrieNode root;

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        root = insert(root, word, 0);
    }

    private TrieNode insert(TrieNode node, String word, int idx) {
        char chr = word.charAt(idx);
        if (node == null) {
            node = new TrieNode();
            node.chr = chr;
        }
        if (chr < node.chr) {
            node.left = insert(node.left, word, idx);
        } else if (chr > node.chr) {
            node.right = insert(node.right, word, idx);
        } else if (idx < word.length() - 1) {
            node.mid = insert(node.mid, word, idx + 1);
        } else {
            node.val = 1;
        }
        return node;
    }


    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = search(root, word, 0);
        return node != null && node.val != null;
    }

    private TrieNode search(TrieNode node, String word, int idx) {
        if (node == null) {
            return null;
        }
        char chr = word.charAt(idx);
        if (chr < node.chr) {
            return search(node.left, word, idx);
        } else if (chr > node.chr) {
            return search(node.right, word, idx);
        } else if (idx < word.length() - 1) {
            return search(node.mid, word, idx + 1);
        }
        return node;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = search(root, prefix, 0);
        if (node == null) {
            return false;
        }
        return startsWith(node);
    }

    private boolean startsWith(TrieNode node) {
        if (node == null) {
            return false;
        }
        if (node.val != null) {
            return true;
        }
        boolean mid = startsWith(node.mid);
        if (mid) {
            return true;
        }
        boolean left = startsWith(node.left);
        boolean right = startsWith(node.right);
        return left || right;
    }

    static class TrieNode {
        char chr;
        Integer val;
        TrieNode left;
        TrieNode right;
        TrieNode mid;
    }
}
