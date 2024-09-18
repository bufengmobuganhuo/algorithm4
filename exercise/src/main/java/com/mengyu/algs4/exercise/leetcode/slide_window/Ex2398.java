package com.mengyu.algs4.exercise.leetcode.slide_window;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yu zhang
 */
public class Ex2398 {

    public static void main(String[] args) {
        int[] chargeTimes = {11, 12, 19}, runningCosts = {10, 8, 7};
        System.out.println(new Ex2398().maximumRobots(chargeTimes, runningCosts, 19));
    }

    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        Deque<Integer> deque = new ArrayDeque<>();
        long curCostsSum = 0;
        int ans = 0;
        int leftPtr = -1, rightPtr = 0, n = chargeTimes.length;
        while (rightPtr < n) {
            curCostsSum += runningCosts[rightPtr];
            while (!deque.isEmpty() && chargeTimes[deque.peekLast()] <= chargeTimes[rightPtr]) {
                deque.pollLast();
            }
            deque.offerLast(rightPtr);
            int k = rightPtr - leftPtr;
            long cost = chargeTimes[deque.peekFirst()] + k * curCostsSum;
            while (leftPtr < rightPtr && cost > budget) {
                leftPtr++;
                if (!deque.isEmpty() && deque.peekFirst() == leftPtr) {
                    deque.pollFirst();
                }
                curCostsSum -= runningCosts[leftPtr];
                k--;
                if (deque.isEmpty()) {
                    break;
                }
                cost = chargeTimes[deque.peekFirst()] + k * curCostsSum;
            }
            ans = Math.max(ans, k);
            rightPtr++;
        }
        return ans;
    }
}
