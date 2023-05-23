package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yuzhang
 * @date 2021/7/6 上午7:08
 * TODO
 */
public class Ex1418 {
    public static void main(String[] args) {

    }
    public List<List<String>> displayTable(List<List<String>> orders) {
        Map<Integer, Map<String, Integer>> countMap = new HashMap<>();
        Set<String> foodItemSet = new HashSet<>();
        for (List<String> order : orders) {
            int tableNumber = Integer.parseInt(order.get(1));
            String foodItem = order.get(2);
            Map<String, Integer> foodItemMap = countMap.computeIfAbsent(tableNumber, key -> new HashMap<>());
            foodItemMap.put(foodItem, foodItemMap.getOrDefault(foodItem, 0) + 1);
            foodItemSet.add(foodItem);
        }
        List<String> foodItem = foodItemSet.stream().sorted().collect(Collectors.toList());
        List<Integer> tableNumberList = countMap.keySet().stream().sorted().collect(Collectors.toList());
        List<List<String>> ans = new ArrayList<>();
        foodItem.add(0, "Table");
        ans.add(foodItem);
        for (int tableNumber : tableNumberList) {
            Map<String, Integer> foodItemMap = countMap.get(tableNumber);
            List<String> table = new ArrayList<>();
            table.add(String.valueOf(tableNumber));
            for (int j = 1; j < foodItem.size(); j++) {
                table.add(String.valueOf(foodItemMap.getOrDefault(foodItem.get(j), 0)));
            }
            ans.add(table);
        }
        return ans;
    }
}
