package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] chrCnt = new int[26];
            for (char chr : str.toCharArray()) {
                chrCnt[chr - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (chrCnt[i] > 0) {
                    sb.append(i).append(chrCnt[i]).append('-');
                }
            }
            map.computeIfAbsent(sb.toString(), key -> new ArrayList<>()).add(str);
        }
        List<List<String>> ans = new ArrayList<>();
        map.forEach((k, v) -> {
            ans.add(v);
        });
        return ans;
    }
}
