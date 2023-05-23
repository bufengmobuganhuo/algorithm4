package com.mengyu.algs4.knowledge.chapter5_Strings.chapter5_3_TrieSymbolTable.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangyu
 * 2020/6/11 11:45
 * TODO
 */
public class EX_5_3_8 {
    public static void main(String[] args) {
        String txt = "ABCADTABCTEDABC";
        String pattern = "ABC";
        EX_5_3_8 ex_5_3_8 = new EX_5_3_8(pattern);
        System.out.println(ex_5_3_8.search(txt));
        System.out.println(Arrays.toString(ex_5_3_8.searchAll(txt).toArray()));
        System.out.println(ex_5_3_8.count(txt));
    }

    private int[] next;
    private String pattern;

    public EX_5_3_8(String pattern) {
        this.pattern = pattern;
        generateNext();
    }

    public int count(String txt) {
        int count = 0;
        int i = 0, j = 0;
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

    public List<Integer> searchAll(String txt) {
        generateNextMore();
        int i = 0, j = 0;
        List<Integer> res = new ArrayList<>();
        while (i < txt.length() && j < pattern.length()) {
            if (j == -1 || txt.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    res.add(i - j);
                    j = next[j];
                }
            } else {
                j = next[j];
            }
        }
        return res;
    }

    /**
     * next数组往后多求一位
     */
    private void generateNextMore() {
        next = new int[pattern.length() + 1];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < pattern.length()) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }

    public int search(String txt) {
        int i = 0, j = 0;
        while (i < txt.length() && j < pattern.length()) {
            if (j == -1 || txt.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pattern.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    private void generateNext() {
        next = new int[pattern.length()];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < pattern.length() - 1) {
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
