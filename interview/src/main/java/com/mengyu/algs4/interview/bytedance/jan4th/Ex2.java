package com.mengyu.algs4.interview.bytedance.jan4th;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/1/4 上午8:49
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        Ex2 ex2 = new Ex2();
        System.out.println(ex2.shortestDistance(words,"makes","coding"));
    }
    public int shortestDistance(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) {
            return -1;
        } else if (word1 == null || word1.length() == 0) {
            return -1;
        } else if (word2 == null || word2.length() == 0) {
            return -1;
        }
        long res = Integer.MAX_VALUE;
        Map<String, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (word1.equals(words[i])) {
                res = findLen(res, map, word2, i);
            } else if (word2.equals(words[i])) {
                res = findLen(res, map, word1, i);
            }
            LinkedList<Integer> list = map.getOrDefault(words[i], new LinkedList<>());
            list.offer(i);
            map.put(words[i], list);
        }
        return (int) res;
    }

    private long findLen(long res, Map<String, LinkedList<Integer>> map, String word, int idx) {
        if (map.containsKey(word)) {
            long left = map.get(word).getFirst();
            long right = map.get(word).getLast();
            res = Math.min(res, Math.min(Math.abs(left - idx), Math.abs(right - idx)));
        }
        return res;
    }
}
