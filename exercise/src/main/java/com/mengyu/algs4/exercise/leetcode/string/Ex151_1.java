package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex151_1 {

    public static void main(String[] args) {
        System.out.println(new Ex151_1().reverseWords("  hello world  "));
    }

    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        StringBuilder word = new StringBuilder();
        for (int i = s.length() - 1; i >= 0;) {
            if (s.charAt(i) == ' ') {
                while (i >= 0 && s.charAt(i) == ' ') {
                    i--;
                }
                if (word.length() > 0) {
                    res.append(" ").append(word.reverse());
                    word = new StringBuilder();
                }
            } else {
                word.append(s.charAt(i));
                i--;
            }
        }
        res.delete(0, 1);
        if (word.length() > 0) {
            if (res.length() > 0) {
                res.append(" ");
            }
            res.append(word.reverse());
        }

        return res.toString();
    }
}
