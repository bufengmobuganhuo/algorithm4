package com.mengyu.algs4.exercise.leetcode.pointer;

import java.awt.Color;

/**
 * @author yu zhang
 */
public class Ex522 {

    public static void main(String[] args) {
        int red = Color.BLUE.getRed();
        int green = Color.BLUE.getGreen();
        int blue = Color.BLUE.getBlue();
        System.out.println("red:" + red);
        System.out.println("green:" + green);
        System.out.println("blue:"+blue);
        String hex = String.format("#%02X%02X%02X", red, green, blue);
        System.out.println(hex);
    }

    public int findLUSlength(String[] strs) {
        int ans = -1, n = strs.length;
        for (int i = 0; i < n; i++) {
            boolean check = true;
            for (int j = 0; j < n; j++) {
                if (i != j && isSub(strs[i], strs[j])) {
                    check = false;
                    break;
                }
            }
            if (check) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    private boolean isSub(String s, String t) {
        int sPtr = 0, tPtr = 0;
        while (tPtr < t.length() && sPtr < s.length()) {
            if (s.charAt(sPtr) == t.charAt(tPtr)) {
                sPtr++;
            }
            tPtr++;
        }
        return sPtr == s.length();
    }
}
