package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex522_1 {
    public int findLUSlength(String[] strs) {
        int ans = -1;
        for (int i = 0; i < strs.length; i++) {
            boolean checked = true;
            for (int j = 0; j < strs.length; j++) {
                if (i != j && isSub(strs[i], strs[j])) {
                    checked = false;
                    break;
                }
            }
            if (checked) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    private boolean isSub(String s1, String s2) {
        int p1 = 0, p2 = 0;
        while (p1 < s1.length() && p2 < s2.length()) {
            if (s1.charAt(p1) == s2.charAt(p2)) {
                p1++;
            }
            p2++;
        }
        return p1 == s1.length();
    }
}
