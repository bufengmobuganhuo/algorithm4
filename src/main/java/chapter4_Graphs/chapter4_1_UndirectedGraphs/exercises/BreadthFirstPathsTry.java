package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/8/3 9:08 上午
 * 深度优先搜索，回答最短路径问题
 */
public class BreadthFirstPathsTry {
    public static void main(String[] args) {
        String path = "/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Digraph graph = new Digraph(new In(path));
        BreadthFirstPathsTry breadthFirstPaths = new BreadthFirstPathsTry(graph, 0);
        Stack<Integer> paths = (Stack<Integer>) breadthFirstPaths.pathTo(5);
        while (!paths.isEmpty()) {
            System.out.print(paths.pop() + "-");
        }
        System.out.println(breadthFirstPaths.distTo(9));
    }

    private int[] edgeTo;
    private boolean[] marked;
    private int start;
    private int distTo[];

    public BreadthFirstPathsTry(Digraph graph, int start) {
        this.start = start;
        edgeTo = new int[graph.getVertexNum()];
        marked = new boolean[graph.getVertexNum()];
        distTo = new int[graph.getVertexNum()];
        bfs(graph, start);
    }

    private void bfs(Digraph graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        marked[start] = true;
        distTo[start] = 0;
        queue.offer(start);
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int adjVertex : graph.adj(vertex)) {
                if (!marked[adjVertex]) {
                    marked[adjVertex] = true;
                    queue.offer(adjVertex);
                    edgeTo[adjVertex] = vertex;
                    distTo[adjVertex] = distTo[vertex] + 1;
                }
            }
        }
    }

    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public Iterable<Integer> pathTo(int vertex) {
        if (!hasPathTo(vertex)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int tmpVertex = vertex; tmpVertex != start; tmpVertex = edgeTo[tmpVertex]) {
            path.push(tmpVertex);
        }
        path.push(start);
        return path;
    }

    public int distTo(int vertex) {
        return distTo[vertex];
    }
}
