package com.mengyu.algs4.exercise.leetcode.rank.year2023.august20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @date 2023/8/20 10:44
 * TODO
 */
public class Ex3 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(1);
        List<Integer> list1 = new ArrayList<>();
        list1.add(0);
        list1.add(2);
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(3);
        list2.add(2);
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list);
        lists.add(list1);
        lists.add(list2);
        System.out.println(new Ex3().maximizeTheProfit(5, lists));
    }

    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        // dp[i + 1]: 销售编号不超过i的房屋的最大盈利。
        // dp[0]: 销售编号不超过-1的房屋的最大盈利，因为没有这个编号，则获利为0
        int[] dp = new int[n + 1];
        // 以offer[1]（记为end）分组
        List<int[]>[] groups = new ArrayList[n];
        Arrays.setAll(groups, e -> new ArrayList<>());
        for (List<Integer> offer : offers) {
            groups[offer.get(1)].add(new int[]{offer.get(0), offer.get(2)});
        }
        for (int i = 0; i < n; i++) {
            // 如果不卖
            dp[i + 1] = dp[i];
            // 如果卖，那么遍历所有end_j=i的购买请求，dp[i+1] = max{dp[start_j] + gold_j}
            // 在dp[start_j]后面的肯定没卖过，dp[start_j]是销售[0...start_j - 1]的盈利
            for (int[] offer : groups[i]) {
                dp[i + 1] = Math.max(dp[i + 1], dp[offer[0]] + offer[1]);
            }
        }
        return dp[n];
    }

}
