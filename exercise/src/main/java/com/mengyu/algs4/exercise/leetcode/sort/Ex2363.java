package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex2363 {
    public static void main(String[] args) {
        int[][] items1 = {{1,1},{4,5},{3,8}};
        int[][] items2 = {{3,1},{1,5}};
        System.out.println(new Ex2363().mergeSimilarItems(items1, items2));
    }
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Arrays.sort(items1, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(items2, Comparator.comparingInt(o -> o[0]));
        List<List<Integer>> ans = new ArrayList<>();
        int i = 0, j = 0;
        while (i < items1.length || j < items2.length) {
            if (i >= items1.length) {
                add(ans, items2[j][0], items2[j][1]);
                j++;
            } else if (j >= items2.length) {
                add(ans, items1[i][0], items1[i][1]);
                i++;
            } else if (items1[i][0] <= items2[j][0]) {
                add(ans, items1[i][0], items1[i][1]);
                i++;
            } else {
                add(ans, items2[j][0], items2[j][1]);
                j++;
            }
        }
        return ans;
    }

    private void add(List<List<Integer>> ans, int val, int weight) {
        if (ans.size() > 0) {
            List<Integer> list = ans.get(ans.size() - 1);
            if (list.get(0) == val) {
                list.set(1, list.get(1) + weight);
            } else {
                ans.add(Arrays.asList(val, weight));
            }
        } else {
            ans.add(Arrays.asList(val, weight));
        }
    }
}
