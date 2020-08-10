package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Graph;
import chapter4_Graphs.chapter4_2_DirectedGraphs.BreadFirstDirectedPaths;
import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;
import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;

/**
 * @author zhangyu
 * 2020/5/6 16:29
 * 练习4.2.22：最短先导路径=min(vertex1到公共祖先最短路径长度+vertex2到公共祖先最短路径长度)
 */
public class EX_4_2_22 {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyDG";
        In in=new In(path);
        Digraph digraph=new Digraph(in);
        EX_4_2_22 ex_4_2_22 = new EX_4_2_22();
        System.out.print(ex_4_2_22.shortestAncestorLength(digraph,0,0));
    }
    public int shortestAncestorLength(Digraph digraph,int vertex1, int vertex2){
        //分别以vertex1,vertex2为起点做bfs
        BreadFirstDirectedPaths breadFirstDirectedPaths1=new BreadFirstDirectedPaths(digraph.reverse(),vertex1);
        BreadFirstDirectedPaths breadFirstDirectedPaths2=new BreadFirstDirectedPaths(digraph.reverse(),vertex2);

        Integer minLen=Integer.MAX_VALUE;
        for (int i=0;i<digraph.getVertexNum();i++){
            //如果都有路径
            if (breadFirstDirectedPaths1.hasPathTo(i)&&breadFirstDirectedPaths2.hasPathTo(i)){
                minLen=Math.min(breadFirstDirectedPaths1.distTo(i)+breadFirstDirectedPaths2.distTo(i),minLen);
            }
        }
        return minLen;
    }
}
