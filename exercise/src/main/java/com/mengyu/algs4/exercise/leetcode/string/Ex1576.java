package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex1576 {
    public String modifyString(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '?') {
                continue;
            }
            for (char chr = 'a'; chr <= 'c'; chr++) {
                if ((i > 0 && sb.charAt(i - 1) == chr) || (i < sb.length() - 1) && sb.charAt(i + 1) == chr) {
                    continue;
                }
                sb.replace(i, i + 1, String.valueOf(chr));
            }
        }
        return sb.toString();
    }

    private char getChr(char chr1, char chr2) {
        if (chr1 == '?' && chr2 == '?') {
            return 'a';
        } else if (chr1 == '?') {
            return (char) (chr2 < 'z' ? chr2 + 1 : chr2 - 1);
        } else if (chr2 == '?') {
            return (char) (chr1 < 'z' ? chr1 + 1 : chr1 - 1);
        }
        char min = chr1;
        char max = chr2;
        if (min > max) {
            min = chr2;
            max = chr1;
        }
        if (min == max && min == 'a') {
            return (char) (min + 1);
        } else if (min == max && min == 'z') {
            return (char) (min - 1);
        } else if (max == min) {
            return (char) (min + 1);
        } else if (max - min > 1) {
            return (char) (min + 1);
        } else if (max + 1 <= 'z') {
            return (char) (max + 1);
        }
        return (char) (min - 1);
    }
}
