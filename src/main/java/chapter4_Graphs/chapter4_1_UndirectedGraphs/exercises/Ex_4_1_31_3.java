package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2021/2/24 上午9:38
 * TODO
 */
public class Ex_4_1_31_3 {
    private int[] edgeTo;
    private int count;

    public int count(Digraph digraph){
        edgeTo = new int[digraph.getVertexNum()];
        bfs(digraph,0);
        return count;
    }

    private void bfs(Digraph digraph, int vertex) {
        Arrays.fill(edgeTo, -1);
        edgeTo[vertex] = vertex;
        Queue<Integer> que = new LinkedList<>();
        que.offer(vertex);
        while (!que.isEmpty()) {
            vertex = que.poll();
            for (int adjVertex : digraph.adj(vertex)) {
                if (edgeTo[adjVertex] == -1) {
                    que.offer(adjVertex);
                    edgeTo[adjVertex] = vertex;
                } else if (edgeTo[adjVertex] == vertex) {
                    count++;
                }
            }
        }
    }
}
