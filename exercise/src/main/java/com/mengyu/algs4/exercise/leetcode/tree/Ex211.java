package com.mengyu.algs4.exercise.leetcode.tree;

/**
 * @author yu zhang
 */
public class Ex211 {
    public static void main(String[] args) {
        Ex211 ex211 = new Ex211();
        ex211.addWord("bad");
        ex211.addWord("dad");
        ex211.addWord("mad");
        ex211.addWord("pad");
        ex211.addWord("bad");
        ex211.addWord(".ad");
        System.out.println(ex211.search("bad"));
        System.out.println(ex211.search(".ad"));
        System.out.println(ex211.search("b.."));
    }
    private Node root;

    public Ex211() {

    }

    public void addWord(String word) {
        root = addWord(root, word, 0);
    }

    private Node addWord(Node node, String word, int idx) {
        char chr = word.charAt(idx);
        if (node == null) {
            node = new Node();
            node.chr = chr;
        }
        if (chr < node.chr) {
            node.left = addWord(node.left, word, idx);
        } else if (chr > node.chr) {
            node.right = addWord(node.right, word, idx);
        } else if (idx < word.length() - 1) {
            node.mid = addWord(node.mid, word, idx + 1);
        } else {
            node.val = true;
        }
        return node;
    }

    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(Node node, String word, int idx) {
        if (node == null) {
            return false;
        }
        char chr = word.charAt(idx);
        if (chr == '.') {
            if (idx == word.length() - 1 && node.val) {
                return true;
            } else if (idx == word.length() - 1) {
                return false;
            }
            return search(node.left, word, idx) || search(node.right, word, idx) || search(node.mid, word, idx + 1);
        }
        if (chr == node.chr) {
            if (idx == word.length() - 1 && node.val) {
                return true;
            }
            if (idx < word.length() - 1) {
                return search(node.mid, word, idx + 1);
            }
        }
        if (chr < node.chr) {
            return search(node.left, word, idx);
        }
        return search(node.right, word, idx);
    }

    private static class Node {
        private boolean val;
        private Node left, mid, right;
        private char chr;
    }
}
