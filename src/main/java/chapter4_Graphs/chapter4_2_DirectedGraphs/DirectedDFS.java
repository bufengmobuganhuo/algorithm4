package chapter4_Graphs.chapter4_2_DirectedGraphs;

import java.util.Iterator;

/**
 * @author zhangyu
 * 2020/4/29 10:53
 * TODO
 */
public class DirectedDFS {
    private boolean[] marked;
    public DirectedDFS(Digraph digraph,int start) {
        marked=new boolean[digraph.getVertexNum()];
        dfs(digraph,start);
    }

    public DirectedDFS(Digraph digraph,Iterable<Integer> starts){
        marked=new boolean[digraph.getVertexNum()];
        for (int start:starts){
            if (!marked[start]){
                dfs(digraph,start);
            }
        }
    }

    private void dfs(Digraph digraph,int start){
        marked[start]=true;
        for (int vertex:digraph.adj(start)){
            if (!marked[vertex]){
                dfs(digraph,vertex);
            }
        }
    }

    /**
     * @param vertex
     * @return 是否能从给定起点到达vertex
     */
    public boolean marked(int vertex){
        return marked[vertex];
    }
}
