package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_5_UnionFind;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/10/22 9:30 上午
 * 基于深度的并查集
 */
public class UF_DepthQuickUnion extends UF {
    private int[] depth;
    private int[] connectedComponentId;

    public UF_DepthQuickUnion(int length) {
        super(length);
        depth = new int[length];
        connectedComponentId = new int[length];
        // 一开始各自为政，高度都是1
        Arrays.fill(depth, 1);
    }

    /**
     * 基于深度的合并，将高度小的合并到深度大的
     *
     * @param point1
     * @param point2
     */
    @Override
    public void union(int point1, int point2) {
        int point1Root = find(point1);
        int point2Root = find(point2);
        if (point1Root == point2Root) {
            return;
        }
        // 将深度小的合并到深度大的分支上
        if (depth[point1Root] < depth[point2Root]) {
            connectedComponentId[point1Root] = point2Root;
        } else if (depth[point1Root] > depth[point2Root]) {
            connectedComponentId[point2Root] = point1Root;
            // 如果两个的深度一样，则随便合并，但是合并后的深度会+1
        } else {
            connectedComponentId[point1Root] = point2Root;
            depth[point2Root] += 1;
        }
    }

    @Override
    public int find(int point) {
        while (point != connectedComponentId[point]) {
            point = connectedComponentId[point];
        }
        return point;
    }
}
