package com.mengyu.algs4.exercise.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex68 {

    public static void main(String[] args) {
        String[] words = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        System.out.println(new Ex68().fullJustify(words, 16));
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        for (int i = -1, j = 0; j < n; ) {
            int width = 0, spaceWidth = 0;
            while (j < n && width + spaceWidth < maxWidth) {
                if (width != 0) {
                    spaceWidth++;
                }
                width += words[j].length();
                j++;
            }
            j--;
            if (width + spaceWidth > maxWidth) {
                width -= words[j].length();
                j--;
            }
            int cnt = j - i;
            int spaces = maxWidth - width;
            int spacesPerWord = 0, mod = 0;
            if (j == n - 1) {
                spacesPerWord = 1;
                mod = spaces - (cnt - 1) * spacesPerWord;
            } else if (cnt == 1) {
                mod = spaces;
            } else {
                spacesPerWord = spaces / (cnt - 1);
                mod = spaces % (cnt - 1);
            }

            StringBuilder sb = new StringBuilder();
            for (int k = i + 1; k <= j; k++) {
                sb.append(words[k]);
                for (int l = 0; l < spacesPerWord && k < j; l++) {
                    sb.append(" ");
                }
                if (mod != 0 && (cnt != 2 && j < n - 1)) {
                    sb.append(" ");
                    mod--;
                }
            }
            while (mod != 0) {
                sb.append(" ");
                mod--;
            }
            ans.add(sb.toString());
            i = j;
            j++;
        }
        return ans;
    }
}
