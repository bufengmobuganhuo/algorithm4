package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/11/18 上午9:17
 * TODO
 */
public class Ex_4_1_35_1 {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Digraph graph=new Digraph(new In(path));
        System.out.println(solution(graph));
    }
    private static int res;
    private static int cnt;
    private static int[] dfn;
    private static int[] low;

    public static int solution(Digraph graph) {
        dfn = new int[graph.getVertexNum()];
        low = new int[graph.getVertexNum()];
        Arrays.fill(dfn, -1);
        for (int i = 0; i < graph.getVertexNum(); i++) {
            if (dfn[i] == -1) {
                dfs(graph, i, i);
            }
        }
        return res;
    }

    private static void dfs(Digraph graph, int father, int child) {
        dfn[child] = cnt++;
        low[child] = dfn[child];
        for (int grandson : graph.adj(child)) {
            if (dfn[grandson] == -1) {
                dfs(graph, child, grandson);
                low[child] = Math.min(low[child], low[grandson]);
                if (low[grandson] >= dfn[grandson]) {
                    StdOut.println(child + "-" + grandson + " is a bridge");
                    res++;
                }
            } else if (grandson != father) {
                low[child] = Math.min(low[child], dfn[grandson]);
            }
        }
    }
}
