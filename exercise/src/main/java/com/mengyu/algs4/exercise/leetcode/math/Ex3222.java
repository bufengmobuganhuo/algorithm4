package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex3222 {

    public static void main(String[] args) {
        System.out.println(new Ex3222().losingPlayer(2, 7));
    }

    public String losingPlayer(int x, int y) {
        int cnt1 = x, cnt2 = y / 4;
        int cnt = Math.min(cnt1, cnt2);
        return cnt % 2 == 0 ? "Bob" : "Alice";
    }
}
