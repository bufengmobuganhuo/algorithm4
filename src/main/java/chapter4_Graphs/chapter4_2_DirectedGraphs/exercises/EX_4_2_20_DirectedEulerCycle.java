package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/6 11:36
 * 练习4.2.20：有向图的欧拉环
 * 1.欧拉环：访问每条边一次且仅一次的环
 * 2.条件：当且仅当有向图G是连通的且每个顶点的出度=入度时，G含有一条有向欧拉环
 * 3.解决：遍历边的话，需要从一个顶点A跳到另外一个（顶点A可达的）顶点
 */
public class EX_4_2_20_DirectedEulerCycle {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/EulerCycle.txt";
        In in=new In(path);

        Digraph digraph=new Digraph(in);
        EX_4_2_20_DirectedEulerCycle ex_4_2_20DirectedEulerCycle =new EX_4_2_20_DirectedEulerCycle(digraph);
        Stack<Integer> cycle= ex_4_2_20DirectedEulerCycle.getCycle();

        while (!cycle.isEmpty()){
            System.out.print(cycle.pop()+"-");
        }
    }
    private Stack<Integer> cycle=null;
    private final Digraph digraph;
    public EX_4_2_20_DirectedEulerCycle(Digraph digraph) {
        this.digraph=digraph;
        getEulerCycle(digraph);
    }

    private void getEulerCycle(Digraph digraph){
        //如果有顶点的出入度不相同，则没有欧拉环
        for (int vertex=0;vertex<digraph.getVertexNum();vertex++){
            if (digraph.indegree(vertex)!=digraph.outdegree(vertex)){
                return;
            }
        }

        Iterator<Integer>[] adj=new Iterator[digraph.getVertexNum()];
        for (int vertex=0;vertex<digraph.getVertexNum();vertex++){
            adj[vertex]=digraph.adj(vertex).iterator();
        }

        //获取到不孤立的顶点
        int nonIsolateVertex=getNonIsolateVertex(digraph);
        Stack<Integer> stack=new Stack<>();
        stack.push(nonIsolateVertex);
        cycle=new Stack<>();
        while (!stack.isEmpty()){
            int vertex=stack.pop();
            while (adj[vertex].hasNext()){
                stack.push(vertex);
                //从一个顶点跳到另一个可达的顶点
                vertex=adj[vertex].next();
            }
            cycle.push(vertex);
        }
    }

    public Stack<Integer> getCycle(){
        return cycle;
    }

    /**
     * @param digraph
     * @return 获取到不孤立的顶点
     */
    private int getNonIsolateVertex(Digraph digraph){
        for (int vertex=0;vertex<digraph.getVertexNum();vertex++){
            if (digraph.outdegree(vertex)!=0){
                return vertex;
            }
        }
        return -1;
    }
}
