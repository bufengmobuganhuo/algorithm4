package com.mengyu.algs4.exercise.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex638_4 {
    private int ans;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        ans = Integer.MAX_VALUE;
        shopping(price, special, needs, 0, 0);
        return ans;
    }

    // 先用大礼包购买，不够了之后再单独购买，有些大礼包无法购买后，下次肯定也无法购买，直接略过
    private void shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int curCost, int idx) {
        if (ans <= curCost) {
            // 之前的结果比当前方案花费更少，没有必要计算下去
            return;
        }
        if (idx == special.size()) {
            // 大礼包用完了，单独购买
            curCost += dot(price, needs);
            ans = Math.min(ans, curCost);
            return;
        }
        List<Integer> pkg = special.get(idx);
        if (canBuy(pkg, needs)) {
            // 当前大礼包还可以继续购买
            List<Integer> leftNeed = new ArrayList<>();
            for (int i = 0; i < needs.size(); i++) {
                leftNeed.add(needs.get(i) - pkg.get(i));
            }
            // 继续尝试使用该大礼包购买
            shopping(price, special, leftNeed, curCost + pkg.get(pkg.size() - 1), idx);
        }
        // 不用当前大礼包
        shopping(price, special, needs, curCost, idx + 1);
    }

    private boolean canBuy(List<Integer> pkg, List<Integer> needs) {
        for (int i = 0; i < needs.size(); i++) {
            if (pkg.get(i) > needs.get(i)) {
                return false;
            }
        }
        return true;
    }

    // 使用回溯，逐个尝试大礼包
    private int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 单独购买
        int cost = dot(price, needs);
        for (List<Integer> pkg : special) {
            List<Integer> leftNeed = new ArrayList<>(needs);
            int idx = 0;
            for (; idx < needs.size(); idx++) {
                int left = leftNeed.get(idx) - pkg.get(idx);
                if (left < 0) {
                    break;
                }
                leftNeed.set(idx, left);
            }
            // 大礼包可以购买
            if (idx == needs.size()) {
                cost = Math.min(cost, shopping(price, special, leftNeed) + pkg.get(idx));
            }
        }
        return cost;
    }

    private int dot(List<Integer> price, List<Integer> needs) {
        int sum = 0;
        for (int i = 0; i < price.size(); i++) {
            sum += price.get(i) * needs.get(i);
        }
        return sum;
    }
}
