package com.mengyu.algs4.exercise.leetcode.greedy;

import java.util.PriorityQueue;

/**
 * @author yu zhang
 * 假设字符串长度为n：
 * 1. 假如n是偶数，那么他有n/2个偶数位，n/2个奇数位，那么当一个字符的出现次数>n/2时，肯定无法重构
 * 2. 假如n是奇数，那么他有(n+1)/2个偶数位，n/2个奇数位，那么当一个字符的出现次数>(n+1)/2时，肯定无法重构
 * 综合整数除法的特性（如果n是偶数，则n/2 = (n+1)/2），可以整理为：
 * 如果一个字符的出现次数>(n+1)/2，则无法重构
 */
public class Ex767_1 {

    /**
     * 方法二：
     * 1. 先判断是否可以重构，不可重构直接返回
     * 2. 由于最多有(n+1)/2个偶数位，n/2个奇数位
     * 那么对于出现频率>n/2的一定要放到偶数位（因为偶数位多一些）
     * 对于出现频率<=n/2的，优先放到奇数位，如果奇数位不够可以放到偶数位
     */
    public String reorganizeString2(String s) {
        int n = s.length();
        int[] freqMap = new int[26];
        for (char chr : s.toCharArray()) {
            freqMap[chr - 'a']++;
            if (freqMap[chr - 'a'] > (n + 1) / 2) {
                return "";
            }
        }
        // 奇数位，偶数位
        int oddIdx = 1, evenIdx = 0;
        char[] res = new char[n];
        for (int i = 0; i < 26; i++) {
            while (freqMap[i] > 0 && freqMap[i] <= n / 2 && oddIdx < n) {
                res[oddIdx] = (char) (i + 'a');
                oddIdx += 2;
                freqMap[i]--;
            }
            while (freqMap[i] > 0) {
                res[evenIdx] = (char) (i + 'a');
                evenIdx += 2;
                freqMap[i]--;
            }
        }
        return new String(res);
    }

    /**
     * 方法一：
     * 1. 先判断是否可以重构，不可重构直接返回
     * 2. 以字符出现频率构造一个大顶堆，每次从堆中取出2个字符构造（可以保证这两个字符肯定是不同的），更新频率后再重新放入堆
     * 那么对于n为偶数的情况，只需从堆中取n/2次，就可以构造完成
     * 对于n为奇数的情况，只需从堆中取n/2次+堆中剩余一个字符，构造完成
     */
    public String reorganizeString(String s) {
        int[] freqMap = new int[26];
        int n = s.length();
        for (char chr : s.toCharArray()) {
            freqMap[chr - 'a']++;
            if (freqMap[chr - 'a'] > (n + 1) / 2) {
                return "";
            }
        }
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>((o1, o2) -> freqMap[o2-'a'] - freqMap[o1 - 'a']);
        for (int i = 0; i < 26; i++) {
            if (freqMap[i] > 0) {
                priorityQueue.offer((char) (i + 'a'));
            }
        }
        StringBuilder res = new StringBuilder();
        while (priorityQueue.size() / 2 > 0) {
            char chr1 = priorityQueue.poll();
            char chr2 = priorityQueue.poll();
            res.append(chr1);
            res.append(chr2);
            if (--freqMap[chr1 - 'a']>0) {
                priorityQueue.offer(chr1);
            }
            if (--freqMap[chr2 - 'a'] > 0) {
                priorityQueue.offer(chr2);
            }
        }
        if (!priorityQueue.isEmpty()) {
            res.append(priorityQueue.poll());
        }
        return res.toString();
    }
}
