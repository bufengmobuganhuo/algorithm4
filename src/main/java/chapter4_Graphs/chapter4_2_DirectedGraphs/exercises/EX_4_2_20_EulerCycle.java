package chapter4_Graphs.chapter4_2_DirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Digraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/7 10:55
 * 练习4.2.20：无向图的欧拉环
 * 1.必要条件：无向图所有顶点的度数均为偶数
 */
public class EX_4_2_20_EulerCycle {
    private Stack<Integer> cycle;

    public EX_4_2_20_EulerCycle(Digraph graph) {
        if (graph.getEdgeNum()==0){
            return;
        }
        //检查必要条件
        for (int i=0;i<graph.getVertexNum();i++){
            if (graph.degree(i)%2!=0){
                return;
            }
        }

        Queue<Edge>[] adj=new LinkedList[graph.getVertexNum()];
        for (int i=0;i<adj.length;i++){
            adj[i]=new LinkedList<>();
        }
        for (int i=0;i<graph.getVertexNum();i++){
            int selfLoops=0;
            for (int vertex:graph.adj(i)){
                //如果是自环
                if (vertex==i){
                    Edge edge=new Edge(i,vertex);
                    adj[i].offer(edge);
                    adj[vertex].offer(edge);
                    selfLoops++;
                    //规定了从小顶点到大顶点开始构建有向图，避免了重复构建边
                }else if(i<vertex){
                    Edge edge=new Edge(i,vertex);
                    adj[i].offer(edge);
                    adj[vertex].offer(edge);
                }
            }
            //以下方式和寻找有向图欧拉环的方法一致
            Stack<Integer> stack = new Stack<>();
            int nonIsolateVertex=getNonIsolateVertex(graph);
            stack.push(nonIsolateVertex);
            cycle=new Stack<>();
            while (!stack.isEmpty()){
                int vertex=stack.pop();
                while (!adj[vertex].isEmpty()){
                    Edge edge = adj[vertex].poll();
                    if (edge.isUsed){
                        continue;
                    }
                    edge.isUsed=true;
                    stack.push(vertex);
                    vertex=edge.otherVertex(vertex);
                }
                cycle.push(vertex);
            }
        }
    }

    public Stack<Integer> getCycle(){
        return cycle;
    }

    public boolean hasCycle(){
        return cycle!=null;
    }

    private int getNonIsolateVertex(Digraph graph){
        for (int i=0;i<graph.getVertexNum();i++){
            if (graph.degree(i)>0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 代表一条 vertex1 --- vertex2的无向边，
     * 实际上也是有向图的一条双向边： vertex1 <-> vertex2
     */
    private class Edge{
        private int vertex1;
        private int vertex2;
        private boolean isUsed;

        public Edge(int vertex1, int vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.isUsed=false;
        }

        /**
         * @param vertex
         * @return 获取与vertex相连的另一顶点
         */
        public int otherVertex(int vertex){
            if (vertex==vertex1){
                return vertex2;
            }else if(vertex==vertex2){
                return vertex1;
            }else{
                throw new IllegalArgumentException();
            }
        }
    }
}
