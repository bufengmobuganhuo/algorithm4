package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.BreadFirstDirectedPaths;
import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Stack;
import java.util.zip.DeflaterInputStream;

/**
 * @author zhangyu
 * 2020/5/7 15:40
 * 练习4.2.40：给定一个有向图，设计一个算法来寻找边数最小的有向环（或报告图是无环的）。
 *              在最坏的情况下，算法的运行时间应该与EV成正比。
 *
 */
public class EX_4_2_40_ShortestCycle {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyDG";
        In in=new In(path);

        Digraph digraph=new Digraph(in);
        EX_4_2_40_ShortestCycle ex_4_2_40_shortestCycle=new EX_4_2_40_ShortestCycle(digraph);
        Stack<Integer> cycle=ex_4_2_40_shortestCycle.getCycle();
        while (!cycle.isEmpty()){
            System.out.print(cycle.pop());
        }
    }
    private Stack<Integer> cycle;

    public EX_4_2_40_ShortestCycle(Digraph digraph) {
        //反向图
        Digraph gReverse=digraph.reverse();
        int length=digraph.getVertexNum()+1;
        for (int vertex=0;vertex<digraph.getVertexNum();vertex++){
            BreadFirstDirectedPaths bfs=new BreadFirstDirectedPaths(gReverse, vertex);
            for (int adjVertex:digraph.adj(vertex)){
                /*
                 * 在正向图中是 vertex -> adjVertex，
                 * 又在反向图中存在“最短路径”：vertex -> adjVertex，
                 * 说明在正向图中存在“最短路径”：adjVertex -> vertex，说明正向图中有环
                 * 环的长度为 dist(vertex->adjVertex)+dist(adjVertex->vertex)
                 * */
                if (bfs.hasPathTo(adjVertex)&&(bfs.distTo(adjVertex)+1)<length){
                    cycle=new Stack<>();
                    Stack<Integer> path=bfs.pathTo(adjVertex);
                    while (!path.isEmpty()){
                        cycle.push(path.pop());
                    }
                    cycle.push(vertex);
                }
            }
        }
    }

    public boolean hasCycle(){
        return cycle!=null;
    }

    public Stack<Integer> getCycle(){
        return cycle;
    }
}
