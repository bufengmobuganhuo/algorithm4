package com.mengyu.algs4.exercise.leetcode.rank.year2022.apr17;

/**
 * @author yuzhang
 * @date 2022/4/17 10:23 AM
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        System.out.println(new Ex1().digitSum("11111222223", 3));
    }
    public String digitSum(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() > k) {
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < sb.length(); i+=k) {
                int num = 0;
                for (int j = i; j < k + i && j < sb.length(); j++) {
                    num += (sb.charAt(j) - '0');
                }
                tmp.append(num);
            }
            sb = tmp;
        }
        return sb.toString();
    }
}
