package chapter4_Graphs.chapter4_2_DirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/4/29 19:26
 * 寻找有向环
 */
public class DirectedCycle {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyDG";
        In in=new In(path);
        Digraph digraph=new Digraph(in);
        DirectedCycle directedCycle=new DirectedCycle(digraph);
        if (directedCycle.hasCycle()){
            Stack<Integer> cycle= (Stack<Integer>) directedCycle.cycle();
            while (!cycle.isEmpty()){
                System.out.print(cycle.pop()+"-");
            }
        }
    }
    private boolean[] marked;
    private int[] edgeTo;
    /**
     *表示在深度优先搜索时被遍历到的一条路径上的顶点（在递归调用栈上的顶点）
     */
    private boolean[] onStack;
    private Stack<Integer> path;

    public DirectedCycle(Digraph digraph) {
        marked=new boolean[digraph.getVertexNum()];
        edgeTo=new int[digraph.getVertexNum()];
        onStack=new boolean[digraph.getVertexNum()];
        for (int i=0;i<digraph.getVertexNum();i++){
            if (!marked[i]){
                dfs(digraph,i);
            }
        }
    }

    public void dfs(Digraph digraph,int start){
        marked[start]=true;
        //遍历到start，则该顶点在递归调用栈上
        onStack[start]=true;
        for (int vertex:digraph.adj(start)){
            //如果已经判定有环，则返回
            if (hasCycle()){
                return;
                //还没有访问过
            }else if(!marked[vertex]){
                edgeTo[vertex]=start;
                dfs(digraph,vertex);
                //如果被访问过，并且在递归调用栈上（表明在一条路径上），
                //说明经过一条路径，又回到了顶点vertex（上一层的），出现环
            }else if (onStack[vertex]){
                path=new Stack<>();
                //edgeTo[vertex]=start表示：start -> vertex，
                //对于一个环：2 <=> 3，首先2 -> 3，edgeTo[3]=2,
                // 但是edgeTo[2]一定不是3(2是从别的顶点访问过来的)
                //当从3 -> 2访问时，此时判定有环，应当从edgeTo[3]开始追溯才能到2，而不能从2开始
                for (int temp=start;temp!=vertex;temp=edgeTo[temp]){
                    path.push(temp);
                }
                path.push(vertex);
                path.push(start);
            }
        }
        //当前顶点被遍历结束，不再栈上
        onStack[start]=false;
    }

    /**
     * @return 图是否有环
     */
    public boolean hasCycle(){
        return path!=null;
    }

    public Iterable<Integer> cycle(){
        return path;
    }
}
