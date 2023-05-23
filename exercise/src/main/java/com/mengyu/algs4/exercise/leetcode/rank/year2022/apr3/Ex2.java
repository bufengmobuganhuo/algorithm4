package com.mengyu.algs4.exercise.leetcode.rank.year2022.apr3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yuzhang
 * @date 2022/4/3 10:38 AM
 * TODO
 */
public class Ex2 {
    public List<List<Integer>> findWinners(int[][] matches) {
        Set<Integer> winners = new HashSet<>();
        Map<Integer, Integer> losers = new HashMap<>();
        for (int[] match : matches) {
            winners.add(match[0]);
            losers.put(match[1], losers.getOrDefault(match[1], 0) + 1);
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> neverLose = new ArrayList<>();
        for (int winner : winners) {
            if (!losers.containsKey(winner)) {
                neverLose.add(winner);
            }
        }
        List<Integer> loseOnce = new ArrayList<>();
        for (Map.Entry<Integer, Integer> loser : losers.entrySet()) {
            if (loser.getValue() == 1) {
                loseOnce.add(loser.getKey());
            }
        }
        ans.add(neverLose.stream().sorted().collect(Collectors.toList()));
        ans.add(loseOnce.stream().sorted().collect(Collectors.toList()));
        return ans;
    }
}
