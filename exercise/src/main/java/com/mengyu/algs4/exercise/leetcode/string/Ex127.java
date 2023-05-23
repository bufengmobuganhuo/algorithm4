package com.mengyu.algs4.exercise.leetcode.string;

import javafx.util.Pair;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/8/24 8:53 上午
 * 双向广度优先搜索
 */
public class Ex127 {
    public static void main(String[] args) {
        Ex127 ex127 = new Ex127();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(ex127.ladderLength(beginWord, endWord, wordList));
    }

    // 邻接表
    private Map<String, List<String>> adj;
    private int wordLen;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        wordLen = beginWord.length();
        adj = new HashMap<>();
        // 构造邻接表, (*it, h*t, hi*) -（都可以变成）-> hit
        wordList.forEach(word -> {
            for (int i = 0; i < wordLen; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, wordLen);
                List<String> adjList = this.adj.getOrDefault(newWord, new ArrayList<>());
                adjList.add(word);
                this.adj.put(newWord, adjList);
            }
        });
        // 从起点开始广度优先搜索:pair<单词，从起点到当前顶点深度>
        Queue<Pair<String, Integer>> queueBegin = new LinkedList<>();
        queueBegin.offer(new Pair<>(beginWord, 1));
        // <单词，深度>
        Map<String, Integer> markedBegin = new HashMap<>();
        markedBegin.put(beginWord, 1);

        // 从终点开始广度优先搜索:pair<单词，从终点到当前顶点的深度>
        Queue<Pair<String, Integer>> queueEnd = new LinkedList<>();
        queueEnd.offer(new Pair<>(endWord, 1));
        Map<String, Integer> markedEnd = new HashMap<>();
        markedEnd.put(endWord, 1);
        while (!queueEnd.isEmpty() && !queueBegin.isEmpty()) {
            // 从起点开始广度优先搜索"一层"
            int ans = visit(queueBegin, markedBegin, markedEnd);
            if (ans > -1) {
                return ans;
            }
            // 从终点开始广度优先搜索"一层"
            ans = visit(queueEnd, markedEnd, markedBegin);
            if (ans > -1) {
                return ans;
            }
        }
        return 0;
    }

    private int visit(Queue<Pair<String, Integer>> queue,
                      Map<String, Integer> markedMap,
                      Map<String, Integer> otherMarkedMap) {
        Pair<String, Integer> vertex = queue.poll();
        int depth = vertex.getValue();
        String word = vertex.getKey();

        for (int i = 0; i < wordLen; i++) {
            String newWord = word.substring(0, i) + "*" + word.substring(i + 1, wordLen);

            for (String adjVertex : adj.getOrDefault(newWord, new ArrayList<>())) {
                // 双向广度优先搜索相遇，则找到
                if (otherMarkedMap.containsKey(adjVertex)) {
                    return depth + otherMarkedMap.get(adjVertex);
                }

                if (!markedMap.containsKey(adjVertex)) {
                    markedMap.put(adjVertex, depth + 1);
                    queue.offer(new Pair<>(adjVertex, depth + 1));
                }
            }
        }
        return -1;
    }

}
