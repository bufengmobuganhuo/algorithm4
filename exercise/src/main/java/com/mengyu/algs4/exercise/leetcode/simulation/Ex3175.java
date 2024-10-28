package com.mengyu.algs4.exercise.leetcode.simulation;

/**
 * @author yu zhang
 */
public class Ex3175 {
    public int findWinningPlayer(int[] skills, int k) {
        int n = skills.length;
        int winner = skills[0] > skills[1] ? 0 : 1;
        int cnt = 1;
        if (cnt >= k) {
            return winner;
        }
        for (int i = 2; i < n; i++) {
            if (skills[winner] < skills[i]) {
                winner = i;
                cnt = 1;
            } else {
                cnt++;
            }
            if (cnt >= k) {
                return winner;
            }
        }
        return winner;
    }
}
