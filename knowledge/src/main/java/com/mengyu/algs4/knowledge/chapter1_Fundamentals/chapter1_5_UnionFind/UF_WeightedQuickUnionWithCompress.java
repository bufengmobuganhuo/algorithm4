package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_5_UnionFind;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/10/22 9:49 上午
 * 带有路径压缩的加权quick-union
 */
public class UF_WeightedQuickUnionWithCompress extends UF {
    private int[] connectedComponentId;
    private int[] weight;

    public UF_WeightedQuickUnionWithCompress(int length) {
        super(length);
        connectedComponentId = new int[length];
        weight = new int[length];
        Arrays.fill(weight, 1);
    }

    @Override
    public void union(int point1, int point2) {
        int point1Root = find(point1);
        int point2Root = find(point2);
        if (point1Root == point2Root) {
            return;
        }
        if (weight[point1Root] < weight[point2Root]) {
            connectedComponentId[point1Root] = point2Root;
            weight[point2Root] += weight[point1Root];
        } else {
            connectedComponentId[point2Root] = point1Root;
            weight[point1Root] += weight[point2Root];
        }
    }

    @Override
    public int find(int point) {
        while (point != connectedComponentId[point]) {
            // 让当前触点指向当前节点的爷爷触点
            connectedComponentId[point] = connectedComponentId[connectedComponentId[point]];
            point = connectedComponentId[point];
        }
        return point;
    }
}
