package com.mengyu.algs4.knowledge.chapter9_Math;

/**
 * @author yu zhang
 */
public class GreatestCommonDivisor {
    public static void main(String[] args) {
        System.out.println(solution1(12,18));
        System.out.println(solution2(12, 18));
        System.out.println(solution1(3,4));
        System.out.println(solution2(3,4));
    }
    // 辗转相除法
    private static int solution1(int a, int b) {
        int mod = 0;
        do {
            mod = a % b;
            a = b;
            b = mod;
        }while (b != 0);
        return a;
    }

    // 更相减损数
    private static int solution2(int a, int b){
        do {
            a = (a > b ? a - b : a);
            b = (a < b ? b - a : b);
        }while (a != b);
        return a;
    }
}
