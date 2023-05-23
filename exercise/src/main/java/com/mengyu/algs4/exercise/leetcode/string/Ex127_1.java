package com.mengyu.algs4.exercise.leetcode.string;

import javafx.util.Pair;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/11/5 8:56 上午
 * TODO
 */
public class Ex127_1 {
    private Map<String, List<String>> adj;
    private int wordLen;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        wordLen = beginWord.length();
        buildDGraph(wordList);
        Queue<Pair<String,Integer>> startQueue = new LinkedList<>();
        Map<String,Integer> startMarkedMap = new HashMap<>();
        startQueue.offer(new Pair<>(beginWord,1));
        startMarkedMap.put(beginWord,1);

        Queue<Pair<String,Integer>> endQue = new LinkedList<>();
        Map<String,Integer> endMarkedMap = new HashMap<>();
        endQue.offer(new Pair<>(endWord,1));
        endMarkedMap.put(endWord,1);
        while (!startQueue.isEmpty()&&!endQue.isEmpty()){
            int answer = visit(startQueue,startMarkedMap,endMarkedMap);
            if (answer!=-1){
                return answer;
            }
            answer = visit(endQue,endMarkedMap,startMarkedMap);
            if (answer!=-1){
                return answer;
            }
        }
        return 0;
    }

    private void buildDGraph(List<String> wordList) {
        adj = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < wordLen; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, wordLen);
                List<String> adjWords = adj.getOrDefault(newWord, new ArrayList<>());
                adjWords.add(word);
                adj.put(newWord, adjWords);
            }
        }
    }

    private int visit(Queue<Pair<String, Integer>> queue,
                      Map<String, Integer> markedMap,
                      Map<String, Integer> otherMarkedMap) {
        Pair<String, Integer> vertex = queue.poll();
        String word = vertex.getKey();
        Integer depth = vertex.getValue();
        for (int i = 0; i < wordLen; i++) {
            String newWord = word.substring(0, i) + "*" + word.substring(i + 1, wordLen);
            for (String adjVertex : adj.getOrDefault(newWord, new ArrayList<>())) {
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
