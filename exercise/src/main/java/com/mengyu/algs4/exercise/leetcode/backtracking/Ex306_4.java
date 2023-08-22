package com.mengyu.algs4.exercise.leetcode.backtracking;

/**
 * @author yu zhang
 */
public class Ex306_4 {

    public static void main(String[] args) {
        System.out.println(new Ex306_4().isAdditiveNumber("199100199"));
    }

    public boolean isAdditiveNumber(String num) {
        return backtracking(num, 0, 0, 0, 0);
    }

    private boolean backtracking(String num, int idx, long lastNum, long target, int cnt) {
        if (idx == num.length()) {
            return cnt >= 3;
        }
        long cur = 0;
        for (int i = idx; i < num.length(); i++) {
            if (num.charAt(idx) == '0' && i > idx) {
                continue;
            }
            cur = cur * 10 + num.charAt(i) - '0';
            if (cnt >= 2) {
                if (cur < target) {
                    continue;
                } else if (cur > target) {
                    break;
                }
            }
            if (backtracking(num, i + 1, cur, cur + lastNum, cnt + 1)) {
                return true;
            }
        }

        return false;
    }
}
