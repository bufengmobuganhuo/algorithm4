package com.mengyu.algs4.exercise.leetcode.recursive;

/**
 * @author yuzhang
 * @date 2021/8/7 下午3:33
 * TODO
 */
public class Ex779_1 {
    public static void main(String[] args) {
        Ex779_1 ex779_1 = new Ex779_1();
        System.out.println(ex779_1.kthGrammar(3, 2));
    }

    public int kthGrammar(int n, int k) {
        return kthGrammar(n, k, false);
    }

    private int kthGrammar(int n, int no, boolean needFlip) {
        if (n == 1) {
            return needFlip ? 1 : 0;
        }
        if (no % 2 == 0) {
            return kthGrammar(n - 1, no /2, !needFlip);
        }
        return kthGrammar(n - 1, no / 2 + 1, needFlip);
    }
}
