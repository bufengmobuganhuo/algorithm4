package com.mengyu.algs4.exercise.leetcode.unionfind;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex947_3 {
    public int removeStones(int[][] stones) {
        int[] ids = new int[20000];
        int[] weights = new int[20000];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
            weights[i] = 1;
        }
        for (int[] stone : stones) {
            connect(ids, weights, stone[0], stone[1] + 10000);
        }
        for (int[] stone : stones) {
            set.add(find(ids, stone[0]));
        }
        return stones.length - set.size();
    }

    private int find(int[] ids, int point) {
        while (point != ids[point]) {
            ids[point] = ids[ids[point]];
            point = ids[point];
        }
        return point;
    }

    private void connect(int[] ids, int[] weights, int point1, int point2) {
        int root1 = find(ids, point1);
        int root2 = find(ids, point2);
        if (weights[root1] < weights[root2]){
            weights[root2] += weights[root1];
            ids[root1] = root2;
        }else {
            weights[root1] += weights[root2];
            ids[root2] = root1;
        }
    }
}
