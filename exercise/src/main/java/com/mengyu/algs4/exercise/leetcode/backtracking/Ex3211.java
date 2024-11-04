package com.mengyu.algs4.exercise.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex3211 {
    public List<String> validStrings(int n) {
        List<String> ans = new ArrayList<>();
        backtracking(new StringBuilder(), n , ans);
        return ans;
    }

    private void backtracking(StringBuilder sb, int n, List<String> ans) {
        if (sb.length() == n) {
            ans.add(sb.toString());
            return;
        }
        if (sb.length() == 0 || sb.charAt(sb.length() - 1) != '0') {
            sb.append('0');
            backtracking(sb, n, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append('1');
        backtracking(sb, n, ans);
        sb.deleteCharAt(sb.length() - 1);
    }
}
