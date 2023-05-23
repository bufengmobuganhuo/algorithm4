package com.mengyu.algs4.exercise.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/3/12 上午10:05
 * TODO
 */
public class Ex131_1 {
    public static void main(String[] args) {
        Ex131_1 ex131_1 = new Ex131_1();
        ex131_1.partition("aab");
    }
    private List<List<String>> ans;

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        ans = new ArrayList<>();
        backtracking(s, 0, new LinkedList<>());
        return ans;
    }

    private void backtracking(String str, int startIdx, LinkedList<String> track) {
        if (startIdx >= str.length()) {
            List<String> tmp = new ArrayList<>(track);
            ans.add(tmp);
            return;
        }
        for (int i = 1; i < str.length() - startIdx + 1; i++) {
            if (!isPalindromeStr(str, startIdx, startIdx + i - 1)) {
                continue;
            }
            track.offerLast(str.substring(startIdx, startIdx + i));
            backtracking(str,startIdx+i,track);
            track.pollLast();
        }
    }

    private boolean isPalindromeStr(String str, int leftPtr, int rightPtr) {
        while (leftPtr <= rightPtr) {
            if (str.charAt(leftPtr) != str.charAt(rightPtr)) {
                return false;
            }
            leftPtr++;
            rightPtr--;
        }
        return true;
    }
}
