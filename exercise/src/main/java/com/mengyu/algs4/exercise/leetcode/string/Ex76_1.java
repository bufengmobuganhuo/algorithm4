package com.mengyu.algs4.exercise.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/12/1 上午10:21
 * TODO
 */
public class Ex76_1 {
    public static void main(String[] args) {
        String s = "a";
        String t = "aa";
        Ex76_1 ex76 = new Ex76_1();
        System.out.println(ex76.minWindow2(s, t));
    }

    public String minWindow2(String source, String target) {
        int[] tChrFreq = new int[128];
        int[] winFreq = new int[128];
        for (int i = 0; i < target.length(); i++) {
            tChrFreq[target.charAt(i)]++;
        }
        int leftPtr = 0, rightPtr = 0, ansLeftPtr = 0, ansRightPtr = 0, realLen = 0;
        int minLen = Integer.MAX_VALUE;
        int sourceLen = source.length();
        int targetLen = target.length();
        while (rightPtr < sourceLen) {
            if (tChrFreq[source.charAt(rightPtr)] == 0) {
                rightPtr++;
                continue;
            }
            if (winFreq[source.charAt(rightPtr)] < tChrFreq[source.charAt(rightPtr)]) {
                realLen++;
            }
            winFreq[source.charAt(rightPtr)]++;
            rightPtr++;
            while (realLen == targetLen) {
                if (rightPtr - leftPtr < minLen) {
                    minLen = rightPtr - leftPtr;
                    ansLeftPtr = leftPtr;
                    ansRightPtr = rightPtr;
                }
                if (tChrFreq[source.charAt(leftPtr)] == 0) {
                    leftPtr++;
                    continue;
                }
                if (winFreq[source.charAt(leftPtr)] == tChrFreq[source.charAt(leftPtr)]) {
                    realLen--;
                }
                winFreq[source.charAt(leftPtr)]--;
                leftPtr++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : source.substring(ansLeftPtr, ansRightPtr);
    }

    public String minWindow(String source, String target) {
        Map<Character, Integer> ansChrMap = new HashMap<>();
        Map<Character, Integer> tChrMap = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            tChrMap.put(target.charAt(i), tChrMap.getOrDefault(target.charAt(i), 0) + 1);
        }
        int leftPtr = 0, rightPtr = -1, ansLeftPtr = -1, ansRightPtr = 0;
        int minLen = Integer.MAX_VALUE;
        int sLen = source.length();
        while (true) {
            rightPtr++;
            if (rightPtr >= sLen) {
                break;
            }
            char chr = source.charAt(rightPtr);
            if (tChrMap.containsKey(chr)) {
                ansChrMap.put(chr, ansChrMap.getOrDefault(chr, 0) + 1);
            }
            while (containsAll(ansChrMap, tChrMap) && leftPtr <= rightPtr) {
                if (rightPtr - leftPtr + 1 < minLen) {
                    minLen = rightPtr - leftPtr + 1;
                    ansLeftPtr = leftPtr;
                    ansRightPtr = rightPtr;
                }
                if (!tChrMap.containsKey(source.charAt(leftPtr))) {
                    leftPtr++;
                    continue;
                }
                ansChrMap.put(source.charAt(leftPtr), ansChrMap.get(source.charAt(leftPtr)) - 1);
                leftPtr++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : source.substring(ansLeftPtr, ansRightPtr + 1);
    }

    private boolean containsAll(Map<Character, Integer> ansChrMap, Map<Character, Integer> tChrMap) {
        for (Map.Entry<Character, Integer> entry : tChrMap.entrySet()) {
            if (entry.getValue() > ansChrMap.getOrDefault(entry.getKey(), 0)) {
                return false;
            }
        }
        return true;
    }
}
