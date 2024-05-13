package com.mengyu.algs4.exercise.leetcode.simulation;

/**
 * @author yu zhang
 */
public class Ex2079 {
    public int wateringPlants(int[] plants, int capacity) {
        int n = plants.length;
        int curCapacity = capacity;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt++;
            curCapacity -= plants[i];
            if (i < n - 1 && plants[i + 1] > curCapacity) {
                cnt += i + 1;
                cnt += i + 1;
                curCapacity = capacity;
            }
        }
        return cnt;
    }
}
