package com.mengyu.algs4.exercise.leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2020/9/27 8:58 上午
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();
        System.out.println(ex3.lengthOfLongestSubstring("a"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 0;
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0;
        while (right < s.length()) {
            char chr = s.charAt(right++);
            if (set.contains(chr)) {
                ans = Math.max(ans, set.size());
                while (left <= right && s.charAt(left) != chr) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.remove(s.charAt(left));
                left++;
            }
            set.add(chr);
        }
        return Math.max(ans, set.size());
    }
}
