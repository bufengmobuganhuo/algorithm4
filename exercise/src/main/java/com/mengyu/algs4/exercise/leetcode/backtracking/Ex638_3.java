package com.mengyu.algs4.exercise.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex638_3 {
    private Map<List<Integer>, Integer> needsPriceMap;

    private int res;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // needsPriceMap = new HashMap<>();
        res = Integer.MAX_VALUE;
        shopping(price, special, needs, 0, 0);
        return res;
    }

    // 更快解法
    private void shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int cost, int idx) {
        if (res <= cost) {
            return;
        }
        if (idx == special.size()) {
            cost += dot(price, needs);
            res = Math.min(res, cost);
            return;
        }
        List<Integer> pkg = special.get(idx);
        // 如果当前这个背包还可以用，则用他，这里比官方解法快的原因是，官方解法一定会从头开始找背包，而这里过滤掉了不能使用的
        if (canBuy(pkg, needs)) {
            List<Integer> leftNeeds = new ArrayList<>();
            for (int i = 0; i < needs.size(); i++) {
                leftNeeds.add(needs.get(i) - pkg.get(i));
            }
            shopping(price, special, leftNeeds, cost + pkg.get(needs.size()), idx);
        }
        // 再往下找package
        shopping(price, special, needs, cost, idx + 1);
    }

    private boolean canBuy(List<Integer> pkg, List<Integer> needs) {
        for (int i = 0; i < needs.size(); i++) {
            if (pkg.get(i) > needs.get(i)) {
                return false;
            }
        }
        return true;
    }

    // 官方解法，记忆化搜索
    private int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (needsPriceMap.containsKey(needs)) {
            return needsPriceMap.get(needs);
        }
        int minCost = dot(price, needs);
        for (List<Integer> pkg : special) {
            int i = 0;
            List<Integer> leftNeeds = new ArrayList<>(needs);
            for (; i < needs.size(); i++) {
                int diff = needs.get(i) - pkg.get(i);
                if (diff < 0) {
                    break;
                }
                leftNeeds.set(i, diff);
            }
            if (i == needs.size()) {
                minCost = Math.min(minCost, pkg.get(i) + shopping(price, special, leftNeeds));
            }
        }
        needsPriceMap.put(needs, minCost);
        return minCost;
    }

    private int dot(List<Integer> price, List<Integer> needs) {
        int cost = 0;
        for (int i = 0; i < needs.size(); i++) {
            cost += needs.get(i) * price.get(i);
        }
        return cost;
    }
}
