package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex76_3 {

    public static void main(String[] args) {
        System.out.println(new Ex76_3().minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        int[] tFreq = new int[128];
        int[] winFreq = new int[128];
        int sl = s.length(), tl = t.length();
        for (int i = 0; i < tl; i++) {
            tFreq[t.charAt(i)]++;
        }
        int ansLeft = 0, ansLen = Integer.MAX_VALUE;
        int l = 0, r = 0;
        int realLen = 0;
        while (r < sl) {
            if (tFreq[s.charAt(r)] == 0) {
                r++;
                continue;
            }
            if (winFreq[s.charAt(r)] < tFreq[s.charAt(r)]) {
                realLen++;
            }
            winFreq[s.charAt(r)]++;
            r++;
            while (realLen == tl) {
                if (r - l < ansLen) {
                    ansLeft = l;
                    ansLen = r - l;
                }
                if (tFreq[s.charAt(l)] == 0) {
                    l++;
                    continue;
                }
                if (winFreq[s.charAt(l)] == tFreq[s.charAt(l)]) {
                    realLen--;
                }
                winFreq[s.charAt(l)]--;
                l++;
            }
        }
        if (ansLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(ansLeft, ansLeft + ansLen);
    }
}
