package com.mengyu.algs4.exercise.leetcode.unionfind;

/**
 * @author yuzhang
 * @date 2021/3/22 上午11:35
 * TODO
 */
public class Ex959_1 {
    public static void main(String[] args) {
        String[] grid = {" /", "/ "};
        Ex959_1 ex959_1 = new Ex959_1();
        System.out.println(ex959_1.regionsBySlashes(grid));
    }

    public int regionsBySlashes(String[] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int N = grid.length;
        int pointNum = 4 * N * N;
        WeightUnionFind weightUnionFind = new WeightUnionFind(pointNum);
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int root = 4 * (row * N + col);
                char chr = grid[row].charAt(col);
                if (chr != '\\') {
                    weightUnionFind.connect(root, root + 3);
                    weightUnionFind.connect(root + 1, root + 2);
                }
                if (chr != '/') {
                    weightUnionFind.connect(root, root + 1);
                    weightUnionFind.connect(root + 2, root + 3);
                }
                // 向上合并
                if (row > 0) {
                    int lastRoot = 4 * ((row - 1) * N + col);
                    weightUnionFind.connect(root, lastRoot + 2);
                }
                // 向下合并
                if (row < N - 1) {
                    int nextRoot = 4 * ((row + 1) * N + col);
                    weightUnionFind.connect(root + 2, nextRoot);
                }
                // 向右合并
                if (col < N - 1) {
                    int rightRoot = 4 * (row * N + col + 1);
                    weightUnionFind.connect(root + 1, rightRoot + 3);
                }
                if (col > 0) {
                    int leftRoot = 4 * (row * N + col - 1);
                    weightUnionFind.connect(root + 3, leftRoot + 1);
                }
            }
        }
        return weightUnionFind.count;
    }

    static class WeightUnionFind {
        int[] ids;
        int[] weights;
        int count;

        public WeightUnionFind(int cap) {
            ids = new int[cap];
            weights = new int[cap];
            for (int i = 0; i < cap; i++) {
                ids[i] = i;
                weights[i] = 1;
            }
            count = cap;
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
            count--;
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
