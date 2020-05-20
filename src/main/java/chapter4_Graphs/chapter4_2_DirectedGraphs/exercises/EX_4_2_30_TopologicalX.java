package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhangyu
 * 2020/5/7 14:53
 * 练习4.2.30：基于队列的拓扑排序
 */
public class EX_4_2_30_TopologicalX {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyDG";
        In in=new In(path);

        Digraph digraph=new Digraph(in);
        EX_4_2_30_TopologicalX ex_4_2_30TopologicalX =new EX_4_2_30_TopologicalX(digraph);
        Queue<Integer> order= ex_4_2_30TopologicalX.getOrder();
        while (!order.isEmpty()){
            System.out.print(order.poll()+"-");
        }
    }
    private Queue<Integer> order;
    //每个顶点对应的入度
    private int[] indegrees;
    private int count;

    public EX_4_2_30_TopologicalX(Digraph digraph) {
        Queue<Integer> queue = new LinkedList<>();
        indegrees=new int[digraph.getVertexNum()];
        //初始化所有顶点的入度
        for (int i=0;i<digraph.getVertexNum();i++){
            indegrees[i]=digraph.indegree(i);
            //如果顶点的入度为0，则放入队列
            if (indegrees[i]==0){
                queue.offer(i);
            }
        }

        order=new LinkedList<>();
        while (!queue.isEmpty()){
            int vertex=queue.poll();
            //从queue中取出来的都是入度为0的
            order.offer(vertex);
            count++;
            for (int adjVertex:digraph.adj(vertex)){
                if (--indegrees[adjVertex]==0){
                    queue.add(adjVertex);
                }
            }
        }

        if (count!=digraph.getVertexNum()){
            order=null;
        }
    }

    public Queue<Integer> getOrder(){
        return order;
    }
}
