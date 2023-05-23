package com.mengyu.algs4.exercise.leetcode.unionfind;

/**
 * @author yuzhang
 * @date 2020/10/28 9:21 上午
 * TODO
 */
public class Ex1319 {
    public static void main(String[] args) {
        int[][] connections = {{0,1},{0,2},{3,4},{2,3}};
        Ex1319 ex1319 = new Ex1319();
        System.out.println(ex1319.makeConnected(5,connections));
    }
    public int makeConnected(int n, int[][] connections) {
        if (connections.length<n-1){
            return -1;
        }
        UnionFind unionFind = new UnionFind(n);
        for (int[] connection : connections) {
            unionFind.union(connection[0],connection[1]);
        }
        return unionFind.count>0?unionFind.count-1:0;
    }

    static class UnionFind {
        int[] weight;
        int[] ids;
        int count;

        public UnionFind(int length) {
            weight = new int[length];
            ids = new int[length];
            for (int i = 0; i < length; i++) {
                weight[i]=1;
                ids[i]=i;
            }
            count = length;
        }

        public void union(int point1, int point2) {
            int point1Root = find(point1);
            int point2Root = find(point2);
            if (point1Root == point2Root) {
                return;
            }
            if (weight[point1Root] > weight[point2Root]) {
                weight[point1Root] += weight[point2Root];
                ids[point2Root] = point1Root;
            } else {
                weight[point2Root] += weight[point1Root];
                ids[point1Root] = point2Root;
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
