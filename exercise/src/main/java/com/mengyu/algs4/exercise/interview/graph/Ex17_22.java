package com.mengyu.algs4.exercise.interview.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex17_22 {
    public static void main(String[] args) {
        List<String> wordList = new ArrayList<String>(){
            {
                add("frye");
                add("heat");
                add("tree");
                add("thee");
                add("game");
                add("free");
                add("hell");
                add("fame");
                add("faye");
            }
        };
        System.out.println(new Ex17_22().findLadders("game", "thee", wordList));
    }
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        LinkedList<Integer>[] adj = new LinkedList[wordList.size() + 1];
        int  start = -1, end = -1;
        for (int i = 0; i < wordList.size(); i++) {
            if (endWord.equals(wordList.get(i))) {
                end = i;
            }
            if (beginWord.equals(wordList.get(i))) {
                start = i;
            }
        }
        if (end == -1) {
            return new ArrayList<>();
        }
        if (start == -1) {
            wordList.add(beginWord);
            start = wordList.size() - 1;
        }
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (getDiff(wordList.get(i), wordList.get(j)) == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        Queue<Integer> startQue = new LinkedList<>();
        boolean[] startMarked = new boolean[wordList.size()];
        int[] startEdgeTo = new int[wordList.size()];
        startQue.offer(start);
        startMarked[start] = true;

        Queue<Integer> endQue = new LinkedList<>();
        boolean[] endMarked = new boolean[wordList.size()];
        int[] endEdgeTo = new int[wordList.size()];
        endQue.offer(end);
        endMarked[end] = true;

        while (!startQue.isEmpty() && !endQue.isEmpty()) {
            int ansVertex = visit(adj, startQue, startMarked, startEdgeTo, endMarked);
            if (ansVertex >= 0) {
                List<String> ans = new ArrayList<>();
                for (int i = ansVertex; i != end; i = endEdgeTo[i]) {
                    ans.add(wordList.get(i));
                }
                ans.add(wordList.get(end));
                for (int i = startEdgeTo[ansVertex]; i != start; i=startEdgeTo[i]) {
                    ans.add(0, wordList.get(i));
                }
                ans.add(0, beginWord);
                return ans;
            }
            ansVertex = visit(adj, endQue, endMarked, endEdgeTo, startMarked);
            if (ansVertex >= 0) {
                List<String> ans = new ArrayList<>();
                for (int i = ansVertex; i != end; i = endEdgeTo[i]) {
                    ans.add(wordList.get(i));
                }
                ans.add(wordList.get(end));
                for (int i = startEdgeTo[ansVertex]; i != start; i=startEdgeTo[i]) {
                    ans.add(0, wordList.get(i));
                }
                ans.add(0, beginWord);
                return ans;
            }
        }
        return new ArrayList<>();
    }

    private int visit(LinkedList<Integer>[] adj, Queue<Integer> que, boolean[] marked, int[] edgeTo, boolean[] anotherMarked) {
        int vertex = que.poll();
        if (anotherMarked[vertex]) {
            return vertex;
        }
        for (int adjVertex : adj[vertex]) {
            if (!marked[adjVertex]) {
                marked[adjVertex] = true;
                que.offer(adjVertex);
                edgeTo[adjVertex] = vertex;
            }
        }
        return -1;
    }

    private int getDiff(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
