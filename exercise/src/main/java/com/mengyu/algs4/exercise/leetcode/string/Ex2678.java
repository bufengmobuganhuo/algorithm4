package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex2678 {
    public int countSeniors(String[] details) {
        int cnt = 0;
        for (String detail : details) {
            if ((detail.charAt(11) - '0') * 10 + (detail.charAt(12) - '0') > 60) {
                cnt++;
            }
        }
        return cnt;
    }
}
