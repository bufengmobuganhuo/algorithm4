package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yuzhang
 * @date 2020/12/14 上午11:28
 * TODO
 */
public class Ex689 {
    public static void main(String[] args) {
        Ex689 ex689 = new Ex689();
        String num = "82734";
        System.out.println(ex689.minPartitions(num));
    }

    /**
     * 1. 对于单位数来说，需要将它拆分成尽可能多的1相加，如，7=1+1+1+1+1+1+1
     * 2. 对于多位数来说，实际上就是使用上述策略逐个处理其每一位上的数字，
     *      在处理的过程中需要去除前导0，直到这个数变为0为止
     * @param n
     * @return
     */
    public int minPartitions(String n) {
        if (n == null || n.length() == 0) {
            return 0;
        }
        int count = 0;
        StringBuilder res = new StringBuilder(n);
        while (res.length() > 0) {
            // 去除前导0
            while (res.length() > 0 && res.charAt(0) == '0') {
                res.deleteCharAt(0);
            }
            if (res.length() == 0) {
                break;
            }
            for (int i = 0; i < res.length(); i++) {
                char chr = res.charAt(i);
                // 为每一位上的数字尽可能多的分配1
                if (chr > '0') {
                    res.setCharAt(i, (char) (chr - '1'+'0'));
                }
            }
            count++;
        }
        return count;
    }
}
