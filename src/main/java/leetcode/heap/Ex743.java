package leetcode.heap;

import org.omg.CORBA.INTERNAL;
import tonghuashun.Ex1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author yuzhang
 * @date 2020/9/30 10:02 上午
 * TODO
 */
public class Ex743 {
    public static void main(String[] args) {
        Ex743 ex743 = new Ex743();
        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        System.out.println(ex743.networkDelayTime(times, 4, 2));
    }

    private TreeMap<Integer, Integer> idxPriorityQueue;
    private int[] distTo;
    private LinkedList<WeightedEdge>[] adj;

    public int networkDelayTime(int[][] times, int N, int K) {
        distTo = new int[N + 1];
        idxPriorityQueue = new TreeMap<>();
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[0] = Integer.MIN_VALUE;
        buildWeightedDgraph(times, N);
        distTo[K] = 0;
        idxPriorityQueue.put(K, 0);
        while (!idxPriorityQueue.isEmpty()) {
            int startVertex = idxPriorityQueue.firstKey();
            relax(startVertex);
            idxPriorityQueue.remove(startVertex);
        }
        return find();
    }

    private int find(){
        int maxWeight = -1;
        for (int i = 1; i < distTo.length; i++) {
            if (distTo[i]==Integer.MAX_VALUE){
                return -1;
            }
            maxWeight = Math.max(maxWeight,distTo[i]);
        }
        return maxWeight;
    }

    private void relax(int startVertex) {
        for (WeightedEdge adjEdge : adj[startVertex]) {
            int endVertex = adjEdge.end;
            if (distTo[endVertex] > distTo[startVertex] + adjEdge.weight) {
                distTo[endVertex] = distTo[startVertex] + adjEdge.weight;
                idxPriorityQueue.put(endVertex, distTo[endVertex]);
            }
        }
    }

    private void buildWeightedDgraph(int[][] times, int N) {
        LinkedList<WeightedEdge>[] adj = new LinkedList[N + 1];
        for (int i = 0; i < adj.length; i++) {
            if (i != 0) {
                adj[i] = new LinkedList<>();
            }
        }
        for (int[] time : times) {
            WeightedEdge edge = new WeightedEdge(time[0], time[1], time[2]);
            adj[edge.start].add(edge);
        }
        this.adj = adj;
    }

    static class WeightedEdge {
        int start;
        int end;
        int weight;

        public WeightedEdge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
