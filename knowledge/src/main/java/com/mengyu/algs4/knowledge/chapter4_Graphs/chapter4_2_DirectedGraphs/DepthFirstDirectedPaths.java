package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/4/29 11:11
 * 使用深度优先搜索获取路径
 */
public class DepthFirstDirectedPaths {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyDG";
        In in=new In(path);
        Digraph digraph=new Digraph(in);
        DepthFirstDirectedPaths depthFirstDirectedPaths=new DepthFirstDirectedPaths(digraph,0);
        Stack<Integer> stack=depthFirstDirectedPaths.pathTo(2);
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+"-");
        }
    }
    private final Digraph digraph;
    private boolean[] marked;
    private int edgeTo[];
    private final int start;

    public DepthFirstDirectedPaths(Digraph digraph,int start) {
        this.digraph = digraph;
        marked=new boolean[digraph.getVertexNum()];
        edgeTo=new int[digraph.getVertexNum()];
        this.start=start;
        dfs(start);
    }

    public void dfs(int start){
        marked[start]=true;
        for (int vertex:digraph.adj(start)){
            if (!marked[vertex]){
                edgeTo[vertex]=start;
                dfs(vertex);
            }
        }
    }

    /**
     * @param vertex
     * @return 是否存在一条从start -> vertex的路径
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
