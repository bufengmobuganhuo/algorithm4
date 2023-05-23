package com.mengyu.algs4.exercise.leetcode.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex564 {
    public static void main(String[] args) {
        System.out.println(new Ex564().nearestPalindromic("11011"));
    }

    /**
     * 1. 发现可以使用数字的左半部分替换数字的右半部分，此时因为替换的是较低位,但是可能会遇到如下两种情况
     *  (1).如果替换后的数比原数大，也许可以通过减少中间位来靠近原数，比如99321, 替换后是99399，但是99299更接近
     *      此时还会出现一个问题：减小中间位可能会让位数发生变化，比如101，减少中间数后变成了9，然后再补充较低位成了99（10^(len-1) - 1）
     *  (2).如果替换后的数比原数小，也许可以通过增加中间位来靠近原数，比如12399,替换后12321，但是12421更接近
     *      此时也会出现一个问题；增加中间位可鞥会让位数发生变化，比如999，增加后变成了100，最后替换成了1001（10^len + 1）
     */
    public String nearestPalindromic(String n) {
        // 获取所有可能结果
        List<Long> candidates = getCandidates(n);
        long ans = -1, selfNumber = Long.parseLong(n);
        for (long candidate : candidates) {
            // 不能是自身
            if (candidate == selfNumber) {
                continue;
            }
            if (ans == -1 || Math.abs(selfNumber - candidate) < Math.abs(selfNumber - ans)
                    // 距离一样，返回较小的
                    || (Math.abs(selfNumber - candidate) == Math.abs(selfNumber - ans) && candidate < ans)){
                ans = candidate;
            }
        }
        return String.valueOf(ans);
    }

    public List<Long> getCandidates(String n) {
        int len = n.length();
        // 加入因为位数导致的问题
        List<Long> candidates = new ArrayList<Long>(){{
            add((long) (Math.pow(10, len - 1) - 1));
            add((long) (Math.pow(10, len) + 1));
        }};
        // 123 -> 12，1234 -> 12
        long selfPrefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        // 从中间位-1到中间位+1
        for (long i = selfPrefix - 1; i <= selfPrefix + 1; i++) {
            StringBuilder candidate = new StringBuilder();
            // 先连接前缀
            String prefix = String.valueOf(i);
            candidate.append(prefix);
            // 获取后缀
            StringBuilder suffix = new StringBuilder(prefix).reverse();
            // 如果len是偶数，则从0开始截取，如果len是奇数，从1开始截取
            // 1234 -> 12 -(反转后变成21，然后从s[0]开始截取)-> 1221;  123 -> 12 -(反转后变成21，然后从s[1]开始截取)> 121
            candidate.append(suffix.substring(len & 1));
            candidates.add(Long.valueOf(candidate.toString()));
        }
        return candidates;
    }
}
