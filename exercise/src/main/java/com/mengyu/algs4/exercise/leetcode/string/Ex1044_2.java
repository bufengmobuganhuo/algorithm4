package com.mengyu.algs4.exercise.leetcode.string;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1044_2 {

    public static void main(String[] args) {
        String s = "aacaagtttacaagc";
        System.out.println(new Ex1044_2().longestDupSubstring(s));
    }

    private static String str;

    public String longestDupSubstring(String s) {
        str = s;
        Str[] suffix = new Str[s.length()];
        for (int i = 0; i < s.length(); i++) {
            suffix[i] = new Str(i);
        }
        Arrays.sort(suffix);
        int maxLen = -1;
        String ans = "";
        for (int i = suffix.length - 1; i > 0; i--) {
            Str str1 = suffix[i];
            Str str2 = suffix[i - 1];
            if (Math.min(str1.len(), str2.len()) < maxLen) {
                continue;
            }
            int idx = 0;
            while (idx < Math.min(str1.len(), str2.len()) && str1.get(idx) == str2.get(idx)) {
                idx++;
            }
            if (maxLen < idx) {
                maxLen = idx;
                ans = str.substring(str1.startIdx, maxLen + str1.startIdx);
            }
        }
        return ans;
    }

    private static class Str implements Comparable<Str> {

        private int startIdx;

        private char get(int idx) {
            return str.charAt(idx + startIdx);
        }

        private int len() {
            return str.length() - startIdx;
        }

        public Str(int startIdx) {
            this.startIdx = startIdx;
        }

        @Override
        public int compareTo(Str o) {
            for (int i = 0; i < Math.min(len(), o.len()); i++) {
                if (get(i) != o.get(i)) {
                    return get(i) - o.get(i);
                }
            }
            return len() - o.len();
        }
    }
}
