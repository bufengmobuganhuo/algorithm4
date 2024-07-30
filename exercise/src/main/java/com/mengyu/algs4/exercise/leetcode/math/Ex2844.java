package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex2844 {
    public static void main(String[] args) {
        System.out.println(new Ex2844().minimumOperations("2245047"));
    }

    public int minimumOperations2(String num) {
        int n = num.length();
        boolean find0 = false, find5 = false;
        for (int i = n - 1; i >= 0; i--) {
            char chr = num.charAt(i);
            if ((chr == '0' || chr == '5') && find0) {
                return n - i - 2;
            } else if (chr == '0') {
                find0 = true;
            } else if (chr == '5') {
                find5 = true;
            } else if ((chr == '2' || chr == '7') && find5) {
                return n - i - 2;
            }
        }
        return find0 ? n - 1 : n;
    }

    public int minimumOperations(String num) {
        int n = num.length(), ans = n;
        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '0') {
                ans = Math.min(ans, n - 1);
            }
            for (int j = i + 1; j < n; j++) {
                char chr1 = num.charAt(i);
                char chr2 = num.charAt(j);
                if ((chr1 == '0' && chr2 =='0') || (chr1 == '2' && chr2 == '5')
                        || (chr1 == '5' && chr2 == '0') || (chr1 == '7' && chr2 == '5')) {
                    ans = Math.min(ans, n - i - 2);
                }
            }
        }
        return ans;
    }
}
