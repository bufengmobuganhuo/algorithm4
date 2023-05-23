package com.mengyu.algs4.exercise.leetcode.unionfind;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2020/10/25 10:01 上午
 * TODO
 */
public class Ex684 {
    public static void main(String[] args) {
        int[][] edges = {{3,4},{1,2},{2,4},{3,5},{2,5}};
        Ex684 ex684 = new Ex684();
        System.out.println(Arrays.toString(ex684.findRedundantConnection(edges)));
    }
    private int[] ans;
    public int[] findRedundantConnection(int[][] edges) {
        return solution2(edges);
    }

    /**
     * 解法二：使用并查集，原理同解法一差不多
     * @param edges
     * @return
     */
    private int[] solution2(int[][] edges){
        UnionFind unionFind = new UnionFind(edges.length+1);
        for (int[] edge : edges) {
            if (unionFind.connected(edge[0],edge[1])){
                ans=edge;
            }
            unionFind.connect(edge[0],edge[1]);
        }
        return ans;
    }

    static class UnionFind{
        int[] weight;
        int[] connectedComponentId;

        public UnionFind(int length) {
            weight=new int[length];
            connectedComponentId=new int[length];
            for (int i = 0; i < length; i++) {
                weight[i]=1;
                connectedComponentId[i]=i;
            }
        }

        void connect(int point1, int point2){
            int point1Root = find(point1);
            int point2Root = find(point2);
            if (point1Root==point2Root){
                return;
            }
            if (weight[point1Root]<weight[point2Root]){
                connectedComponentId[point1Root]=point2Root;
                weight[point2Root]+=weight[point1Root];
            }else{
                connectedComponentId[point2Root]=point1Root;
                weight[point1Root]+=weight[point2Root];
            }
        }

        boolean connected(int point1,int point2){
            return find(point1)==find(point2);
        }

        int find(int point){
            while (point!=connectedComponentId[point]){
                // 路径压缩
                connectedComponentId[point]=connectedComponentId[connectedComponentId[point]];
                point=connectedComponentId[point];
            }
            return point;
        }
    }

    /**
     * 每次都判断再不加入该边的情况下，是否能让edge[0]->edge[1]，如果可以则说明有环
     * @param edges
     * @return
     */
    private int[] solution1(int[][] edges){
        LinkedList<Integer>[] adj = buildGraph(edges);
        ans=new int[2];
        for (int[] edge : edges) {
            if (hasPath(adj,edge[0],edge[1],new boolean[adj.length])){
                ans=edge;
            }
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        return ans;
    }

    private boolean hasPath(LinkedList<Integer>[] adj,int vertex,int targetVertex,boolean[] marked){
        marked[vertex]=true;
        for (int adjVertex : adj[vertex]) {
            if (adjVertex==targetVertex||marked[targetVertex]){
                marked[targetVertex]=true;
                break;
            }
            if (!marked[adjVertex]){
                hasPath(adj,adjVertex,targetVertex,marked);
            }
        }
        return marked[targetVertex];
    }

    private LinkedList<Integer>[] buildGraph(int[][] edges){
        LinkedList<Integer>[] adj = new LinkedList[edges.length+1];
        for (int i = 0; i < adj.length; i++) {
            adj[i]=new LinkedList<>();
        }
        return adj;
    }
}
