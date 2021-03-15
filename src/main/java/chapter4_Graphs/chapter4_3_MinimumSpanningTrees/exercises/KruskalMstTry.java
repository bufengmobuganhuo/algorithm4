package chapter4_Graphs.chapter4_3_MinimumSpanningTrees.exercises;

import chapter4_Graphs.chapter4_3_MinimumSpanningTrees.Edge;
import chapter4_Graphs.chapter4_3_MinimumSpanningTrees.EdgeWeightedGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2021/3/2 上午10:14
 * TODO
 */
public class KruskalMstTry {
    private Queue<Edge> mst;
    private PriorityQueue<Edge> priorityQueue;

    public KruskalMstTry(EdgeWeightedGraph weightedGraph) {
        mst = new LinkedList<>();
        priorityQueue = new PriorityQueue<>();
        WeightUnionFind unionFind = new WeightUnionFind();
        for (Edge edge : weightedGraph.edges()) {
            priorityQueue.offer(edge);
        }
        while (!priorityQueue.isEmpty() && mst.size() != weightedGraph.getVertexNum() - 1) {
            Edge edge = priorityQueue.poll();
            int vertex1 = edge.either();
            int vertex2 = edge.other(vertex1);
            if (unionFind.connected(vertex1,vertex2)){
                continue;
            }
            unionFind.connect(vertex1,vertex2);
            mst.offer(edge);
        }
    }

    static class WeightUnionFind {
        int[] ids;
        int[] weights;

        private void connect(int point1, int point2) {
            int root1 = find(point1);
            int root2 = find(point2);
            if (root1 == root2) {
                return;
            }
            if (weights[root1] > weights[root2]) {
                weights[root1] += weights[root2];
                ids[root2] = root1;
            } else {
                weights[root2] += weights[root1];
                ids[root1] = root2;
            }
        }

        private boolean connected(int point1, int point2) {
            return find(point1) == find(point2);
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
