package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex1631 {

    public static void main(String[] args) {
        int[][] heights = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}};
        System.out.println(new Ex1631().minimumEffortPath(heights));
    }

    private int[][] directs = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public int minimumEffortPath2(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        PriorityQueue<int[]> que = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        que.offer(new int[]{0, 0, 0});

        while (!que.isEmpty()) {
            int[] edge = que.poll();
            int x = edge[0], y = edge[1], d = edge[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + directs[i][0];
                int ny = y + directs[i][1];
                int id = nx * n + ny;

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.max(d, Math.abs(heights[x][y] - heights[nx][ny])) < dist[id]) {
                    dist[id] = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));
                    que.offer(new int[]{nx, ny, dist[id]});
                }
            }
        }

        return dist[m * n - 1];
    }

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int left = 0, right = 1000000;
        int ans = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 使用bfs校验，当消耗 <= mid时，是否可以到达右下角
            Queue<int[]> que = new LinkedList<>();
            que.offer(new int[]{0, 0});
            // id = i * n + m，给每个节点一个编号，表示是否访问过
            boolean[] marked = new boolean[m * n];
            marked[0] = true;
            while (!que.isEmpty()) {
                int[] edge = que.poll();
                int x = edge[0], y = edge[1];
                for (int i = 0; i < 4; i++) {
                    int nx = x + directs[i][0];
                    int ny = y + directs[i][1];
                    int id = nx * n + ny;
                    // 如果消耗满足条件，则继续往下访问
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.abs(heights[x][y] - heights[nx][ny]) <= mid && !marked[id]) {
                        que.offer(new int[]{nx, ny});
                        marked[id] = true;
                    }
                }
            }
            // 如果能到达，则更新结果
            if (marked[(m - 1) * n + n - 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
