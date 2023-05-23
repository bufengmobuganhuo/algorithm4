package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex6 {
    public String convert(String s, int numRows) {
        int len = s.length();
        if (numRows == 1 || numRows >= len) {
            return s;
        }
        // 周期
        int cycle = 2 * numRows - 2;
        StringBuilder sb = new StringBuilder();
        // 枚举每一行
        for (int i = 0; i < numRows; i++) {
            // 在每一行中，每个周期最多有2个字符
            for (int j = 0; j + i < len; j += cycle) {
                // 第一个字符
                sb.append(s.charAt(i + j));
                // 第二个字符,只有第二行开始才有，并且最后一行没有
                if (i > 0 && i < numRows - 1 && j + cycle - i < len) {
                    sb.append(s.charAt(j + cycle - i));
                }
            }
        }
        return sb.toString();
    }
}
