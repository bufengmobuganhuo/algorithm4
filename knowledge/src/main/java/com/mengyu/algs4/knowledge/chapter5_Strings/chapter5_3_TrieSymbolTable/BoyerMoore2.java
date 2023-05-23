package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_3_TrieSymbolTable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/11/27 下午4:34
 * TODO
 */
public class BoyerMoore2 {
    public static void main(String[] args) {
        String target = "ababababca";
        String pattern = "abababca";
        BoyerMoore2 boyerMoore = new BoyerMoore2(pattern);
        System.out.println(boyerMoore.search(target));
    }

    private Map<Character, Integer> right;
    private final String pattern;

    public BoyerMoore2(String pattern) {
        this.pattern = pattern;
        this.right = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            right.put(pattern.charAt(i), i);
        }
    }

    public String search(String txt) {
        int skipStep = 0;
        for (int i = 0; i <= txt.length() - pattern.length(); i += skipStep) {
            skipStep = 0;
            for (int j = pattern.length() - 1; j >= 0; j--) {
                if (pattern.charAt(j) != txt.charAt(i + j)) {
                    skipStep = j - right.getOrDefault(txt.charAt(i + j), -1);
                    skipStep = Math.max(1, skipStep);
                    break;
                }
            }
            if (skipStep == 0) {
                return txt.substring(i, i + pattern.length());
            }
        }
        return null;
    }
}
