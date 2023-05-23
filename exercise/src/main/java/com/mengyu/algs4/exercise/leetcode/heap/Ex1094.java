package com.mengyu.algs4.exercise.leetcode.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex1094 {
    public static void main(String[] args) {
        int[][] trips = {
            {2, 1, 5},
            {3, 3, 7}
        };
        System.out.println(new Ex1094().carPooling(trips, 4));
    }

    /**
     * 使用差分数组：n
     */
    public boolean carPooling2(int[][] trips, int capacity) {
        // 找到最远的站台
        int farthestPlatform = 0;
        for (int[] trip : trips) {
            farthestPlatform = Math.max(farthestPlatform, trip[2]);
        }
        // diff[i],在站台i上下车后，车上的净变化人数
        int[] diff = new int[farthestPlatform + 1];
        for (int[] trip : trips) {
            // 在站台trip[1]车上增加了trip[0]
            diff[trip[1]] += trip[0];
            // 在站台[2]车上减少了trip[0]
            diff[trip[2]] -= trip[0];
        }
        int start = diff[0];
        if (start > capacity) {
            return false;
        }
        for (int i = 1; i < diff.length; i++) {
            // 在某个站台，或者时刻的人数=初始状态 + 净变化数
            start += diff[i];
            if (start > capacity) {
                return false;
            }
        }
        return true;
    }

    /**
     * 使用优先队列；nlogn
     */
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        PriorityQueue<int[]> endQue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        for (int i = 0; i < trips.length; i++) {
            int[] start = trips[i];
            while (!endQue.isEmpty() && endQue.peek()[2] <= start[1]) {
                capacity += endQue.poll()[0];
            }
            if (capacity < start[0]) {
                return false;
            }
            endQue.offer(start);
            capacity -= start[0];
        }
        return true;
    }
}
