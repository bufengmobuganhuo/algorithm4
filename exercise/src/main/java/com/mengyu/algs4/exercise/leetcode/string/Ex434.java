package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yuzhang
 * @date 2020/12/15 下午3:52
 * TODO
 */
public class Ex434 {
    public static void main(String[] args) {
        String s = "hello    , my name is tom ";
        Ex434 ex434 = new Ex434();
        System.out.println(ex434.countSegments(s));
    }

    public int countSegments(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        boolean lastIsSpace = false;
        int idx = 0;
        // 跳过前面的空格
        while (idx < s.length() && s.charAt(idx) == ' ') {
            idx++;
        }
        if (idx == s.length()) {
            return count;
        }
        int endIdx = s.length() - 1;
        // 跳过后面的空格
        while (endIdx >= 0 && s.charAt(endIdx) == ' ') {
            endIdx--;
        }
        if (endIdx < 0) {
            return count;
        }
        for (; idx <= endIdx; idx++) {
            if (s.charAt(idx) == ' ' && !lastIsSpace) {
                count++;
                lastIsSpace = true;
                // 如果是字符
            } else if (s.charAt(idx) != ' '){
                lastIsSpace = false;
            }
        }
        return count + 1;
    }
}
