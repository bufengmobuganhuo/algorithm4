package chapter4_Graphs.chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/4/21 10:52
 * 使用深度优先搜索查找一条从起点到目的顶点的路径
 */
public class DepthFirstPaths {
    public static void main(String[] args) {
        String path="/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Digraph graph=new Digraph(new In(path));
        DepthFirstPaths depthFirstPaths=new DepthFirstPaths(graph,0);
        Stack<Integer> paths= (Stack<Integer>) depthFirstPaths.pathTo(5);
        while (!paths.isEmpty()){
            System.out.print(paths.pop()+"-");
        }
    }
    private boolean[] marked;
    private int[] edgeTo;
    /**
     * 起点
     */
    private final int start;

    public DepthFirstPaths(Digraph graph, int start) {
        marked=new boolean[graph.getVertexNum()];
        edgeTo=new int[graph.getVertexNum()];
        this.start = start;
        dfs(graph,start);
    }

    public void dfs(Digraph graph, int start){
        //将顶点标记
        marked[start]=true;
        for (int vertex:graph.adj(start)){
            //如果vertex没有被访问过
            if (!marked[vertex]){
                //从start可以到达vertex
                edgeTo[vertex]=start;
                dfs(graph,vertex);
            }
        }
    }

    /**
     * @param target 是否有一条从起点到target的路径
     * @return
     */
    public boolean hasPathTo(int target){
        //从起点开始深度优先搜索，如果target被标记过，
        // 则表明从起点可以到target
        return marked[target];
    }

    /**
     * @param target 从起点到target的路径
     * @return
     */
    public Iterable<Integer> pathTo(int target){
        if (!hasPathTo(target)){
            return null;
        }
        Stack<Integer> paths=new Stack<>();
        /*
         *  从目标顶点开始，从edgeTo[target]可以到达target，
         *  从edgeTo[edgeTo[target]]又可以达到edgeTo[target]，
         *  以此向上推，直到遇到起点
         * */
        for (int tempVertex=target;tempVertex!=start;tempVertex=edgeTo[tempVertex]){
            paths.push(tempVertex);
        }
        //最后一个把起点放进去
        paths.push(start);
        return paths;
    }

}
