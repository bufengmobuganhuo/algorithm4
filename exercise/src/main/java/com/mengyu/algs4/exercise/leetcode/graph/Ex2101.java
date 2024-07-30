package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex2101 {
    public static void main(String[] args) {
        int[][] bombs = {{1,1,100000},{100000,100000,1}};
        System.out.println(new Ex2101().maximumDetonation(bombs));
    }
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        LinkedList<Integer>[] edges = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && isAffected(bombs[i][0], bombs[i][1], bombs[i][2], bombs[j][0], bombs[j][1])) {
                    edges[i].add(j);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            boolean[] marked = new boolean[n];
            Queue<Integer> que = new LinkedList<>();
            que.offer(i);
            marked[i] = true;
            int cnt = 1;
            while (!que.isEmpty()) {
                int vertex = que.poll();
                for (int adjVertex : edges[vertex]) {
                    if (!marked[adjVertex]) {
                        cnt++;
                        que.offer(adjVertex);
                        marked[adjVertex] = true;
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    private boolean isAffected(long x1, long y1, long r, long x2, long y2) {
        return ((x1 - x2) * (x1 - x2)) + ((y1 - y2) *  (y1 - y2)) <= (r * r);
    }
}
