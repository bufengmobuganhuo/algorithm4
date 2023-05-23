package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2020/8/12 8:39 上午
 * TODO
 */
public class Ex210 {
    public static void main(String[] args) {
        int[][] prerequisites={
                {1,0},{2,0},{3,0},{2,1},{0,3}
        };
        Ex210 ex210=new Ex210();
        System.out.println(Arrays.toString(ex210.findOrder(4,prerequisites)));
    }
    private boolean hasCycle;
    private boolean[] onStack;
    private boolean[] marked;
    private int[] reverseOrder;
    private int index;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        hasCycle=false;
        onStack=new boolean[numCourses];
        marked=new boolean[numCourses];
        reverseOrder=new int[numCourses];
        index=numCourses-1;
        Digraph digraph=buildDigraph(numCourses,prerequisites);
        for (int i = 0; i < numCourses; i++) {
            if (!marked[i]){
                dfs(digraph,i);
            }
        }
        if (hasCycle){
            return new int[0];
        }else{
            return reverseOrder;
        }
    }

    private Digraph buildDigraph(int numCourses,int[][] prerequisites){
        Digraph digraph=new Digraph(numCourses);
        for (int i = 0; i < prerequisites.length; i++) {
            digraph.addEdge(prerequisites[i][1],prerequisites[i][0]);
        }
        return digraph;
    }

    private void dfs(Digraph digraph,int vertex){
        onStack[vertex]=true;
        marked[vertex]=true;
        for (int adjVertex : digraph.adj(vertex)) {
            if (!marked[adjVertex]){
                dfs(digraph,adjVertex);
            }else if (onStack[adjVertex]){
                hasCycle=true;
                return;
            }
        }
        onStack[vertex]=false;
        reverseOrder[index--]=vertex;
    }

    static class Digraph{
        private LinkedList<Integer>[] adj;
        private int vertexNum;

        public Digraph(int vertexNum) {
            this.vertexNum = vertexNum;
            adj=new LinkedList[vertexNum];
            for (int i = 0; i < vertexNum; i++) {
                adj[i]=new LinkedList<>();
            }
        }

        public void addEdge(int vertex1,int vertex2){
            adj[vertex1].offer(vertex2);
        }

        public Iterable<Integer> adj(int vertex){
            return adj[vertex];
        }
    }
}
