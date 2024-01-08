package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yu zhang
 */
public class Ex2706 {

    public static void main(String[] args) {
        System.out.println(new Ex2706().buyChoco(new int[]{1, 2, 2}, 3));
    }

    public int buyChoco(int[] prices, int money) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int price : prices) {
            if (min1 > price) {
                min2 = min1;
                min1 = price;
            } else if (min2 > price) {
                min2 = price;
            }
        }
        int ans = money - min1 - min2;
        return ans >= 0 ? ans : money;
    }
}
