package com.mengyu.algs4.exercise.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex438 {
    public static void main(String[] args) {
        Ex438 ex438 = new Ex438();
        String s = "cbaebabacdabc";
        String p = "abc";
        System.out.println(ex438.findAnagrams(s, p));
    }

    public List<Integer> findAnagrams(String s, String p) {
        // 统计p中每种字符的出现个数
        int[] map = new int[26];
        // 统计滑动窗口中每种需要的字符的出现个数
        int[] countMap = new int[26];
        // 左闭右闭区间
        int leftPtr = 0, rightPtr = 0;
        // 滑动窗口中满足出现次数的字符种数
        int validChrs = 0;
        for (char chr : p.toCharArray()) {
            map[chr - 'a']++;
        }
        // 需要的字符种数
        int needChrs = 0;
        for (int count : map) {
            needChrs = count > 0 ? needChrs + 1 : needChrs;
        }
        List<Integer> ans = new ArrayList<>();
        while (rightPtr < s.length()) {
            char chr = s.charAt(rightPtr);
            // 如果是需要的字符
            if (map[chr - 'a'] > 0) {
                countMap[chr - 'a']++;
                // 满足出现次数
                if (countMap[chr - 'a'] == map[chr - 'a']) {
                    validChrs++;
                }
                // 如果有不需要的字符，则跳过这个字符，重新开始
            } else {
                rightPtr++;
                leftPtr = rightPtr;
                Arrays.fill(countMap, 0);
                validChrs = 0;
                continue;
            }
            // 如果满足长度条件
            if (rightPtr - leftPtr + 1 == p.length()) {
                // 如果满足出现次数条件，则符合条件
                if (validChrs == needChrs) {
                    ans.add(leftPtr);
                }
                chr = s.charAt(leftPtr++);
                // 如果被删除的字符是需要的字符
                if (map[chr - 'a'] > 0) {
                    countMap[chr - 'a']--;
                    validChrs = countMap[chr - 'a'] + 1 == map[chr - 'a'] ? validChrs - 1 : validChrs;
                }
            }
            rightPtr++;
        }
        return ans;
    }
}
