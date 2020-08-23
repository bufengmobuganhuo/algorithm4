package chapter4_Graphs.chapter4_4_ShortestPaths.exercises;

import chapter4_Graphs.chapter4_4_ShortestPaths.DirectedEdge;
import chapter4_Graphs.chapter4_4_ShortestPaths.EdgeWeightDigraph;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/8/10 10:24 上午
 * TODO
 */
public class AcyclicShortestPath {
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public AcyclicShortestPath(EdgeWeightDigraph weightDigraph,int start) {
        distTo=new double[weightDigraph.getVertexNum()];
        Arrays.fill(distTo,Double.POSITIVE_INFINITY);
        distTo[start]=0.0;
        TopologicalEdgeWeightedDigraph topological=new TopologicalEdgeWeightedDigraph(weightDigraph);
        Stack<Integer> reverseOrder= (Stack<Integer>) topological.reverseOrder();
        while (!reverseOrder.isEmpty()){
            relax(weightDigraph,reverseOrder.pop());
        }
    }

    private void relax(EdgeWeightDigraph weightDigraph,int vertex){
        for (DirectedEdge adjEdge: weightDigraph.adj(vertex)) {
            int endVertex=adjEdge.getEnd();
            if (distTo[endVertex]>adjEdge.getWeight()+distTo[vertex]){
                distTo[endVertex]=adjEdge.getWeight()+distTo[vertex];
                edgeTo[endVertex]=adjEdge;
            }
        }
    }
}
