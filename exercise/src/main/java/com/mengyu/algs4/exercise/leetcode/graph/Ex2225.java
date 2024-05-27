package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex2225 {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> winners = new HashMap<>();
        Map<Integer, Integer> losers = new HashMap<>();
        for (int[] match : matches) {
            int winner = match[0], loser = match[1];
            winners.put(winner, winners.getOrDefault(winner, 0) + 1);
            losers.put(loser, losers.getOrDefault(loser, 0) + 1);
        }
        List<Integer> allWin = new ArrayList<>();
        for (int winner : winners.keySet()) {
            if (losers.containsKey(winner)) {
                continue;
            }
            allWin.add(winner);
        }
        List<Integer> lose1 = new ArrayList<>();
        for (int loser : losers.keySet()) {
            if (losers.get(loser) == 1) {
                lose1.add(loser);
            }
        }
        Collections.sort(allWin);
        Collections.sort(lose1);
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(allWin);
        ans.add(lose1);
        return ans;
    }
}
