package com.mengyu.algs4.exercise.leetcode.rank.year2022.jan2;

/**
 * @author yuzhang
 * @date 2022/1/2 10:20 上午
 * TODO
 */
public class Ex1 {
    public boolean checkString(String s) {
        boolean flag = false;
        for (char chr : s.toCharArray()) {
            if (chr == 'b') {
                flag = true;
            }
            if (chr == 'a' && flag) {
                return false;
            }
        }
        return true;
    }
}
