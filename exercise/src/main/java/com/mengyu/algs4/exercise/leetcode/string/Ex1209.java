package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yuzhang
 * @date 2021/1/18 上午8:55
 * TODO
 */
public class Ex1209 {
    public static void main(String[] args) {
        String s = "deeedbbcccbdaa";
        Ex1209 ex1209 = new Ex1209();
        System.out.println(ex1209.removeDuplicates(s, 3));
    }

    public String removeDuplicates(String s, int k) {
        if (s == null || s.length() < k) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        int lastLen = 0;
        while (lastLen != sb.length()) {
            lastLen = sb.length();
            char lastChr = sb.charAt(0);
            int count = 1;
            for (int i = 1; i < sb.length(); i++) {
                if (sb.charAt(i) == lastChr) {
                    count++;
                } else {
                    lastChr = sb.charAt(i);
                    count = 1;
                }
                if (count == k) {
                    sb.delete(i - k + 1, i + 1);
                    int j = i - k + 1;
                    while (j > 0 && j < sb.length() && sb.charAt(j) == sb.charAt(j - 1)) {
                        lastChr = sb.charAt(j);
                        j--;
                    }
                    i = j - 1;
                    count = 0;
                }
            }
        }
        return sb.toString();
    }
}
