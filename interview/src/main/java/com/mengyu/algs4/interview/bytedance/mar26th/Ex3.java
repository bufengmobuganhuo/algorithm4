package com.mengyu.algs4.interview.bytedance.mar26th;

/**
 * @author yuzhang
 * @date 2021/3/26 上午9:59
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        String s = "()))((";
        Ex3 ex3 = new Ex3();
        System.out.println(ex3.minAddToMakeValid(s));
    }
    public int minAddToMakeValid(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        int count = 0, res = 0;
        for (int i = 0; i < S.length(); i++) {
            char chr = S.charAt(i);
            if (chr == ')') {
                count--;
            } else {
                if (i > 0 && S.charAt(i - 1) == ')' && count < 0) {
                    res += Math.abs(count);
                    count = 1;
                } else {
                    count++;
                }
            }
        }
        return res + Math.abs(count);
    }
}
