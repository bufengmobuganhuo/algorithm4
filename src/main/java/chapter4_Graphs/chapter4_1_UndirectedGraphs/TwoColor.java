package chapter4_Graphs.chapter4_1_UndirectedGraphs;

import chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.TwoSum;
import edu.princeton.cs.algs4.In;

/**
 * @author zhangyu
 * 2020/4/21 16:16
 * 判断是否是二分图
 */
public class TwoColor {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyG.txt";
        Graph graph=new Graph(new In(path));
        TwoColor twoColor=new TwoColor(graph);
        System.out.print(twoColor.isTwoColorable);
    }
    private boolean[] marked;
    //存储每个顶点的颜色
    private boolean[] color;
    private boolean isTwoColorable;

    public TwoColor(Graph graph) {
        marked=new boolean[graph.getVertexNum()];
        color=new boolean[graph.getVertexNum()];
        isTwoColorable=true;
        for (int start=0;start<graph.getVertexNum();start++){
            if (!marked[start]){
                dfs(graph,start);
            }
        }
    }

    private void dfs(Graph graph,int start){
        marked[start]=true;
        for (int vertex:graph.adj(start)){
            //如果未被标记过，在使用上一层顶点的反颜色为其着色
            if (!marked[vertex]){
                color[vertex]=!color[start];
                dfs(graph,vertex);
                //已经被标记过，且颜色和上一层顶点（上一层顶点就是即与该顶点相连的顶点）同色
                //说明不是二分图
            }else if(color[vertex]==color[start]){
                isTwoColorable=false;
            }
        }
    }

    public boolean isTwoColorable(){
        return isTwoColorable;
    }
}
