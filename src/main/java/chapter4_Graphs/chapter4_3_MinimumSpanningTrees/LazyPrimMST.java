package chapter4_Graphs.chapter4_3_MinimumSpanningTrees;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zhangyu
 * 2020/5/8 14:52
 * 延时Prim算法
 */
public class LazyPrimMST {
    public static void main(String[] args) {
        String path = "/Volumes/F/Algorithm4/src/main/resources/tinyEWG.txt";
        In in = new In(path);
        EdgeWeightedGraph weightedGraph = new EdgeWeightedGraph(in);
        LazyPrimMST lazyPrimMST = new LazyPrimMST(weightedGraph);
        Iterable<Edge> edges = lazyPrimMST.edges();
        Iterator<Edge> iterator = edges.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
        System.out.println((lazyPrimMST.lazyWeight() == lazyPrimMST.nonLazyWeight()) + ":" + lazyPrimMST.lazyWeight() + "-" + lazyPrimMST.nonLazyWeight());
    }

    /**
     * 顶点是否在最小生成树中
     */
    private boolean[] marked;
    /**
     * 优先队列，用于计算最小权重边
     */
    private PriorityQueue<Edge> priorityQueue;
    /**
     * 最小生成树的边
     */
    private Queue<Edge> mst;

    /**
     * 最小生成树的总权重
     */
    private double weight;

    public LazyPrimMST(EdgeWeightedGraph weightedGraph) {
        marked = new boolean[weightedGraph.getVertexNum()];
        priorityQueue = new PriorityQueue<>();
        mst = new LinkedList<>();
        //先将顶点0加入优先队列
        visit(weightedGraph, 0);
        while (!priorityQueue.isEmpty()) {
            //找到最小权重边及他的两个顶点
            Edge edge = priorityQueue.poll();
            int vertex1 = edge.either();
            int vertex2 = edge.other(vertex1);
            //如果两个顶点都在树中，说明不是横切边，则失效
            if (marked[vertex1] && marked[vertex2]) {
                continue;
            }
            mst.add(edge);
            weight += edge.getWeight();
            //从上述两个顶点开始继续向下访问
            if (!marked[vertex1]) {
                visit(weightedGraph, vertex1);
            }
            if (!marked[vertex2]) {
                visit(weightedGraph, vertex2);
            }
        }
    }

    /**
     * 标记vertex，并将所有连接vertex和未被标记顶点的边加入PQ
     *
     * @param weightedGraph
     * @param vertex
     */
    private void visit(EdgeWeightedGraph weightedGraph, int vertex) {
        marked[vertex] = true;
        for (Edge edge : weightedGraph.adj(vertex)) {
            if (!marked[edge.other(vertex)]) {
                priorityQueue.add(edge);
            }
        }
    }

    /**
     * @return 获取所有生成树的边
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * @return 延时方式获取最小生成树总权重
     */
    public double lazyWeight() {
        double weight = 0;
        for (Edge edge : mst) {
            weight += edge.getWeight();
        }
        return weight;
    }

    /**
     * @return 即时获取总权重weight
     */
    public double nonLazyWeight() {
        return weight;
    }
}
