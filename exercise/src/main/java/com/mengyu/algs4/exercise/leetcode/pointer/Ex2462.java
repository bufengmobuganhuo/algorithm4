package com.mengyu.algs4.exercise.leetcode.pointer;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex2462 {

    public static void main(String[] args) {
        System.out.println(new Ex2462().totalCost2(new int[]{31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58}, 11, 2));
    }

    public long totalCost2(int[] costs, int k, int candidates) {
        int n = costs.length;
        int leftPtr = candidates - 1, rightPtr = n - candidates;
        PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> {
            if(o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        if (leftPtr + 1 < rightPtr) {
            for (int i = 0; i < candidates; i++) {
                que.offer(new int[]{costs[i], i});
                que.offer(new int[]{costs[n - i - 1], n - i - 1});
            }
        } else {
            for (int i = 0; i < n; i++) {
                que.offer(new int[]{costs[i], i});
            }
        }
        long ans = 0l;
        for (int i = 0; i < k; i++) {
            int[] cost = que.poll();
            ans += cost[0];
            if (leftPtr + 1 < rightPtr) {
                if (cost[1] <= leftPtr) {
                    leftPtr++;
                    que.offer(new int[]{costs[leftPtr], leftPtr});
                } else {
                    rightPtr--;
                    que.offer(new int[]{costs[rightPtr], rightPtr});
                }
            }
        }
        return ans;
    }


    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        int leftPtr = 0, rightPtr = n - 1;
        PriorityQueue<int[]> leftQue = new PriorityQueue<>((o1, o2) -> {
            if(o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        PriorityQueue<int[]> rightQue = new PriorityQueue<>((o1, o2) -> {
            if(o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        long ans = 0;
        for (int i = 0; i < candidates; i++) {
            leftQue.offer(new int[]{costs[leftPtr], leftPtr});
            leftPtr++;
        }
        for (int i = 0; i < candidates && rightPtr > leftPtr; i++) {
            rightQue.offer(new int[]{costs[rightPtr], rightPtr});
            rightPtr--;
        }
        for (int i = 0; i < k; i++) {
            int[] cost1 = leftQue.isEmpty() ? new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE} : leftQue.peek();
            int[] cost2 = rightQue.isEmpty() ? new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE} : rightQue.peek();
            if (cost1[0] < cost2[0] || (cost1[0] == cost2[0] && cost1[1] < cost2[1])) {
                ans += cost1[0];
                leftQue.poll();
                System.out.println(Arrays.toString(cost1));
                if (leftPtr < n && leftPtr <= rightPtr) {
                    leftQue.offer(new int[]{costs[leftPtr], leftPtr});
                    leftPtr++;
                }
            } else if (cost1[0] > cost2[0] || cost1[0] == cost2[0]) {
                ans += cost2[0];
                rightQue.poll();
                System.out.println(Arrays.toString(cost2));
                if (rightPtr > -1 && rightPtr >= leftPtr) {
                    rightQue.offer(new int[]{costs[rightPtr], rightPtr});
                    rightPtr--;
                }
            }
        }
        return ans;
    }
}
