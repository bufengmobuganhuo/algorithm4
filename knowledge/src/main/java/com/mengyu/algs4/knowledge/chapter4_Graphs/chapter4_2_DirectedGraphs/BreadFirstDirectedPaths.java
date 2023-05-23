package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/4/29 11:28
 * 广度优先搜索，获取最短路径
 */
public class BreadFirstDirectedPaths {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyDG";
        In in=new In(path);
        Digraph digraph=new Digraph(in);
        BreadFirstDirectedPaths breadthFirstPaths=new BreadFirstDirectedPaths(digraph,0);
        Stack<Integer> stack=breadthFirstPaths.pathTo(2);
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+"-");
        }
    }
    private final int start;
    private boolean[] marked;
    private int[] edgeTo;
    private final Digraph digraph;
    private int[]distTo;

    public BreadFirstDirectedPaths(Digraph digraph,int start) {
        this.digraph = digraph;
        this.start = start;
        marked=new boolean[digraph.getVertexNum()];
        edgeTo=new int[digraph.getVertexNum()];
        distTo=new int[digraph.getVertexNum()];
        bfs(start);
    }

    private void bfs(int start){
        marked[start]=true;
        Queue<Integer> queue=new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()){
            int tempStart=queue.poll();
            for (int vertex:digraph.adj(tempStart)){
                if (!marked[vertex]){
                    queue.add(vertex);
                    edgeTo[vertex]=tempStart;
                    marked[vertex]=true;
                    distTo[vertex]=distTo[tempStart]+1;
                }
            }
        }
    }

    public int distTo(int vertex){
        return distTo[vertex];
    }

    /**
     * @param vertex
     * @return 是否存在一条从start -> vertex的有向路径
     */
    public boolean hasPathTo(int vertex){
        return marked[vertex];
    }

    public Stack<Integer> pathTo(int vertex){
        if (!hasPathTo(vertex)){
            return null;
        }
        Stack<Integer> path=new Stack<>();
        for (int tempVertex=vertex;tempVertex!=start;tempVertex=edgeTo[tempVertex]){
            path.push(tempVertex);
        }
        path.push(start);
        return path;
    }
}
