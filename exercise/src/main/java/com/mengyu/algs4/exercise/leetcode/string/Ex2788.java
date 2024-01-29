package com.mengyu.algs4.exercise.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex2788 {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (char chr : word.toCharArray()) {
                if (chr == separator) {
                    if (sb.length() > 0) {
                        ans.add(sb.toString());
                        sb = new StringBuilder();
                    }
                } else {
                    sb.append(chr);
                }
            }
            if (sb.length() > 0) {
                ans.add(sb.toString());
            }
        }
        return ans;
    }
}
