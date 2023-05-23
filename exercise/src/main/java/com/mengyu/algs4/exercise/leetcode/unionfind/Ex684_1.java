package com.mengyu.algs4.exercise.leetcode.unionfind;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/2/2 上午9:29
 * TODO
 */
public class Ex684_1 {
    public static void main(String[] args) {
        Ex684_1 ex684_1 = new Ex684_1();
        int[][] edges = {
                {1,2}, {2,3}, {3,4}, {1,4}, {1,5}
        };
        System.out.println(Arrays.toString(ex684_1.findRedundantConnection(edges)));
    }
    public int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];
        UnionFind unionFind = new UnionFind(edges.length);
        for (int[] edge : edges) {
            if (unionFind.connect(edge[0],edge[1])){
                res=edge;
            }
        }
        return res;
    }

    static class UnionFind {
        int[] ids;
        int[] weights;

        public UnionFind(int cap) {
            ids = new int[cap + 1];
            weights = new int[cap + 1];
            for (int i = 0; i < cap + 1; i++) {
                ids[i] = i;
                weights[i] = 1;
            }
        }

        public boolean connect(int point1, int point2) {
            int root1 = find(point1);
            int root2 = find(point2);
            if (root1 == root2) {
                return true;
            }
            if (weights[root1] > weights[root2]) {
                ids[root2] = root1;
                weights[root1] += weights[root2];
            } else {
                ids[root1] = root2;
                weights[root2] += weights[root1];
            }
            return false;
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
