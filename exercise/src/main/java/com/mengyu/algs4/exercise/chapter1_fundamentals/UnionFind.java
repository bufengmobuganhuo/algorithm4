package com.mengyu.algs4.exercise.chapter1_fundamentals;

/**
 * @author yu zhang
 */
public class UnionFind {
    private int[] ids;

    private int[] weights;

    public UnionFind(int capacity) {
        ids = new int[capacity];
        weights = new int[capacity];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
            weights[i] = 1;
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

    public boolean connected(int point1, int point2) {
        return find(point1) == find(point2);
    }

    private int find(int point) {
        while (point != ids[point]) {
            ids[point] = ids[ids[point]];
            point = ids[point];
        }
        return point;
    }
}
