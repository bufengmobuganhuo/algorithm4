package com.mengyu.algs4.exercise.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/3/10 上午9:34
 * TODO
 */
public class Ex638_1 {
    public static void main(String[] args) {
        List<Integer> price = Arrays.asList(2, 5);
        List<List<Integer>> special = new ArrayList<>();
        special.add(Arrays.asList(3, 0, 5));
        special.add(Arrays.asList(1, 2, 10));
        List<Integer> needs = Arrays.asList(3, 2);
        Ex638_1 ex638 = new Ex638_1();
        System.out.println(ex638.shoppingOffers(price, special, needs));
    }


    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs(price, special, needs);
    }

    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> map) {
        if (map.containsKey(needs)) {
            return map.get(needs);
        }
        int j = 0;
        int cost = dot(price, needs);
        for (List<Integer> pkg : special) {
            List<Integer> leftNeeds = new ArrayList<>(needs);
            for (j = 0; j < needs.size(); j++) {
                int left = leftNeeds.get(j) - pkg.get(j);
                if (left < 0) {
                    break;
                }
                leftNeeds.set(j, left);
            }
            if (j == needs.size()) {
                cost = Math.min(cost, pkg.get(j) + dfs(price, special, leftNeeds, map));
            }
        }
        map.put(needs, cost);
        return cost;
    }

    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int j = 0;
        int cost = dot(price, needs);
        for (List<Integer> pkg : special) {
            List<Integer> leftNeeds = new ArrayList<>(needs);
            for (j = 0; j < needs.size(); j++) {
                int left = leftNeeds.get(j) - pkg.get(j);
                if (left < 0) {
                    break;
                }
                leftNeeds.set(j, left);
            }
            if (j == needs.size()) {
                cost = Math.min(cost, pkg.get(j) + dfs(price, special, leftNeeds));
            }
        }
        return cost;
    }

    private int dot(List<Integer> price, List<Integer> needs) {
        int cost = 0;
        for (int i = 0; i < needs.size(); i++) {
            cost += price.get(i) * needs.get(i);
        }
        return cost;
    }
}
