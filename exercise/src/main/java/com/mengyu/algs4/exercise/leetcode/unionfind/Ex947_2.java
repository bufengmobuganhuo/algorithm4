package com.mengyu.algs4.exercise.leetcode.unionfind;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/3/22 上午8:58
 * TODO
 */
public class Ex947_2 {
    public int removeStones(int[][] stones) {
        WeightUnionFind weightUnionFind = new WeightUnionFind(20000);
        for (int[] stone : stones) {
            weightUnionFind.connect(stone[0], stone[1] + 10000);
        }
        Set<Integer> unique = new HashSet<>();
        for (int[] stone : stones) {
            unique.add(weightUnionFind.find(stone[0]));
        }
        return stones.length-unique.size();
    }

    static class WeightUnionFind {
        int[] ids;
        int[] weights;

        public WeightUnionFind(int cap) {
            ids = new int[cap];
            weights = new int[cap];
            for (int i = 0; i < cap; i++) {
                ids[i]=i;
                weights[i]=1;
            }
        }

        public void connect(int point1, int point2) {
            int root1 = find(point1);
            int root2 = find(point2);
            if (root1 == root2) {
                return;
            }
            if (weights[root1] > weights[root2]) {
                ids[root2] = root1;
                weights[root1] += weights[root2];
            } else {
                ids[root1] = root2;
                weights[root2] += weights[root1];
            }
        }

        private int find(int point) {
            while (point != ids[point]) {
                ids[point] = ids[ids[point]];
                point = ids[point];
            }
            return point;
        }
    }
}
