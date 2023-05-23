package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex2045 {
    /**
     * 1. 权重都相同的无向图，相当于无权重图
     * 2. 可以模仿加权无向图寻找最小生成树的方法，使用一个数组path[n][2]:
     * path[i][0]：到达i的的最短路径，到达i的次短路径
     * 3. 有了路径后，可以求时间：
     * 因为红绿灯都是成组变化：绿，红｜绿，红｜绿，红｜。。。,因此需要mod(2change)
     * 假设到达节点i的时间是ti，则到达下一节点的时间为：
     * ti + time + twait,其中：
     * if 0 <= ti % (2change) < change : twait = 0
     * if change <= ti % (2change) < 2change : twait = 2change - ti % (2change)
     */
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] adj = new ArrayList[n + 1];
        // 构造无向图
        for (int i = 0; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        int[][] path = new int[n + 1][2];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(path[i], Integer.MAX_VALUE);
        }
        path[1][0] = 0;
        Queue<int[]> que = new LinkedList<>();
        // {节点，最短路径}
        que.offer(new int[]{1, 0});
        while (!que.isEmpty()) {
            int[] info = que.poll();
            int curNode = info[0], len = info[1];
            for (int next : adj[curNode]) {
                // 如果len+1是最短路径
                if (path[next][0] > len + 1) {
                    path[next][0] = len + 1;
                    que.offer(new int[]{next, len + 1});
                    // len + 1 < 之前的次短路径 --> len + 1是新的次短路径
                    // 同时排除 len + 1 == next最短路径的情况
                } else if (len + 1 > path[next][0] && len + 1 < path[next][1]) {
                    path[next][1] = len + 1;
                    que.offer(new int[]{next, len + 1});
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < path[n][1]; i++) {
            int mod = ret % (2 * change);
            if (mod >= change && mod < 2 * change) {
                ret = ret + 2 * change - mod;
            }
            ret = ret + time;
        }
        return ret;
    }
}
