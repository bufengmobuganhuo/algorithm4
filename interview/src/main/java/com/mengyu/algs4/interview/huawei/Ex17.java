package com.mengyu.algs4.interview.huawei;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 2025/3/5 20:03
 * TODO
 */
public class Ex17 {

    public static void main(String[] args) {
        System.out.println(new Ex17().lengthOfLongestSubstring("abcabcbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0, n = s.length();
        int len = 0;
        while (l <= r) {
            while (r < n && map.getOrDefault(s.charAt(r), 0) == 0) {
                map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);
                r++;
            }
            len = Math.max(len, r - l);
            if (r >= n) {
                break;
            }
            while (l < r && s.charAt(l) != s.charAt(r)) {
                map.put(s.charAt(l), map.getOrDefault(s.charAt(l), 0) - 1);
                l++;
            }
            l++;
            r++;
        }
        return len;
    }
}
