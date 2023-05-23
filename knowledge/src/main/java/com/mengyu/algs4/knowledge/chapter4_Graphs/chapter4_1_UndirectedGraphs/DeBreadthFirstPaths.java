package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/8/24 10:15 上午
 * 双向广度优先搜索
 */
public class DeBreadthFirstPaths {
    public static void main(String[] args) {
        DeBreadthFirstPaths bfs=new DeBreadthFirstPaths();
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Digraph graph=new Digraph(new In(path));
        System.out.println(bfs.pathLength(graph,0,7));
    }
    public int pathLength(Digraph graph, int start, int end) {
        // 起点对应的queue
        Queue<Integer> queueStart = new LinkedList<>();
        boolean[] markedStart = new boolean[graph.getVertexNum()];
        int[] startDistTo = new int[graph.getVertexNum()];
        queueStart.offer(start);
        markedStart[start] = true;
        startDistTo[start] = 0;

        // 终点对应的queue
        Queue<Integer> queueEnd = new LinkedList<>();
        boolean[] markedEnd = new boolean[graph.getVertexNum()];
        int[] endDistTo = new int[graph.getVertexNum()];
        queueEnd.offer(end);
        markedEnd[end] = true;
        endDistTo[end] = 0;

        while (!queueStart.isEmpty() && !queueEnd.isEmpty()) {
            // 从起点开始访问一层
            int ans = visit(graph, queueStart, markedStart, startDistTo, markedEnd, endDistTo);
            if (ans > -1) {
                return ans;
            }
            // 从终点开始访问一层
            ans = visit(graph, queueEnd, markedEnd, endDistTo, markedStart, startDistTo);
            if (ans > -1) {
                return ans;
            }
        }
        return -1;
    }

    private int visit(Digraph graph, Queue<Integer> queue, boolean[] marked, int[] distTo, boolean[] markedOther, int[] distToOther) {
        int vertex = queue.poll();

        // 二者相遇
        if (markedOther[vertex]) {
            return distToOther[vertex] + distTo[vertex];
        }

        for (int adjVertex : graph.adj(vertex)) {
            if (!marked[adjVertex]) {
                marked[adjVertex] = true;
                queue.offer(adjVertex);
                distTo[adjVertex] = distTo[vertex] + 1;
            }
        }
        return -1;
    }
}
