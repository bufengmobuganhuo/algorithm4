package com.mengyu.algs4.exercise.leetcode.rank.year2021.may29;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/5/29 下午10:30
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        String s = "xyzzaz";
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.countGoodSubstrings(s));
    }

    public int countGoodSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        int leftPtr = 0, rightPtr = 1;
        while (rightPtr < s.length()) {
            char chr = s.charAt(rightPtr);
            int len = rightPtr - leftPtr + 1;
            if (!set.contains(chr) && len < 3) {
                set.add(chr);
            } else if (!set.contains(chr) && len == 3) {
                count++;
                set.remove(s.charAt(leftPtr));
                set.add(chr);
                leftPtr++;
            } else if (set.contains(chr)) {
                while (leftPtr < s.length() && s.charAt(leftPtr) != chr) {
                    set.remove(s.charAt(leftPtr));
                    leftPtr++;
                }
                leftPtr++;
            }
            rightPtr++;
        }
        return count;
    }
}
