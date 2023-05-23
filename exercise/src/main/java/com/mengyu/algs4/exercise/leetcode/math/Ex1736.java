package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yuzhang
 * @date 2021/7/24 下午3:02
 * TODO
 */
public class Ex1736 {
    public String maximumTime(String time) {
        if (time == null || time.length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(time);
        for (int i = 0; i < time.length(); i++) {
            char chr = time.charAt(i);
            String replace = null;
            if (chr == '?' && i == 0) {
                replace = (sb.charAt(i + 1) == '?' || sb.charAt(i + 1) <= '3') ? "2" : "1";
            } else if (chr == '?' && i == 1) {
                replace = sb.charAt(i - 1) == '2' ? "3" : "9";
            } else if (chr == '?' && i == 2) {
                replace = ":";
            } else if (chr == '?' && i == 3) {
                replace = "5";
            } else if (chr == '?' && i == 4) {
                replace = "9";
            }
            if (replace != null) {
                sb.replace(i, i + 1, replace);
            }
        }
        return sb.toString();
    }
}
