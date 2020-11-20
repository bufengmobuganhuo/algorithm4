package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/11/18 上午8:52
 * TODO
 */
public class Ex_4_1_31_2 {
    public static void main(String[] args) {
        String path = "/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Digraph graph = new Digraph(new In(path));
        System.out.println(solution(graph));
    }

    private static int count;

    public static int solution(Digraph graph) {
        bfs(graph, 0);
        return count;
    }

    private static void bfs(Digraph graph, int startVertex) {
        Queue<Integer> que = new LinkedList<>();
        int[] edgeTo = new int[graph.getVertexNum()];
        Arrays.fill(edgeTo, -1);
        que.offer(startVertex);
        edgeTo[startVertex] = startVertex;
        while (!que.isEmpty()) {
            int vertex = que.poll();
            for (int adjVertex : graph.adj(vertex)) {
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
