package com.mengyu.algs4.exercise.leetcode.unionfind;

/**
 * @author yu zhang
 */
public class Ex959_2 {
    public static void main(String[] args) {
        String[] grid = {"/\\", "\\/"};
        Ex959_2 ex959_2 = new Ex959_2();
        System.out.println(ex959_2.regionsBySlashes(grid));
    }
    private int[] weights;
    private int[] roots;
    private int count;

    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        init(4 * N * N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char chr = grid[i].charAt(j);
                int id = 4 * (i * N + j);
                if (chr == ' ') {
                    connect(id, id + 1);
                    connect(id + 1, id + 2);
                    connect(id + 2, id + 3);
                } else if (chr == '/') {
                    connect(id, id + 1);
                    connect(id + 2, id + 3);
                } else if (chr == '\\') {
                    connect(id, id + 3);
                    connect(id + 1, id + 2);
                }
                if (j < N - 1) {
                    int rightColId = 4 * (i * N + j + 1);
                    connect(id + 2, rightColId);
                }
                if (i < N - 1) {
                    int nextRowId = 4 * ((i + 1) * N + j);
                    connect(id + 3, nextRowId + 1);
                }
            }
        }
        return count;
    }

    private void connect(int point1, int point2) {
        int root1 = find(point1);
        int root2 = find(point2);
        if (root1 == root2) {
            return;
        }
        if (weights[root1] > weights[root2]) {
            weights[root1] += weights[root2];
            roots[root2] = root1;
        } else {
            weights[root2] += weights[root1];
            roots[root1] = root2;
        }
        count--;
    }

    private int find(int point) {
        while (point != roots[point]) {
            roots[point] = roots[roots[point]];
            point = roots[point];
        }
        return point;
    }

    private void init(int len) {
        weights = new int[len];
        roots = new int[len];
        for (int i = 0; i < len; i++) {
            weights[i] = 1;
            roots[i] = i;
        }
        count = len;
    }
}
