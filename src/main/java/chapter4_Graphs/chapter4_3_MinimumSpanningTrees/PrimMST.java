package chapter4_Graphs.chapter4_3_MinimumSpanningTrees;

import chapter4_Graphs.chapter4_2_DirectedGraphs.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author zhangyu
 * 2020/5/9 11:03
 * 即时版本的Prim算法
 */
public class PrimMST {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyEWG.txt";
        In in=new In(path);
        EdgeWeightedGraph weightedGraph=new EdgeWeightedGraph(in);
        PrimMST primMST=new PrimMST(weightedGraph);
        Iterator<Edge> iterator=primMST.edges().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        System.out.println((primMST.lazyWeight()==primMST.nonLazyWeight())+":"+primMST.lazyWeight()+"-"+primMST.nonLazyWeight());
    }
    /**
     * 是否在最小生成树中
     */
    private boolean[] marked;
    /**
     * edgeTo[w]：顶点w与树中某个顶点相连的最短距离边,
     */
    private Edge[] edgeTo;
    /**
     * distTo[w]：顶点w与树中某个顶点相连的最短边的权重
     */
    private double[] distTo;
    /**
     * 索引优先队列，存储有效的横切边
     */
    private IndexMinPQ<Double> indexMinPQ;
    /**
     * 最小生成树的权重
     */
    private double weight;

    public PrimMST(EdgeWeightedGraph weightedGraph) {
        marked=new boolean[weightedGraph.getVertexNum()];
        edgeTo=new Edge[weightedGraph.getVertexNum()];
        distTo=new double[weightedGraph.getVertexNum()];
        indexMinPQ=new IndexMinPQ<>(weightedGraph.getVertexNum());
        for (int i=0;i<weightedGraph.getVertexNum();i++){
            distTo[i]=Double.POSITIVE_INFINITY;
        }
        //用顶点0和权重0初始化
        indexMinPQ.insert(0,0.0);
        distTo[0]=0.0;
        while (!indexMinPQ.isEmpty()){
            visit(weightedGraph,indexMinPQ.delMin());
        }
    }

    private void visit(EdgeWeightedGraph weightedGraph,int vertex){
        //将顶点vertex加入到树中
        marked[vertex]=true;
        for (Edge edge:weightedGraph.adj(vertex)){
            int adjVertex=edge.other(vertex);
            //如果两个顶点都在树中，说明这条边失效
            if (marked[adjVertex]){
                continue;
            }
            //如果这条边的权重<之前adjVertex对应的权重，则更新
            if (edge.getWeight()<distTo[adjVertex]){
                weight-=distTo[adjVertex]==Double.POSITIVE_INFINITY?0:distTo[adjVertex];
                distTo[adjVertex]=edge.getWeight();
                edgeTo[adjVertex]=edge;
                weight+=distTo[adjVertex];
                //如果有效横切边中已经包含了这个顶点，
                // 则优先队列中用于比较的键减小，使用decreaseKey
                if (indexMinPQ.contains(adjVertex)){
                    indexMinPQ.decreaseKey(adjVertex,edge.getWeight());
                }else{
                    indexMinPQ.insert(adjVertex, edge.getWeight());
                }
            }
        }
    }

    /**
     * @return 获取最小生成树的边
     */
    public Iterable<Edge> edges(){
        //当最小生成树生成后，edgeTo[]中的边就是最小生成树的边
        //最小生成树是要包含所有顶点的
        LinkedList<Edge> mst=new LinkedList<>();
        //不需要顶点0的，因为边数=顶点数-1
        for (int i=1;i<edgeTo.length;i++){
            mst.add(edgeTo[i]);
        }
        return mst;
    }

    /**
     * @return 获取最小生成树的权重和，只有在需要时才会获取总权重
     */
    public double lazyWeight(){
        double weight=0;
         for (int i=1;i<distTo.length;i++){
             weight+=distTo[i];
         }
         return weight;
    }

    /**
     * @return 即时获取最小生成树权重
     */
    public double nonLazyWeight(){
        return weight;
    }
}
