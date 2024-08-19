package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex676_1 {

    public static void main(String[] args) {
        String[] dictionary = {"hello", "leetcode"};
        Ex676_1 ex676_1 = new Ex676_1();
        ex676_1.buildDict(dictionary);
        System.out.println(ex676_1.search("hello"));
        System.out.println(ex676_1.search("hhllo"));

    }

    private Node root;

    public Ex676_1() {
        root = new Node();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            insert(word);
        }
    }

    public boolean search(String searchWord) {
        return dfs(searchWord, 0, root, false);
    }

    private boolean dfs(String searchWord, int idx, Node node, boolean changed) {
        if (idx > searchWord.length() - 1) {
            return changed && node.isEnd;
        }
        char chr = searchWord.charAt(idx);
        if (node.next[chr - 'a'] != null && dfs(searchWord, idx + 1, node.next[chr - 'a'], changed)) {
            return true;
        }
        if (!changed) {
            for (Node next : node.next) {
                if (next != null && next != node.next[chr - 'a']) {
                    dfs(searchWord, idx + 1, next, true);
                    if (dfs(searchWord, idx + 1, next, true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char chr = word.charAt(i);
            if (cur.next[chr - 'a'] == null) {
                cur.next[chr - 'a'] = new Node();
            }
            cur = cur.next[chr - 'a'];
        }
        cur.isEnd = true;
    }

    private static class Node {
        private Node[] next;

        private boolean isEnd;

        public Node() {
            next = new Node[26];
        }
    }
}
