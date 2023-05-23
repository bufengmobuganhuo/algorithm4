package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/11/17 上午8:41
 * TODO
 */
public class EX_4_1_16_17_2 {
    public static void main(String[] args) {
        In in = new In("/Volumes/F/Algorithm4/src/main/resources/tinyG.txt");
        Digraph graph = new Digraph(in);
        EX_4_1_16_17_2 ex_4_1_16_17_2 = new EX_4_1_16_17_2(graph);
        System.out.println(ex_4_1_16_17_2.eccentricity(0));
        System.out.println(ex_4_1_16_17_2.girth());
    }

    private final Digraph graph;

    private boolean[] marked;
    /**
     * 每个顶点的离心率
     */
    private Map<Integer, Integer> vertexEccMap;

    public EX_4_1_16_17_2(Digraph graph) {
        this.graph = graph;
        this.vertexEccMap = new HashMap<>();
        buildEccMap();
    }

    private void buildEccMap() {
        for (int i = 0; i < graph.getVertexNum(); i++) {
            if (i != 0 && !marked[i]) {
                throw new UnsupportedOperationException();
            }
            marked = new boolean[graph.getVertexNum()];
            bfs(i);
        }
    }

    private void bfs(int vertex) {
        marked[vertex] = true;
        Queue<Integer> que = new LinkedList<>();
        que.offer(vertex);
        int lastLayerVertex = vertex;
        int lastLayerVertexTmp = vertex;
        int maxDis = -1;
        while (!que.isEmpty()) {
            int vertexTmp = que.poll();
            for (int adjVertex : graph.adj(vertexTmp)) {
                if (!marked[adjVertex]) {
                    que.offer(adjVertex);
                    marked[adjVertex] = true;
                    lastLayerVertexTmp = adjVertex;
                }
            }
            if (lastLayerVertex == vertexTmp) {
                maxDis++;
                lastLayerVertex = lastLayerVertexTmp;
            }
        }
        vertexEccMap.put(vertex, maxDis);
    }

    public int eccentricity(int vertex) {
        return vertexEccMap.get(vertex);
    }

    public int diameter() {
        Optional<Map.Entry<Integer, Integer>> entry = vertexEccMap.entrySet().stream().max(Map.Entry.comparingByValue());
        if (entry.isPresent()) {
            return entry.get().getValue();
        }
        return -1;
    }

    public int radius() {
        Optional<Map.Entry<Integer, Integer>> entry =
                vertexEccMap.entrySet().stream().min(Map.Entry.comparingByValue());
        if (entry.isPresent()) {
            return entry.get().getValue();
        }
        return -1;
    }

    public int center() {
        int radius = radius();
        Optional<Map.Entry<Integer, Integer>> entry =
                vertexEccMap.entrySet().stream().filter(integerIntegerEntry -> integerIntegerEntry.getValue() == radius).findFirst();
        if (entry.isPresent()) {
            return entry.get().getValue();
        }
        return -1;
    }

    /**
     * 周长
     *
     * @return
     */
    public int girth() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < graph.getVertexNum(); i++) {
            min = Math.min(min, bfsForGirth(i));
        }
        return min;
    }

    private int bfsForGirth(int startVertex) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(startVertex);
        // distTo[i]:起点到顶点i的距离
        int[] distTo = new int[graph.getVertexNum()];
        // edgeTo[i]=vertex:vertex可以到达顶点i
        int[] edgeTo = new int[graph.getVertexNum()];
        Arrays.fill(distTo, -1);
        distTo[startVertex] = 0;
        int min = Integer.MAX_VALUE;
        while (!que.isEmpty()) {
            int tmpStart = que.poll();
            for (int adjVertex : graph.adj(tmpStart)) {
                if (distTo[adjVertex] == -1) {
                    distTo[adjVertex] = distTo[tmpStart] + 1;
                    edgeTo[adjVertex] = tmpStart;
                    que.offer(adjVertex);
                } else if (edgeTo[tmpStart] != adjVertex) {
                    min = Math.min(min, distTo[adjVertex] + distTo[tmpStart] + 1);
                }
            }
        }
        return min;
    }
}
