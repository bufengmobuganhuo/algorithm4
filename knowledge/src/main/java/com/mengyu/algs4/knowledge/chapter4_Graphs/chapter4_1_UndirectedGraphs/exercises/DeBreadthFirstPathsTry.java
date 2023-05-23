package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2021/2/23 上午10:21
 * TODO
 */
public class DeBreadthFirstPathsTry {
    public int pathLength(Digraph graph, int start, int end) {
        int[] startDistTo = new int[graph.getVertexNum()];
        boolean[] startMarked = new boolean[graph.getVertexNum()];
        Queue<Integer> startQue = new LinkedList<>();
        startQue.offer(start);
        startMarked[start] = true;

        int[] endDistTo = new int[graph.getVertexNum()];
        boolean[] endMarked = new boolean[graph.getVertexNum()];
        Queue<Integer> endQue = new LinkedList<>();
        endQue.offer(end);
        endMarked[end] = true;

        while (!startQue.isEmpty() && !endQue.isEmpty()) {
            int ans = visit(graph, startQue, startMarked, startDistTo, endMarked, endDistTo);
            if (ans > -1) {
                return ans;
            }
            ans = visit(graph, endQue, endMarked, endDistTo, startMarked, startDistTo);
            if (ans > -1) {
                return ans;
            }
        }
        return -1;
    }

    private int visit(Digraph digraph, Queue<Integer> que, boolean[] marked, int[] distTo, boolean[] otherMarked, int[] otherDistTo) {
        int vertex = que.poll();
        if (otherMarked[vertex]) {
            return distTo[vertex] + otherDistTo[vertex];
        }
        for (int adjVertex : digraph.adj(vertex)) {
            if (!marked[adjVertex]){
                marked[adjVertex] = true;
                distTo[adjVertex] = distTo[vertex] + 1;
                que.offer(adjVertex);
            }
        }
        return -1;
    }
}
