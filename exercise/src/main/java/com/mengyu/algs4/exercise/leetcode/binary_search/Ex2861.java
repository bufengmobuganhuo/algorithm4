package com.mengyu.algs4.exercise.leetcode.binary_search;

import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex2861 {

    public static void main(String[] args) {
        List<List<Integer>> composition = Arrays.asList(Arrays.asList(8,3,4,2), Arrays.asList(3,9,5,5), Arrays.asList(1,7,9,8),Arrays.asList(7,6,5,1),Arrays.asList(4,6,9,4),Arrays.asList(6,8,7,1),Arrays.asList(5,10,3,4),Arrays.asList(10,1,2,4),Arrays.asList(10,3,7,2));
        List<Integer> stock = Arrays.asList(9,1,10,0);
        List<Integer> cost = Arrays.asList(3,4,9,9);
        System.out.println(new Ex2861().maxNumberOfAlloys(4, 9, 55, composition, stock, cost));
    }

    private int n;

    private int budget;

    private List<List<Integer>> composition;

    private List<Integer> stock;

    private List<Integer> cost;

    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        this.n = n;
        this.budget = budget;
        this.composition = composition;
        this.stock = stock;
        this.cost = cost;
        int ans = 0;
        for (int i = 0; i < k; i++) {
            int rightPtr = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                rightPtr = Math.min(rightPtr, (budget / cost.get(j) + stock.get(j)) / composition.get(i).get(j));
            }
            int leftPtr = 1;
            while (leftPtr <= rightPtr) {
                int midPtr = leftPtr + (rightPtr - leftPtr) / 2;
                if (check(midPtr, i)) {
                    ans = Math.max(ans, midPtr);
                    leftPtr = midPtr + 1;
                } else {
                    rightPtr = midPtr - 1;
                }
            }
        }
        return ans;
    }

    private boolean check(int cnt, int no) {
        long totalCost = 0;
        for (int i = 0; i < n; i++) {
            totalCost += (long) (Math.max(0, cnt * composition.get(no).get(i) - stock.get(i))) * cost.get(i);
            if (totalCost > budget) {
                return false;
            }
        }
        return totalCost <= budget;
    }
}
