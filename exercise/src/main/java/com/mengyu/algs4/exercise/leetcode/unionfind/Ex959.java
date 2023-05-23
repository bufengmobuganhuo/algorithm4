package com.mengyu.algs4.exercise.leetcode.unionfind;

/**
 * @author yuzhang
 * @date 2020/10/22 10:39 上午
 * 按照如下题解进行编写
 * https://leetcode-cn.com/problems/regions-cut-by-slashes/solution/guan-yu-guan-fang-bing-cha-ji-ti-jie-de-zi-wo-li-j/
 */
public class Ex959 {
    public static void main(String[] args) {
        String[] grid = {" /", "/ "};
        Ex959 ex959 = new Ex959();
        System.out.println(ex959.regionsBySlashes(grid));
    }

    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        // 每个小方格构建四个顶点
        int pointNum = 4 * N * N;
        UF uf = new UF(pointNum);
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                // 用于区分不同的小方格
                int root = 4 * (row * N + col);
                char val = grid[row].charAt(col);
                // 如果是'\'或' '，则合并（0，1）（3，2）
                if (val != '/') {
                    uf.union(root, root + 1);
                    uf.union(root + 3, root + 2);
                }
                // 如果是'/'或' '，则合并（0，3）（1，2）
                if (val != '\\') {
                    uf.union(root, root + 3);
                    uf.union(root + 1, root + 2);
                }
                // 如果不是最后一行，则向下合并（2，0）
                if (row < N - 1) {
                    int nextRoot = 4 * ((row + 1) * N + col);
                    uf.union(root + 2, nextRoot);
                }
                // 如果不是第一行，则向上合并(0,2)
                if (row > 0) {
                    int upRoot = 4 * ((row - 1) * N + col);
                    uf.union(root, upRoot + 2);
                }
                // 如果不是最后一列，则合并右邻居（1，3）
                if (col < N - 1) {
                    int rightRoot = 4 * (row * N + col + 1);
                    uf.union(root + 1, rightRoot + 3);
                }
                // 如果不是第一列，则合并左邻居（1，3）
                if (col > 0) {
                    int leftRoot = 4 * (row * N + col - 1);
                    uf.union(root + 3, leftRoot + 1);
                }
            }
        }
        return uf.count;
    }

    static class UF {
        int[] connectedComponentId;
        int[] weight;
        private int count;

        public UF(int length) {
            connectedComponentId = new int[length];
            weight = new int[length];
            for (int i = 0; i < length; i++) {
                weight[i] = 1;
                connectedComponentId[i] = i;
            }
            count = length;
        }

        public int find(int point) {
            while (point != connectedComponentId[point]) {
                connectedComponentId[point] = connectedComponentId[connectedComponentId[point]];
                point = connectedComponentId[point];
            }
            return point;
        }

        public void union(int point1, int point2) {
            int point1Root = find(point1);
            int point2Root = find(point2);
            if (point1Root == point2Root) {
                return;
            }
            if (weight[point1Root] > weight[point2Root]) {
                weight[point1Root] += weight[point2Root];
                connectedComponentId[point2Root] = point1Root;
            } else {
                weight[point2Root] += weight[point1Root];
                connectedComponentId[point1Root] = point2Root;
            }
            count--;
        }
    }
}
