package com.mengyu.algs4.exercise.offer.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex087 {
    public static void main(String[] args) {
        System.out.println(new Ex087().restoreIpAddresses("10203040"));
    }

    private List<String> ans;

    public List<String> restoreIpAddresses(String s) {
        ans = new ArrayList<>();
        backtracking(s, 0, new StringBuilder(), 4);
        return ans;
    }

    private void backtracking(String s, int idx, StringBuilder sb, int count) {
        if (idx >= s.length() && count == 0) {
            ans.add(sb.toString());
            return;
        }
        int leftLen = s.length() - sb.length() + (4 - count);
        if (leftLen - count * 3 > 0 || leftLen - count < 0) {
            return;
        }
        int minSubLen = leftLen - (count - 1) * 3;
        if (minSubLen > 1 && s.charAt(idx) == '0') {
        } else if (s.charAt(idx) == '0') {
            sb.append("0");
            if (count > 1) {
                sb.append(".");
            }
            backtracking(s, idx + 1, sb, count - 1);
            sb.delete(sb.length() - (count > 1 ? 2 : 1), sb.length());
        } else {
            for (int i = Math.max(minSubLen, 1); i < 4 && s.length() >= idx + i; i++) {
                String num = s.substring(idx, idx + i);
                if (Integer.parseInt(num) > 255) {
                    return;
                }
                sb.append(num);
                if (count > 1) {
                    sb.append(".");
                }
                backtracking(s, idx + i, sb, count - 1);
                sb.delete(sb.length() - i - (count > 1 ? 1 : 0), sb.length());
            }
        }
    }
}
