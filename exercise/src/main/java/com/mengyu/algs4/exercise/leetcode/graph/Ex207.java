package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2020/8/11 11:34 上午
 * TODO
 */
public class Ex207 {
    public static void main(String[] args) {
        Ex207 ex207=new Ex207();
        System.out.println(ex207.canFinish(2,new int[][]{{0,1},{1,0}}));
    }
    private boolean[] marked;
    private boolean[] onStack;
    private boolean hasCycle;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Digraph digraph=buildGraph(numCourses,prerequisites);
        return !hasCycle(digraph);
    }

    private boolean hasCycle(Digraph digraph){
        marked=new boolean[digraph.vertexNum];
        onStack=new boolean[digraph.vertexNum];
        hasCycle=false;
        for (int i = 0; i < digraph.vertexNum; i++) {
            if (!marked[i]){
                dfs(digraph,i);
            }
            if (hasCycle){
                return true;
            }
        }
        return false;
    }

    private void dfs(Digraph digraph,int vertex){
        onStack[vertex]=true;
        marked[vertex]=true;
        for (int adjVertex : digraph.adj(vertex)) {
            if (!marked[adjVertex]){
                dfs(digraph,adjVertex);
            }else if (onStack[adjVertex]){
                hasCycle=true;
            }
        }
        onStack[vertex]=false;
    }

    private Digraph buildGraph(int numCourses, int[][] prerequisites){
        Digraph digraph=new Digraph(numCourses);
        for (int i = 0; i < prerequisites.length; i++) {
            digraph.addEdge(prerequisites[i][1],prerequisites[i][0]);
        }
        return digraph;
    }

    static class Digraph{
        LinkedList<Integer>[] adj;
        int vertexNum;

        public Digraph(int vertexNum) {
            this.vertexNum=vertexNum;
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
