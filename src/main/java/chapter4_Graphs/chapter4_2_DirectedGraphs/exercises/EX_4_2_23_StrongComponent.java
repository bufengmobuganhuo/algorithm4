package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author zhangyu
 * 2020/5/6 17:11
 * 练习4.2.23：强连通分量
 */
public class EX_4_2_23_StrongComponent {
    public static void main(String[] args) {
        Set<Integer> set=new HashSet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        Set<Integer> set1=new HashSet<>();
        set1.add(1);
        set1.add(5);
        set1.add(7);
        set.retainAll(set1);
        for (Integer integer:set){
            System.out.println(integer);
        }
    }
    /**
     * @param vertex
     * @return 查找顶点vertex的连通分量
     */
    public Set<Integer> strongComponent(Digraph digraph,int vertex){
        //找到vertex可以到达的顶点
        Set<Integer> vertexCanReachSet=new HashSet<>();
        for (Integer reachableVertex : digraph.adj(vertex)) {
            vertexCanReachSet.add(reachableVertex);
        }

        //所有可以到达vertex的顶点
        Set<Integer> vertexCanBeReachedSet=new HashSet<>();
        for (int i=0;i<digraph.getVertexNum();i++){
            LinkedList<Integer> adj= (LinkedList<Integer>) digraph.adj(i);
            if (adj.contains(vertex)){
                vertexCanBeReachedSet.add(i);
            }
        }

        //二者取交集
        vertexCanReachSet.retainAll(vertexCanBeReachedSet);
        return vertexCanReachSet;
    }
}
