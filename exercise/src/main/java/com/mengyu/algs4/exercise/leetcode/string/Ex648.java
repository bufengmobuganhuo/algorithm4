package com.mengyu.algs4.exercise.leetcode.string;

import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex648 {

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList("a", "aa", "aaa", "aaaa");
        String sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa";
        System.out.println(new Ex648().replaceWords2(dictionary, sentence));
    }

    private TstNode tstRootNode;

    public String replaceWords2(List<String> dictionary, String sentence) {
        for (String prefix : dictionary) {
            tstRootNode = put(tstRootNode, prefix, 0);
        }
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = shortestPrefixOf(words[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            sb.append(words[i]);
            sb.append(" ");
        }
        sb.append(words[words.length - 1]);
        return sb.toString();
    }

    private String shortestPrefixOf(String word) {
        TstNode tstNode = tstRootNode;
        int index = 0;
        while (tstNode != null && index < word.length()) {
            char chr = word.charAt(index);
            if (chr < tstNode.chr) {
                tstNode = tstNode.left;
            } else if (chr > tstNode.chr) {
                tstNode = tstNode.right;
            } else  {
                index++;
                if (tstNode.isVal) {
                    return word.substring(0, index);
                }
                tstNode = tstNode.mid;
            }
        }
        return word;
    }

    private TstNode put(TstNode node, String key, int index) {
        char chr = key.charAt(index);
        if (node == null) {
            node = new TstNode();
            node.chr = chr;
        }
        if (chr < node.chr) {
            node.left = put(node.left, key, index);
        } else if (chr > node.chr) {
            node.right = put(node.right, key, index);
        } else if (index < key.length() - 1) {
            node.mid = put(node.mid, key, index + 1);
        } else {
            node.isVal = true;
        }
        return node;
    }

    private static class TstNode {
        private char chr;

        private boolean isVal;

        private TstNode left, mid, right;
    }


    private Node root;

    public String replaceWords(List<String> dictionary, String sentence) {
        for (String prefix : dictionary) {
            root = put(root, prefix, 0);
        }
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            int len = shortestPrefixOf(root, words[i], 0, 0);
            if (len > 0) {
                words[i] = words[i].substring(0, len);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            sb.append(words[i]);
            sb.append(" ");
        }
        sb.append(words[words.length - 1]);
        return sb.toString();
    }

    private int shortestPrefixOf(Node node, String word, int index, int length) {
        if (node == null || index == word.length()) {
            return length;
        }
        if (node.isVal) {
            return index;
        }
        char chr = word.charAt(index);
        return shortestPrefixOf(node.next[chr - 'a'], word, index + 1, length);
    }

    private Node put(Node node, String key, int index) {
        if (node == null) {
            node = new Node();
        }
        if (index == key.length()) {
            node.isVal = true;
            return node;
        }
        char chr = key.charAt(index);
        node.next[chr - 'a'] = put(node.next[chr - 'a'], key, index + 1);
        return node;
    }

    private static class Node {
        private boolean isVal;

        private Node[] next = new Node[26];
    }
}
