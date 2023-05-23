package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex212 {
    private final int[][] directs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        Trie now = new Trie();
        for (String word : words) {
            now.insert(word);
        }
        Set<String> ans = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, now, ans);
            }
        }
        return new ArrayList<>(ans);
    }

    // 把匹配到的单词从单词树中删除
    private void dfs2(char[][] board, int i, int j, Trie now, Set<String> ans) {
        if (!now.children.containsKey(board[i][j])) {
            return;
        }
        char chr = board[i][j];
        Trie nxt = now.children.get(chr);
        if (!"".equals(nxt.word)) {
            ans.add(nxt.word);
            nxt.word = "";
        }
        if (!nxt.children.isEmpty()) {
            board[i][j] = '*';
            for (int[] direct : directs) {
                int i1 = i + direct[0], j1 = j + direct[1];
                if (i1 >= 0 && i1 < board.length && j1 >= 0 && j1 < board[0].length) {
                    dfs(board, i1, j1, nxt, ans);
                }
            }
            board[i][j] = chr;
        }
        if (nxt.children.isEmpty()) {
            now.children.remove(chr);
        }
    }

    private void dfs(char[][] board, int i, int j, Trie now, Set<String> ans) {
        if (!now.children.containsKey(board[i][j])) {
            return;
        }
        char chr = board[i][j];
        now = now.children.get(chr);
        if (!"".equals(now.word)) {
            ans.add(now.word);
        }
        board[i][j] = '*';
        for (int[] direct : directs) {
            int i1 = i + direct[0], j1 = j + direct[1];
            if (i1 >= 0 && i1 < board.length && j1 >= 0 && j1 < board[0].length) {
                dfs(board, i1, j1, now, ans);
            }
        }
        board[i][j] = chr;
    }

    static class Trie {
        private final Map<Character, Trie> children;
        private String word;

        public Trie() {
            children = new HashMap<>();
            word = "";
        }

        private void insert(String word) {
            Trie cur = this;
            for (int i = 0; i < word.length(); i++) {
                if (!cur.children.containsKey(word.charAt(i))) {
                    cur.children.put(word.charAt(i), new Trie());
                }
                cur = cur.children.get(word.charAt(i));
            }
            cur.word = word;
        }
    }
}
