package com.mengyu.algs4.exercise.leetcode.pointer;


/**
 * @author yu zhang
 */
public class Ex345 {

    public String reverseVowels(String s) {
        int i = 0, j = s.length() - 1;
        char[] chrs = s.toCharArray();
        while (true) {
            while (i < j) {
                if (!isVowel(chrs[i])) {
                    i++;
                }
                if (!isVowel(chrs[j])) {
                    j--;
                }
                if (isVowel(chrs[i]) && isVowel(chrs[j])) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(chrs, i++, j--);
        }
        return new String(chrs);
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    private boolean isVowel(char chr) {
        return "aeiouAEIOU".indexOf(chr) >= 0;
    }
}
