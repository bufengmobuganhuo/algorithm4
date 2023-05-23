package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/12/16 上午8:42
 * TODO
 */
public class Ex290 {
    public boolean wordPattern(String pattern, String s) {
        if (isEmpty(pattern) || isEmpty(s)) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Map<String, Character> reverseMap = new HashMap<>();
        String[] params = s.split(" ");
        if (params.length != pattern.length()) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            char chr = pattern.charAt(i);
            if (map.containsKey(chr)) {
                String target = map.get(chr);
                if (!target.equals(params[i])) {
                    return false;
                }
            } else if (reverseMap.containsKey(params[i])) {
                return false;
            } else {
                map.put(chr, params[i]);
                reverseMap.put(params[i], chr);
            }
        }
        return true;
    }

    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
