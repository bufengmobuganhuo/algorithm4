package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2021/3/23 上午10:18
 * TODO
 */
public class Ex743 {
    public static void main(String[] args) {
        Ex743 ex743 = new Ex743();
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        System.out.println(ex743.networkDelayTime(times, 4, 2));
    }

    private int[] distTo;
    private List<Point>[] graph;
    private PriorityQueue<Point> priorityQueue;

    public int networkDelayTime(int[][] times, int n, int k) {
        buildGraph(times, n);
        distTo = new int[n + 1];
        priorityQueue = new PriorityQueue<>();
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[k] = 0;
        priorityQueue.offer(new Point(k, 0));
        while (!priorityQueue.isEmpty()) {
            relax(priorityQueue.poll().vertex);
        }
        distTo[0] = 0;
        int max = Arrays.stream(distTo).max().getAsInt();
        return max == Integer.MAX_VALUE ? -1 : max;
    }

    private void relax(int vertex) {
        if (graph[vertex] == null) {
            return;
        }
        for (Point point : graph[vertex]) {
            if (distTo[point.vertex] > point.weight + distTo[vertex]) {
                distTo[point.vertex] = point.weight + distTo[vertex];
                priorityQueue.remove(point);
                priorityQueue.offer(point);
            }
        }
    }

    private void buildGraph(int[][] times, int n) {
        graph = new List[n + 1];
        for (int i = 0; i < times.length; i++) {
            if (graph[times[i][0]] == null) {
                graph[times[i][0]] = new ArrayList<>();
            }
            graph[times[i][0]].add(new Point(times[i][1], times[i][2]));
        }
    }

    static class Point implements Comparable<Point> {
        int vertex;
        int weight;

        public Point(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Point point = (Point) o;

            return vertex == point.vertex;
        }

        @Override
        public int compareTo(Point o) {
            return this.weight - o.weight;
        }
    }
}
