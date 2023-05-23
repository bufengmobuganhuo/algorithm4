package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

/**
 * @author yuzhang
 * @date 2020/11/30 上午8:42
 * TODO
 */
public class Ex_5_3_8_1 {
    private final String pattern;
    private int[] next;

    public Ex_5_3_8_1(String pattern) {
        this.pattern = pattern;
        next = new int[pattern.length()];
    }

    public int count(String txt) {
        int count = 0, i = 0, j = 0;
        while (i < txt.length() && j < pattern.length()) {
            if (j == -1 || txt.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    count++;
                    j = next[j];
                }
            } else {
                j = next[j];
            }
        }
        return count;
    }

    private void generateNext() {
        next[0] = -1;
        int i = 0, j = -1;
        while (i < next.length) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }
}
