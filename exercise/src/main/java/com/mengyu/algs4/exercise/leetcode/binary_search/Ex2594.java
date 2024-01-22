package com.mengyu.algs4.exercise.leetcode.binary_search;

/**
 * @author yu zhang
 */
public class Ex2594 {
    public static void main(String[] args) {
        System.out.println(new Ex2594().repairCars(new int[]{3}, 52));
    }

    public long repairCars(int[] ranks, int cars) {
        long leftPtr = 0, rightPtr = (long) ranks[0] * cars * cars;
        long ans = rightPtr;
        while (leftPtr < rightPtr) {
            long time = leftPtr + (rightPtr - leftPtr) / 2;
            if (check(ranks, cars, time)) {
                ans = time;
                rightPtr = time;
            } else {
                leftPtr = time + 1;
            }
        }
        return ans;
    }

    private boolean check(int[] ranks, int cars, long time) {
        long cnt = 0;
        for (int rank : ranks) {
            cnt += Math.sqrt(time / rank);
        }
        return cnt >= cars;
    }
}
