package com.mengyu.algs4.exercise.bytedance.jan27th;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/1/27 上午11:00
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        Ex2 ex2 = new Ex2();
        String s = "aab";
        System.out.println(ex2.partition(s));
    }

    private List<List<String>> res;
    private boolean[][] dp;

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        res = new ArrayList<>();
        backtracking(s, 0, new LinkedList<>());
        return res;
    }

    private void backtracking(String s, int start, LinkedList<String> track) {
        if (start == s.length()) {
            List<String> list = new ArrayList<>(track);
            res.add(list);
            return;
        }
        for (int i = 1; i <= s.length() - start; i++) {
            String subStr = s.substring(start, start + i);
            if (!isPalindromeStr(s, start, start + i - 1)) {
                continue;
            }
            track.offerLast(subStr);
            backtracking(s, start + i, track);
            track.removeLast();
        }
    }

    private boolean isPalindromeStr2(String str, int leftPtr, int rightPtr) {
        int len = str.length();
        if (dp == null) {
            dp = new boolean[len][len];
            for (int i = len - 1; i >= 0; i--) {
                for (int j = i; j < len; j++) {
                    dp[i][j] = str.charAt(i) == str.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                }
            }
        }
        return dp[leftPtr][rightPtr];
    }

    /**
     * 笨办法，判断是否是回文
     *
     * @param str
     * @return
     */
    private boolean isPalindromeStr(String str, int leftPtr, int rightPtr) {
        while (leftPtr < rightPtr) {
            if (str.charAt(leftPtr) != str.charAt(rightPtr)) {
                return false;
            }
            leftPtr++;
            rightPtr--;
        }
        return true;
    }
}
