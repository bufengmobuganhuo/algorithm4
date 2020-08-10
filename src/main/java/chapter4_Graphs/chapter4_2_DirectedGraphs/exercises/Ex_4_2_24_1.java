package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/8/6 11:22 上午
 * 练习4.2.24：第一次尝试
 */
public class Ex_4_2_24_1 {
    private Stack<Integer> reversePostOrder;
    private boolean[] marked;

    public boolean hasHimltonPath(Digraph digraph){
        getTopologicalOrder(digraph);
        int vertex1=reversePostOrder.pop();
        while (!reversePostOrder.isEmpty()){
            int vertex2=reversePostOrder.pop();
            if (digraph.hasEdge(vertex1,vertex2)){
                return false;
            }
            vertex1=vertex2;
        }
        return true;
    }

    private void getTopologicalOrder(Digraph digraph){
        reversePostOrder=new Stack<>();
        marked=new boolean[digraph.getVertexNum()];
        for (int i = 0; i < digraph.getVertexNum(); i++) {
            if (!marked[i]){
                dfs(digraph,i);
            }
        }
    }

    private void dfs(Digraph digraph, int vertex){
        marked[vertex]=true;
        for (int adjVertex:digraph.adj(vertex)){
            if (!marked[vertex]){
                dfs(digraph,adjVertex);
            }
        }
        reversePostOrder.push(vertex);
    }
}
