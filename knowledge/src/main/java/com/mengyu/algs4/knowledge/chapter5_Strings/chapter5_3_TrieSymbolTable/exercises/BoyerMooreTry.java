package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/3/5 上午9:51
 * TODO
 */
public class BoyerMooreTry {
    private String pattern;
    private Map<Character, Integer> right;

    public BoyerMooreTry(String pattern) {
        this.pattern = pattern;
        right = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            right.put(pattern.charAt(i), i);
        }
    }

    public int search(String txt) {
        int skipStep = 0;
        for (int i = 0; i < txt.length() - pattern.length(); i += skipStep) {
            skipStep = 0;
            for (int j = pattern.length() - 1; j >= 0; j--) {
                if (txt.charAt(i + j) != pattern.charAt(j)) {
                    skipStep = j - right.getOrDefault(txt.charAt(i + j), -1);
                    skipStep = Math.max(skipStep, 1);
                    break;
                }
            }
            if (skipStep == 0) {
                return i;
            }
        }
        return -1;
    }
}
