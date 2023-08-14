package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex76_2 {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(new Ex76_2().minWindow(s, t));
    }

    public String minWindow(String s, String t) {
        int[] winFreq = new int[128];
        int[] tFreq = new int[128];
        for (char chr : t.toCharArray()) {
            tFreq[chr]++;
        }
        int ansLeftPtr = 0, ansLen = Integer.MAX_VALUE;
        int realLen = 0;
        int leftPtr = 0, rightPtr = 0;
        while (rightPtr < s.length()) {
            int idx = s.charAt(rightPtr);
            if (tFreq[idx] == 0) {
                rightPtr++;
                continue;
            }
            if (winFreq[idx] < tFreq[idx]) {
                realLen++;
            }
            winFreq[idx]++;
            rightPtr++;
            while (realLen == t.length()) {
                if (rightPtr - leftPtr < ansLen) {
                    ansLen = rightPtr - leftPtr;
                    ansLeftPtr = leftPtr;
                }
                int leftIdx = s.charAt(leftPtr);
                if (tFreq[leftIdx] == 0) {
                    leftPtr++;
                    continue;
                }
                if (winFreq[leftIdx] == tFreq[leftIdx]) {
                    realLen--;
                }
                winFreq[leftIdx]--;
                leftPtr++;
            }
        }
        if (ansLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(ansLeftPtr, ansLeftPtr + ansLen);
    }
}
