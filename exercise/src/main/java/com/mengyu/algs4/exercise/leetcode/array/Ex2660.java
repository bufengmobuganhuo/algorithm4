package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yu zhang
 */
public class Ex2660 {
    public int isWinner(int[] player1, int[] player2) {
        int n = player1.length;
        int val1 = 0, val2 = 0;
        int[] pre1 = new int[2];
        int[] pre2 = new int[2];
        for (int i = 0; i < n; i++) {
            val1 += (pre1[0] == 10 || pre1[1] == 10) ? 2 * player1[i] : player1[i];
            val2 += (pre2[0] == 10 || pre2[1] == 10) ? 2 * player2[i] : player2[i];
            pre1[0] = pre1[1];
            pre1[1] = player1[i];
            pre2[0] = pre2[1];
            pre2[1] = player2[i];
        }
        return val1 > val2 ? 1 : (val1 == val2 ? 0 : 2);
    }
}
