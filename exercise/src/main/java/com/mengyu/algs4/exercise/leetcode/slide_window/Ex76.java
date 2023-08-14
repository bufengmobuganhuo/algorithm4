package com.mengyu.algs4.exercise.leetcode.slide_window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/8/27 8:53 上午
 * TODO
 */
public class Ex76 {
    public static void main(String[] args) {
        String s = "acdfebdterdfvabcde";
        String t = "abc";
        Ex76 ex76 = new Ex76();
        System.out.println(ex76.minWindow2(s, t));
    }

    /**
     * winFreq:滑动窗口中，字符的出现次数；targetFreq：目标字符串中，字符的出现次数
     * 1. 使用一个变量：realLen，表示滑动窗口中出现target中字符的总次数
     * 2. 由此可知，当realLen=target.length时，滑动窗口满足条件
     * 3. realLen在滑动窗口中的字符满足target时不再增加
     * 1⃣️ 在右指针向右移动以扩大滑动窗口时，只有当winFreq[source.charAt(right)]<targetFreq[source.charAt(right)]时，
     * realLen++;
     * 2⃣️ 在左指针向右移动以缩小滑动窗口时，只有当winFreq[source.charAt(right)]==targetFreq[source.charAt(right)]时，
     * realLen--
     *
     * @param source
     * @param target
     * @return
     */
    public String minWindow2(String source, String target) {
        int[] winFreq = new int[128];
        int[] targetFreq = new int[128];
        char[] sourceChrArr = source.toCharArray();
        // 统计目标字符串中字符的出现频率
        for (int i = 0; i < target.length(); i++) {
            targetFreq[target.charAt(i)]++;
        }
        int sourceLen = source.length();
        int targetLen = target.length();
        int ansLeftPtr = 0, ansLen = Integer.MAX_VALUE;
        // 滑动窗口是一个[left,right)的区间
        int leftPtr = 0, rightPtr = 0;
        int realLen = 0;
        while (rightPtr < sourceLen) {
            // 如果当前右指针指向的字符不在target中，则直接扩大窗口
            if (targetFreq[sourceChrArr[rightPtr]] == 0) {
                rightPtr++;
                continue;
            }
            // 只有在winFreq[source.charAt(right)] < targetFreq[source.charAt(right)]时，更新realLen
            if (winFreq[sourceChrArr[rightPtr]] < targetFreq[sourceChrArr[rightPtr]]) {
                realLen++;
            }
            // 向右扩大窗口,增加滑动窗口中字符的出现频率
            winFreq[sourceChrArr[rightPtr]]++;
            rightPtr++;
            // 当realLen=target.len时，则说明找到了满足条件的窗口,开始从左边开始缩小滑动窗口
            while (realLen == targetLen) {
                // 能进入循环， 则说明滑动窗口满足条件，则更新最小值
                if (rightPtr - leftPtr < ansLen) {
                    ansLen = rightPtr - leftPtr;
                    ansLeftPtr = leftPtr;
                }
                // 如果左指针当前指向的字符不再target中，则直接缩小窗口
                if (targetFreq[sourceChrArr[leftPtr]] == 0) {
                    leftPtr++;
                    continue;
                }
                if (winFreq[sourceChrArr[leftPtr]] == targetFreq[sourceChrArr[leftPtr]]) {
                    realLen--;
                }
                winFreq[sourceChrArr[leftPtr]]--;
                leftPtr++;
            }
        }
        // 如果ansLen没更新过，说明没有满足条件的
        if (ansLen == Integer.MAX_VALUE) {
            return "";
        }
        return source.substring(ansLeftPtr, ansLeftPtr + ansLen);
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> tChrMapCnt = new HashMap<>();
        Map<Character, Integer> ansChrMapCnt = new HashMap<>();
        // 统计t中字符出现的个数
        for (int i = 0; i < t.length(); i++) {
            tChrMapCnt.put(t.charAt(i), tChrMapCnt.getOrDefault(t.charAt(i), 0) + 1);
        }
        int leftPtr = 0, rightPtr = -1, ansLeftPtr = -1, ansRightPtr = 0;
        int minLen = Integer.MAX_VALUE;
        int sLen = s.length();
        while (rightPtr < sLen) {
            rightPtr++;
            if (rightPtr < sLen && tChrMapCnt.containsKey(s.charAt(rightPtr))) {
                ansChrMapCnt.put(s.charAt(rightPtr),
                        ansChrMapCnt.getOrDefault(s.charAt(rightPtr), 0) + 1);
            }
            // 如果当前窗口已经包含了t中的所有字符，则从左边开始缩小窗口，直到不满足为止
            while (containsAllChrs(tChrMapCnt, ansChrMapCnt) && leftPtr <= rightPtr) {
                // 更新最小长度的值
                if (rightPtr - leftPtr + 1 < minLen) {
                    minLen = rightPtr - leftPtr + 1;
                    ansLeftPtr = leftPtr;
                    ansRightPtr = leftPtr + minLen;
                }
                // 缩小窗口的同时，也需要更新字符值
                if (tChrMapCnt.containsKey(s.charAt(leftPtr))) {
                    ansChrMapCnt.put(s.charAt(leftPtr),
                            ansChrMapCnt.getOrDefault(s.charAt(leftPtr), 0) - 1);
                }
                leftPtr++;
            }
        }
        return ansLeftPtr == -1 ? "" : s.substring(ansLeftPtr, ansRightPtr);
    }

    // 判断当前滑动窗口是否已经全部包含t中的字符
    private boolean containsAllChrs(Map<Character, Integer> tChrMapCnt, Map<Character, Integer> ansChrMapCnt) {
        for (Map.Entry<Character, Integer> entry : tChrMapCnt.entrySet()) {
            if (entry.getValue() > ansChrMapCnt.getOrDefault(entry.getKey(), 0)) {
                return false;
            }
        }
        return true;
    }
}
