package com.mengyu.algs4.exercise.leetcode.simulation;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yuzhang
 * @date 2026/11/5 22:29
 */
public class Ex3926 {

    public static void main(String[] args) {
        String[] chunks = {"hello wor","ld hello"};
        String[] queries = {"hello","world","wor"};
        new Ex3926().countWordOccurrences(chunks, queries);
    }

    public int[] countWordOccurrences(String[] chunks, String[] queries) {
        StringBuilder sb = new StringBuilder();
        for (String chunk : chunks) {
            sb.append(chunk);
        }

        Map<String, Integer> cntMap = new HashMap<>();
        int idx = 0;
        while (idx < sb.length()) {
            char chr = sb.charAt(idx);
            if (!Character.isLetter(chr)) {
                idx++;
                continue;
            }
            int startIdx = idx;
            idx++;
            while (idx < sb.length()) {
                chr = sb.charAt(idx);
                if (Character.isLetter(chr)) {
                    idx++;
                } else if (chr == '-' && idx + 1 < sb.length() && Character.isLetter(sb.charAt(idx + 1))) {
                    idx++;
                } else {
                    break;
                }
            }
            String word = sb.substring(startIdx, idx);
            cntMap.merge(word, 1, Integer::sum);
        }
        int[] result = new int[queries.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = cntMap.getOrDefault(queries[i], 0);
        }
        return result;
    }
}
