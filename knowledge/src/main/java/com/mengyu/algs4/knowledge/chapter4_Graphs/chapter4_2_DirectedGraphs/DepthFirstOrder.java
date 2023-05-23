package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/4/30 11:57
 * 顶点的深度优先次序
 */
public class DepthFirstOrder {
    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        for (int item:stack){
            System.out.print(item);
        }
    }
    private boolean[] marked;
    //前序
    private Queue<Integer> preQueue;
    //后序
    private Queue<Integer> postQueue;
    //逆后序
    private Stack<Integer> reversePostStack;

    public DepthFirstOrder(Digraph digraph) {
        marked=new boolean[digraph.getVertexNum()];
        preQueue=new LinkedList<>();
        postQueue=new LinkedList<>();
        reversePostStack=new Stack<>();
        for (int i=0;i<digraph.getVertexNum();i++){
            if (!marked[i]){
                dfs(digraph,i);
            }
        }
    }

    private void dfs(Digraph digraph,int start){
        marked[start]=true;
        //在递归调用前
        preQueue.offer(start);
        for (Integer vertex:digraph.adj(start)){
            if (!marked[vertex]){
                dfs(digraph,vertex);
            }
        }
        //在递归调用后
        postQueue.offer(start);
        reversePostStack.push(start);
    }

    public Iterable<Integer> pre(){
        return preQueue;
    }

    public Iterable<Integer> post(){
        return postQueue;
    }

    public Iterable<Integer> reversePost(){
        return reversePostStack;
    }
}
