package com.mengyu.algs4.exercise.leetcode.slide_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex30 {

    public static void main(String[] args) {
        String s = "barfoofoobarthefoobarman";
        String[] words = {"foo", "bar", "the"};
        System.out.println(new Ex30().findSubstring(s, words));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        int sl = s.length(), wsl = words.length, wl = words[0].length();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < wl; i++) {
            if (sl - i < wsl * wl) {
                break;
            }
            Map<String, Integer> differFromWords = new HashMap<>();
            for (int j = 0; j < wsl; j++) {
                String word = s.substring(i + j * wl, i + (j + 1) * wl);
                differFromWords.put(word, differFromWords.getOrDefault(word, 0) + 1);
            }
            for (String word : words) {
                differFromWords.put(word, differFromWords.getOrDefault(word, 0) - 1);
                if (differFromWords.get(word) == 0) {
                    differFromWords.remove(word);
                }
            }
            for (int start = i; start < sl - wl * wsl + 1; start += wl) {
                if (start != i) {
                    String word = s.substring(start + (wsl - 1) * wl, start + wsl * wl);
                    differFromWords.put(word, differFromWords.getOrDefault(word, 0) + 1);
                    if (differFromWords.get(word) == 0) {
                        differFromWords.remove(word);
                    }
                    word = s.substring(start - wl, start);
                    differFromWords.put(word, differFromWords.getOrDefault(word, 0) - 1);
                    if (differFromWords.get(word) == 0) {
                        differFromWords.remove(word);
                    }
                }
                if (differFromWords.isEmpty()) {
                    ans.add(start);
                }
            }
        }
        return ans;
    }
}
