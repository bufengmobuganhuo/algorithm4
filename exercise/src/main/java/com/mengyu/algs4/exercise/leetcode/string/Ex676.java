package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex676 {

    public static void main(String[] args) {
        Ex676 ex676 = new Ex676();
        ex676.buildDict(new String[]{"hello", "leetcode"});
        ex676.search("hello");
    }

    private Node root;

    public Ex676() {
        root = new Node();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            Node curNode = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (curNode.next[idx] == null) {
                    curNode.next[idx] = new Node();
                }
                curNode = curNode.next[idx];
            }
            curNode.isVal = true;
        }
    }

    public boolean search(String searchWord) {
        return dfs(root, searchWord, 0, false);
    }

    private boolean dfs(Node node, String searchWord, int idx, boolean modified) {
        if (idx == searchWord.length()) {
            return modified && node.isVal;
        }
        char chr = searchWord.charAt(idx);
        if (node.next[chr - 'a'] != null) {
            if (dfs(node.next[chr - 'a'], searchWord, idx + 1, modified)) {
                return true;
            }
        }
        if (!modified) {
            for (int i = 0; i < 26; i++) {
                if (chr - 'a' != i && node.next[i] != null) {
                    if (dfs(node.next[i], searchWord, idx + 1, true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static class Node {
        private boolean isVal;

        private Node[] next = new Node[26];
    }
}
