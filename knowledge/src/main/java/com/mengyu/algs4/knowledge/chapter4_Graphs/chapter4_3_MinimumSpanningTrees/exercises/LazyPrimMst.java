package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.Edge;
import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_3_MinimumSpanningTrees.EdgeWeightedGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/8/7 10:25 上午
 * 延时的Prim算法
 */
public class LazyPrimMst {
    private boolean[] marked;
    private Queue<Edge> mst;
    private PriorityQueue<Edge> priorityQueue;
    private int weight;

    public LazyPrimMst(EdgeWeightedGraph weightedGraph) {
        marked=new boolean[weightedGraph.getVertexNum()];
        mst=new LinkedList<>();
        priorityQueue=new PriorityQueue<>();
        visit(weightedGraph,0);
        while (!priorityQueue.isEmpty()){
            // 取出最小的权重边
            Edge minWeightEdge = priorityQueue.poll();
            int vertex1=minWeightEdge.either();
            int vertex2=minWeightEdge.other(vertex1);
            // 如果都在最小生成树中，则失效
            if (marked[vertex1]&&marked[vertex2]){
                continue;
            }
            mst.offer(minWeightEdge);
            weight+=minWeightEdge.getWeight();
            if (!marked[vertex1]){
                visit(weightedGraph,vertex1);
            }
            if (!marked[vertex2]){
                visit(weightedGraph,vertex2);
            }
        }
    }

    private void visit(EdgeWeightedGraph weightedGraph,int vertex){
        marked[vertex]=true;
        for (Edge adjEdge:weightedGraph.adj(vertex)){
            if (!marked[adjEdge.other(vertex)]){
                priorityQueue.offer(adjEdge);
            }
        }
    }
}
