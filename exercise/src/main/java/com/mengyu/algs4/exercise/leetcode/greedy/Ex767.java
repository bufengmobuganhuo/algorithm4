package com.mengyu.algs4.exercise.leetcode.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2020/12/2 上午11:07
 * TODO
 */
public class Ex767 {
    public static void main(String[] args) {
        Ex767 ex767 = new Ex767();
        String S = "aab";
        System.out.println(ex767.reorganizeString2(S));
    }

    public String reorganizeString2(String S) {
        FreqNode[] map = new FreqNode[26];
        for (int i = 0; i < 26; i++) {
            map[i] = new FreqNode((char) (i + 'a'), 0);
        }
        int maxCount = Integer.MIN_VALUE;
        for (char chr : S.toCharArray()) {
            map[chr - 'a'].freq++;
            maxCount = Math.max(maxCount, map[chr - 'a'].freq);
        }
        if (maxCount > (S.length() + 1) / 2) {
            return "";
        }
        Arrays.sort(map);
        StringBuilder res = new StringBuilder();
        while (map[0].freq > 0) {
            FreqNode letter1 = map[0];
            FreqNode letter2 = map[1];
            res.append(letter1.chr);
            letter1.freq--;
            if (letter2.freq>0){
                res.append(letter2.chr);
                letter2.freq--;
            }
            Arrays.sort(map);
        }
        return res.toString();
    }

    static class FreqNode implements Comparable<FreqNode> {
        char chr;
        int freq;

        public FreqNode(char chr, int freq) {
            this.chr = chr;
            this.freq = freq;
        }

        @Override
        public int compareTo(FreqNode o) {
            return o.freq - this.freq;
        }
    }

    public String reorganizeString(String S) {
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = Integer.MIN_VALUE;
        for (char chr : S.toCharArray()) {
            counts[chr - 'a']++;
            maxCount = Math.max(maxCount, counts[chr - 'a']);
        }
        /*
         * 1. 当 n 是偶数时，有 n/2 个偶数下标和 n/2 个奇数下标，
         *      因此每个字母的出现次数都不能超过 n/2 次，否则出现次数最多的字母一定会出现相邻。
         * 2. 当 n 是奇数时，由于共有 (n+1)/2 个偶数下标，
         *      因此每个字母的出现次数都不能超过 (n+1)/2 次，否则出现次数最多的字母一定会出现相邻。
         * 3. 由于当 n 是偶数时，在整数除法下满足 n/2 和 (n+1)/2 相等，
         *      因此可以合并 n 是偶数与 n 是奇数的情况：如果可以重新排布成相邻的字母都不相同的字符串，每个字母最多出现 (n+1)/2 次。
         */
        if (maxCount > (S.length() + 1) / 2) {
            return "";
        }
        // 按照counts数组中的频率进行字符的排序，大顶堆
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>((o1, o2) -> counts[o2 - 'a'] - counts[o1 - 'a']);
        for (char chr = 'a'; chr <= 'z'; chr++) {
            if (counts[chr - 'a'] > 0) {
                priorityQueue.offer(chr);
            }
        }
        StringBuilder res = new StringBuilder();
        // 保证一次能取出来两个
        while (priorityQueue.size() > 1) {
            char letter1 = priorityQueue.poll();
            char letter2 = priorityQueue.poll();
            res.append(letter1);
            res.append(letter2);
            counts[letter1 - 'a']--;
            counts[letter2 - 'a']--;
            if (counts[letter1 - 'a'] > 0) {
                priorityQueue.offer(letter1);
            }
            if (counts[letter2 - 'a'] > 0) {
                priorityQueue.offer(letter2);
            }
        }
        if (priorityQueue.size() > 0) {
            res.append(priorityQueue.poll());
        }
        return res.toString();
    }
}
