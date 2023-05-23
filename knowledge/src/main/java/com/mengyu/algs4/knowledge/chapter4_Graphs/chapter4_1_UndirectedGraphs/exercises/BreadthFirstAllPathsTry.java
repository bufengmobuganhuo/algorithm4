package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2021/2/23 上午9:54
 * TODO
 */
public class BreadthFirstAllPathsTry {
    private int[] distTo;
    private List<List<Integer>>[] edgeTo;
    private int startVertex;

    public BreadthFirstAllPathsTry(Digraph digraph, int startVertex) {
        this.startVertex = startVertex;
        distTo = new int[digraph.getVertexNum()];
        edgeTo = new List[digraph.getVertexNum()];
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            distTo[i] = Integer.MAX_VALUE;
            edgeTo[i] = new ArrayList<>();
        }
        distTo[startVertex] = 0;
        bfs(digraph, startVertex);
    }

    private void bfs(Digraph digraph, int startVertex) {
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> beginPath = new ArrayList<>();
        beginPath.add(startVertex);
        queue.offer(beginPath);
        while (!queue.isEmpty()) {
            List<Integer> nowPath = queue.poll();
            int lastVertex = nowPath.get(nowPath.size() - 1);
            for (int adjVertex : digraph.adj(lastVertex)) {
                if (distTo[lastVertex] + 1 <= distTo[adjVertex]) {
                    distTo[adjVertex] = distTo[lastVertex] + 1;
                    List<Integer> tmp = new ArrayList<>(nowPath);
                    tmp.add(adjVertex);
                    edgeTo[adjVertex].add(tmp);
                    queue.offer(tmp);
                }
            }
        }
    }
}
