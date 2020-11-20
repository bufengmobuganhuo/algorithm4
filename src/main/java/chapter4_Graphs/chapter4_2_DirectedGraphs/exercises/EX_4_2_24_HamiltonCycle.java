package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;
import chapter4_Graphs.chapter4_2_DirectedGraphs.Topological;
import com.sun.org.apache.bcel.internal.classfile.StackMap;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/7 14:16
 * 练习4.2.24：判断有向无环图中是否存在汉密尔顿环
 */
public class EX_4_2_24_HamiltonCycle {
    public boolean hasCycle(Digraph digraph) {
        Topological topological = new Topological(digraph);
        Stack<Integer> order = (Stack<Integer>) topological.getOrder();
        if (order.isEmpty()) {
            return true;
        }
        int vertex1 = order.pop();
        while (!order.isEmpty()) {
            int vertex2 = order.pop();
            if (!digraph.hasEdge(vertex1, vertex2)) {
                return false;
            }
            vertex1 = vertex2;
        }
        return true;
    }
}
