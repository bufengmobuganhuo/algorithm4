package chapter6_Background;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/6/23 8:55 上午
 * 最短增广路径的Ford-Fulkerson最大流量算法
 */
public class FordFulkerson {
    public static void main(String[] args) {
        In in = new In("/Volumes/F/Algorithm4/src/main/resources/tinyFN.txt");
        FlowNetwork network = new FlowNetwork(in);
        int start = 0, end = network.getVertexNum() - 1;
        FordFulkerson fordFulkerson = new FordFulkerson(network, start, end);

        System.out.println("Max flow from " + start + " to " + end);
        for (int vertex = 0; vertex < network.getVertexNum(); vertex++) {
            for (FlowEdge edge :
                    network.adj(vertex)) {
                if ((vertex == edge.from()) && edge.flow() > 0) {
                    System.out.println("    " + edge);
                }
            }
        }
        System.out.println("Max flow value=" + fordFulkerson.value());
    }

    /**
     * marked[vertex]：是否存在从 起点--> vertex的路径
     */
    private boolean[] marked;
    /**
     * edgeTo[vertex]:从起点 --> vertex 的最短路径伤的最后一条边
     */
    private FlowEdge[] edgeTo;
    /**
     * 当前最大流量
     */
    private double value;

    public FordFulkerson(FlowNetwork network, int start, int end) {
        while (hasAugmentingPath(network, start, end)) {
            //利用所有存在的增广路径计算当前的瓶颈容量
            double bottle = Double.POSITIVE_INFINITY;
            //找到一个最小的可以增加的流量
            for (int vertex = end; vertex != start; vertex = edgeTo[vertex].other(vertex)) {
                bottle = Math.min(bottle, edgeTo[vertex].residualCapacityTo(vertex));
            }
            //增大流量
            for (int vertex = end; vertex != start; vertex = edgeTo[vertex].other(vertex)) {
                edgeTo[vertex].addResidualFlowTo(vertex, bottle);
            }
            value += bottle;
        }
    }

    /**
     * 从起点 --> 终点，查找是否含有增广路径
     *
     * @param network
     * @param start
     * @param end
     * @return
     */
    private boolean hasAugmentingPath(FlowNetwork network, int start, int end) {
        marked = new boolean[network.getVertexNum()];
        edgeTo = new FlowEdge[network.getVertexNum()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        marked[start] = true;
        while (!queue.isEmpty()) {
            int vertex1 = queue.poll();
            for (FlowEdge flowEdge :
                    network.adj(vertex1)) {
                int vertex2 = flowEdge.other(vertex1);
                //（在剩余网络中）对于任意一条连接到未被标记的顶点的边
                // （如果剩余流量为空，则这条边全部被使用，不是增广路径）
                if (flowEdge.residualCapacityTo(vertex2) > 0 && !marked[vertex2]) {
                    marked[vertex2] = true;
                    queue.offer(vertex2);
                    edgeTo[vertex2] = flowEdge;
                }

            }
        }
        return marked[end];
    }

    public double value() {
        return value;
    }

    public boolean inCut(int vertex) {
        return marked[vertex];
    }
}
