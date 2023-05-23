package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/8/5 8:53 上午
 * 练习4.2.20：有向欧拉环第一次尝试
 */
public class EX_4_2_20_DirectedEulerCycle_1 {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/EulerCycle.txt";
        In in=new In(path);

        Digraph digraph=new Digraph(in);
        EX_4_2_20_DirectedEulerCycle_1 ex_4_2_20_directedEulerCycle_1=new EX_4_2_20_DirectedEulerCycle_1(digraph);

        while (!ex_4_2_20_directedEulerCycle_1.cycle.isEmpty()){
            System.out.print(ex_4_2_20_directedEulerCycle_1.cycle.pop()+"-");
        }
    }
    private Stack<Integer> cycle;
    private Digraph digraph;

    public EX_4_2_20_DirectedEulerCycle_1(Digraph digraph) {
        this.digraph = digraph;
        getEulerCycle();
    }

    private void getEulerCycle(){
        // 判断出度是否=入度
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (digraph.indegree(i)!=digraph.outdegree(i)){
                return;
            }
        }
        cycle=new Stack<>();
        Stack<Integer> stack=new Stack<>();
        Iterator<Integer>[] adj=new Iterator[digraph.getVertexNum()];
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            adj[i]=digraph.adj(i).iterator();
        }
        int nonIsolateVertex=getNonIsolateVertex();
        stack.push(nonIsolateVertex);
        while(!stack.isEmpty()){
            int vertex=stack.pop();
            while(adj[vertex].hasNext()){
                stack.push(vertex);
                vertex=adj[vertex].next();
            }
            cycle.push(vertex);
        }
    }

    // 获取非孤立结点
    private int getNonIsolateVertex(){
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (digraph.outdegree(i)!=0){
                return i;
            }
        }
        return -1;
    }
}
