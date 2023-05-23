package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/4/28 16:32
 * 练习4.1.35:边的连通性
 * 桥：如果两个顶点a,b只有一条路径从a到b（或b到a，无向），说明就是桥
 */
public class EX_4_1_35 {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Graph graph=new Graph(new In(path));
        EX_4_1_35 ex_4_1_35=new EX_4_1_35(graph);

    }
    private int bridges;      // number of bridges
    private int cnt;          // counter
    //dfn[v]：顶点v在深度优先搜索时被搜索到的编号（时间戳）
    //一次固定，永不变
    private int[] dfn;
    //low[i]:与顶点i相连顶点的最小dfn(除去其父顶点)
    private int[] low;

    public EX_4_1_35(Graph graph) {
        low = new int[graph.V()];
        dfn = new int[graph.V()];
        Arrays.fill(dfn,-1);

        for (int v = 0; v < graph.V(); v++){
            if (dfn[v] == -1){
                dfs(graph, v, v);
            }
        }
    }

    public int components() { return bridges + 1; }

    /**
     * 给每个结点按照遍历顺序从小到大编号，对于一个结点，一定有一条从较小编号到该结点的路径（上层结点->该结点）
     * 如果该结点还可以从一个更大编号的路径到达其上一层结点，则不是桥
     * @param graph
     * @param father
     * @param child
     */
    private void dfs(Graph graph, int father, int child) {
        dfn[child] = cnt++;
        low[child] = dfn[child];
        for (int grandson : graph.adj(child)) {
            if (dfn[grandson] == -1) {
                dfs(graph, child, grandson);
                low[child] = Math.min(low[child], low[grandson]);
                //low[grandson]>=dfn[grandson]，说明只能从儿子顶点到达孙子顶点（往下走）
                //而不能使用不经过父顶点的路径来往上走（根据访问顺序，编号是从小->大的，往dfn更小的地方走）
                //则说明child-grandson是一个桥
                if (low[grandson] >= dfn[grandson]) {
                    StdOut.println(child + "-" + grandson + " is a bridge");
                    bridges++;
                }
                //保证方向，是从child -> grandson，而不是 grandson(father) -> child
            } else if (grandson != father){
                //因为规定了方向：father -> child -> grandson，并且除去了父顶点
                //所以与child相连顶点的最小dfn值只能是dfn[grandson](grandson已经访问过了，所以有可能会更小)和其本身
                low[child] = Math.min(low[child], dfn[grandson]);
            }
        }
    }
}
