package com.mengyu.algs4.exercise.chapter5_3_triesymboltable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class BoyerMoore {
    private Map<Character, Integer> right;

    public int search(String txt, String pat) {
        right = new HashMap<>();
        for (int i = 0; i < pat.length(); i++) {
            right.put(pat.charAt(i), i);
        }

        int skipCnt = 0;
        for (int i = 0; i < txt.length() - pat.length(); i+=skipCnt) {
            for (int j = pat.length() - 1; j > -1; j--) {
                if (txt.charAt(i + j) == pat.charAt(j)) {
                    skipCnt = j - right.getOrDefault(txt.charAt(i + j), -1);
                    skipCnt = Math.max(skipCnt, 1);
                    break;
                }
            }
            if (skipCnt == 0) {
                return i;
            }
        }
        return -1;
    }
}
