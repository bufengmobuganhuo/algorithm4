package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2021/2/23 上午9:39
 * TODO
 */
public class BreadthFirstPathsTry2 {
    private int startVertex;
    private int[] edgeTo;
    private boolean[] marked;

    public BreadthFirstPathsTry2(Digraph digraph,int startVertex) {
        this.startVertex = startVertex;
        marked = new boolean[digraph.getVertexNum()];
        edgeTo = new int[digraph.getVertexNum()];
        bfs(digraph,startVertex);
    }

    private void bfs(Digraph digraph, int startVertex){
        Queue<Integer> queue = new LinkedList<>();
        marked[startVertex] = true;
        queue.offer(startVertex);
        while (!queue.isEmpty()){
            int vertex = queue.poll();
            for (int adjVertex : digraph.adj(vertex)) {
                if (!marked[adjVertex]){
                    marked[adjVertex] = true;
                    edgeTo[adjVertex]=vertex;
                    queue.offer(adjVertex);
                }
            }
        }
    }
}
