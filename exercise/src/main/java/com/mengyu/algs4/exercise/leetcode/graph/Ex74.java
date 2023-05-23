package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * @author yuzhang
 * @date 2020/8/13 11:13 上午
 * TODO
 */
public class Ex74 {
    public static void main(String[] args) {
        int[][] times={{1,2,1},{2,3,2},{1,3,4}};
        Ex74 ex74=new Ex74();
        System.out.println(ex74.networkDelayTime(times,3,1));
    }
    private int[] distTo;
    private TreeMap<Integer,Integer> treeMap;
    public int networkDelayTime(int[][] times, int N, int K) {
        treeMap =new TreeMap<>();
        distTo=new int[N+1];
        Arrays.fill(distTo,Integer.MAX_VALUE);
        treeMap.put(K,0);
        distTo[K]=0;
        EdgeWeightedDigraph digraph=buildDigraph(times,N);
        while (!treeMap.isEmpty()){
            Integer vertex=treeMap.firstKey();
            treeMap.remove(vertex);
            relax(digraph,vertex);
        }
        return getWeight();
    }

    private int getWeight(){
        int max=Integer.MIN_VALUE;
        for (int i = 1; i < distTo.length; i++) {
            if (distTo[i]==Integer.MAX_VALUE){
                return -1;
            }
            max=Math.max(max,distTo[i]);
        }
        return max;
    }

    private EdgeWeightedDigraph buildDigraph(int[][] times, int N){
        EdgeWeightedDigraph digraph=new EdgeWeightedDigraph(N+1);
        for (int i = 0; i < times.length; i++) {
            digraph.addEdge(new DirectedEdge(times[i][0],times[i][1],times[i][2]));
        }
        return digraph;
    }

    private void relax(EdgeWeightedDigraph weightDigraph,int vertex){
        for (DirectedEdge adjEdge : weightDigraph.adj[vertex]) {
            int end=adjEdge.end;
            if (distTo[end]>distTo[vertex]+adjEdge.weight){
                distTo[end]=distTo[vertex]+adjEdge.weight;
                if (treeMap.containsKey(end)){
                    treeMap.remove(end);
                }
                treeMap.put(end,distTo[end]);
            }
        }
    }

    static class EdgeWeightedDigraph{
        LinkedList<DirectedEdge>[] adj;
        int vertexNum;

        public EdgeWeightedDigraph(int vertexNum) {
            this.vertexNum = vertexNum;
            adj=new LinkedList[vertexNum];
            for (int i = 0; i < vertexNum; i++) {
                adj[i]=new LinkedList<>();
            }
        }

        public void addEdge(DirectedEdge edge){
            adj[edge.start].offer(edge);
        }
    }

    static class DirectedEdge{
        int start;
        int end;
        int weight;

        public DirectedEdge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
