package com.mengyu.algs4.exercise.leetcode.slide_window;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex2981 {

    public static void main(String[] args) {
        System.out.println(new Ex2981().maximumLength("aaaa"));
    }

    public int maximumLength(String s) {
        int n = s.length();
        int leftPtr = 0, rightPtr = 1;
        List<Integer>[] cntMap = new List[26];
        while (rightPtr < n + 1) {
            if (rightPtr == n || s.charAt(rightPtr) != s.charAt(leftPtr)) {
                int len = rightPtr - leftPtr;
                List<Integer> list = cntMap[s.charAt(leftPtr) - 'a'];
                if (cntMap[s.charAt(leftPtr) - 'a'] == null) {
                    cntMap[s.charAt(leftPtr) - 'a'] = new ArrayList<>();
                    list = cntMap[s.charAt(leftPtr) - 'a'];
                }
                for (int i = 1; i < len + 1; i++) {
                    int cnt = len - i + 1;
                    while (list.size() < i + 1) {
                        list.add(0);
                    }
                    list.set(i, list.get(i) + cnt);
                }
                leftPtr = rightPtr;
            }
            rightPtr++;
        }
        int ans = -1;
        for (List<Integer> list : cntMap){
            if (list == null) {
                continue;
            }
            for (int i = list.size() - 1; i > 0; i--) {
                if (list.get(i) >= 3) {
                    ans = Math.max(ans, i);
                    break;
                }
            }
        }
        return ans;
    }
}
