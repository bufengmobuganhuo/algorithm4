package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex1545_1 {
    public static void main(String[] args) {
        Ex1545_1 ex1545_1 = new Ex1545_1();
        System.out.println(ex1545_1.findKthBit(4, 11));
    }

    public char findKthBit(int n, int k) {
        boolean needFlip = false;
        while (n != 1) {
            int chrNums = pow(2, n) - 1;
            if (2 * k < chrNums) {
                n--;
            }else if (2 * k - chrNums > 1) {
                n--;
                k = chrNums - k + 1;
                needFlip = !needFlip;
            }else {
                return needFlip ? '0' : '1';
            }
        }
        return needFlip ? '1' : '0';
    }

    // 递归版本
    private char findKthBit(int n, int k, boolean needFlip) {
        if (n == 1) {
            return needFlip ? '1' : '0';
        }
        int chrNums = pow(2, n) - 1;
        if (2 * k < chrNums) {
            return findKthBit(n - 1, k, needFlip);
        } else if (2 * k - chrNums > 1) {
            return findKthBit(n - 1, chrNums - k + 1, !needFlip);
        } else {
            return needFlip ? '0' : '1';
        }
    }

    private int pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        int res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res *= a;
            }
            a *= a;
            b >>= 1;
        }
        return res;
    }
}
