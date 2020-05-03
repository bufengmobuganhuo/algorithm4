package chapter4_Graphs.chapter4_2_DirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/1 17:26
 * Kosaraju判断强连通分量
 */
public class KosarajuSCC {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyDG";
        In in=new In(path);
        Digraph digraph=new Digraph(in);
        KosarajuSCC kosarajuSCC=new KosarajuSCC(digraph);
        System.out.print(kosarajuSCC.isStronglyConnected(2,3));
        System.out.println(kosarajuSCC.getCount());
    }
    private boolean[] marked;
    //各个顶点所在强连通分量Id
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph digraph) {
        marked=new boolean[digraph.getVertexNum()];
        id=new int[digraph.getVertexNum()];
        DepthFirstOrder depthFirstOrder=new DepthFirstOrder(digraph.reverse());
        Stack<Integer> stack= (Stack<Integer>) depthFirstOrder.reversePost();
        while (!stack.isEmpty()){
            int vertex=stack.pop();
            if (!marked[vertex]){
                dfs(digraph,vertex);
                count++;
            }
        }
    }

    private void dfs(Digraph digraph,int start){
        marked[start]=true;
        id[start]=count;
        for (int vertex:digraph.adj(start)){
            if (!marked[vertex]){
                dfs(digraph, vertex);
            }
        }
    }

    /**
     * @param vertex1
     * @param vertex2
     * @return 两个顶点是否强连通
     */
    public boolean isStronglyConnected(int vertex1,int vertex2){
        return id[vertex1]==id[vertex2];
    }

    /**
     * @param vertex
     * @return 返回顶点所在强连通分量id
     */
    public int id(int vertex){
        return id[vertex];
    }

    /**
     * @return 获取连通分两个数
     */
    public int getCount(){
        return count;
    }
}
