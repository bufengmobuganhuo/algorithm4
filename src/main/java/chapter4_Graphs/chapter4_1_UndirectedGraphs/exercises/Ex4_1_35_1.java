package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/8/3 8:42 下午
 * 练习4.1.35：第一次尝试
 */
public class Ex4_1_35_1 {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Digraph graph=new Digraph(new In(path));
        Ex4_1_35_1 ex_4_1_35=new Ex4_1_35_1(graph);

    }
    // 在遍历过程中对每个结点编号，编号后不会发生改变
    private int[] dfn;
    // lower[vertex]：与该vertex相连的最小dfn
    private int[] lower;
    private int id;
    private int bridgeNum;

    public Ex4_1_35_1(Digraph graph) {
        dfn=new int[graph.getVertexNum()];
        lower=new int[graph.getVertexNum()];
        Arrays.fill(dfn,-1);
        Arrays.fill(lower,-1);
        for (int i = 0; i < graph.getVertexNum(); i++) {
            if (dfn[i]==-1){
                dfs(graph,i,i);
            }
        }
    }

    private void dfs(Digraph graph, int father, int child){
        dfn[child]=id++;
        lower[child]=dfn[child];
        for (int grandson : graph.adj(child)) {
            if (dfn[grandson]==-1){
                dfs(graph,child,grandson);
                lower[child]=Math.min(lower[child],lower[grandson]);
                if (lower[grandson]>=dfn[grandson]){
                    bridgeNum++;
                    System.out.println("bridge:"+child+"-"+grandson);
                }
            }else if (father!=grandson){
                lower[child]=Math.min(lower[child],dfn[grandson]);
            }
        }

    }
}
