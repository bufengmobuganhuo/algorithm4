package chapter4_Graphs.chapter4_2_DirectedGraphs;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import edu.princeton.cs.algs4.In;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/4/30 16:05
 * 基于深度优先搜索的拓扑排序
 */
public class Topological {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyDG";
        In in=new In(path);
        Digraph digraph=new Digraph(in);
        Topological topological=new Topological(digraph);
        if (topological.isDAG()){
            Stack<Integer> order= (Stack<Integer>) topological.getOrder();
            while (!order.isEmpty()){
                System.out.print(order.pop()+"-");
            }
        }
    }
    private Iterable<Integer> order;

    public Topological(Digraph digraph) {
        DirectedCycle cycle=new DirectedCycle(digraph);
        if (!cycle.hasCycle()){
            DepthFirstOrder dfo=new DepthFirstOrder(digraph);
            order=dfo.reversePost();
        }
    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    /**
     * @return 是否是有向无环图
     */
    public boolean isDAG(){
        return order!=null;
    }

}
