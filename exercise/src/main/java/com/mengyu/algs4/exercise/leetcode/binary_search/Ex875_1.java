package com.mengyu.algs4.exercise.leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex875_1 {
    public static void main(String[] args) {
        int[] piles = {312884470};
        System.out.println(new Ex875_1().minEatingSpeed(piles, 968709470));
    }
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(piles, mid, h)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] piles, int k, int h) {
        int count = 0;
        for (int pile : piles) {
            count += (pile + k - 1) / k;
        }
        return count <= h;
    }
}
