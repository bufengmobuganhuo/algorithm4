package leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex743_1 {
    private int max;

    private LinkedList<Point>[] adj;

    private PriorityQueue<Point> idxMinQue;

    private int[] weights;

    public int networkDelayTime(int[][] times, int n, int k) {
        buildGraph(times, n);
        idxMinQue = new PriorityQueue<>();
        weights = new int[n + 1];
        Arrays.fill(weights, Integer.MAX_VALUE);
        weights[k] = 0;
        idxMinQue.offer(new Point(k, 0));
        while (!idxMinQue.isEmpty()) {
            relax(idxMinQue.poll().vertex);
        }
        weights[0] = 0;
        max = Arrays.stream(weights).max().getAsInt();
        return max == Integer.MAX_VALUE ? -1 : max;
    }

    private void relax(int vertex) {
        if (adj[vertex] == null){
            return;
        }
        for (Point adjPoint : adj[vertex]) {
            if (weights[adjPoint.vertex] > adjPoint.weight + weights[vertex]) {
                weights[adjPoint.vertex] = adjPoint.weight + weights[vertex];
                idxMinQue.remove(adjPoint);
                idxMinQue.offer(adjPoint);
            }
        }
    }

    private LinkedList<Point>[] buildGraph(int[][] times, int n) {
        if (adj == null) {
            adj = new LinkedList[n + 1];
        }
        for (int[] time : times) {
            if (adj[time[0]] == null) {
                adj[time[0]] = new LinkedList<>();
            }
            adj[time[0]].add(new Point(time[1], time[2]));
        }
        return adj;
    }

    // 到vertex的权重=weight
    private class Point implements Comparable<Point> {
        private int vertex;
        private int weight;

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
        public int hashCode() {
            return Objects.hash(vertex);
        }

        @Override
        public int compareTo(Point o) {
            return this.weight - o.weight;
        }
    }

}
