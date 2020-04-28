package chapter4_Graphs.chapter4_1_UndirectedGraphs.exercises;

import chapter4_Graphs.chapter4_1_UndirectedGraphs.Graph;
import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * @author zhangyu
 * 2020/4/26 16:47
 * 练习4.1.16
 */
public class EX_4_1_16 {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\tinyG.txt";
        Graph graph=new Graph(new In(path));
        EX_4_1_16 ex_4_1_16=new EX_4_1_16(graph);
        System.out.println(ex_4_1_16.eccentricity(0));
        System.out.println(ex_4_1_16.diameter());
        System.out.println(ex_4_1_16.center());
        System.out.println(ex_4_1_16.girth());
    }
    private final Graph graph;
    private HashMap<Integer, Integer> vertexEccMap;

    public EX_4_1_16(Graph graph) {
        this.graph=graph;
        vertexEccMap=new HashMap<>(graph.getVertexNum());
    }

    /**
     * @param vertex
     * @return vertex的离心率：距离vertex最远的顶点的最短距离
     */
    public int eccentricity(int vertex){
        int maxLevel=0;
        boolean[] marked=new boolean[graph.getVertexNum()];
        Queue<Integer> queue=new LinkedList<>();
        //一层中最后一个顶点
        int lastVertexInLevel=vertex;
        //遍历的过程中，访问到的最后一个顶点
        int tempLstVertexInLevel=vertex;

        marked[vertex]=true;
        queue.add(vertex);
        while (!queue.isEmpty()){
            int tempStart=queue.poll();
            for (int adjVertex:graph.adj(tempStart)){
                if (!marked[adjVertex]){
                    queue.add(adjVertex);
                    tempLstVertexInLevel=adjVertex;
                    marked[adjVertex]=true;
                }
            }
            //如果遍历到了当前层的最后一个顶点，则层数+1
            if (tempStart==lastVertexInLevel){
                maxLevel++;
                lastVertexInLevel=tempLstVertexInLevel;
            }
        }
        return maxLevel-1;
    }

    /**
     * @return 半径：所有顶点的最大离心率
     */
    public int diameter(){
        int maxEccentricity=Integer.MIN_VALUE;
        if (vertexEccMap.size()==graph.getVertexNum()){
            for (Map.Entry<Integer,Integer> entry:vertexEccMap.entrySet()){
                maxEccentricity=Math.max(maxEccentricity,entry.getValue());
            }
            return maxEccentricity;
        }
        //将遍历过程中得到的结果保存下来
        for (int i=0;i<graph.getVertexNum();i++){
            int eccentricity=eccentricity(i);
            vertexEccMap.put(i,eccentricity);
            maxEccentricity=Math.max(maxEccentricity,eccentricity);
        }
        return maxEccentricity;
    }

    /**
     * @return 半径：所有顶点的最小离心率
     */
    public int radius(){
        int minEccentricity=Integer.MAX_VALUE;
        if (vertexEccMap.size()==graph.getVertexNum()){
            for (Map.Entry<Integer,Integer> entry:vertexEccMap.entrySet()){
                minEccentricity=Math.min(minEccentricity,entry.getValue());
            }
            return minEccentricity;
        }
        for (int i=0;i<graph.getVertexNum();i++){
            int eccentricity=eccentricity(i);
            vertexEccMap.put(i,eccentricity);
            minEccentricity=Math.min(minEccentricity,eccentricity);
        }
        return minEccentricity;
    }

    /**
     * @return 图的中点：离心率=半径的顶点
     */
    public int center(){
        int radius= radius();
        for (Map.Entry<Integer,Integer> entry:vertexEccMap.entrySet()){
            if (radius==entry.getValue()){
                return entry.getKey();
            }
        }
        return -1;
    }

    /**
     * @return 图的周长：图中最短环的长度
     */
    public int girth(){
        int minLen=Integer.MAX_VALUE;
        for (int i=0;i<graph.getVertexNum();i++){
            minLen=Math.min(minLen,bfs(i));
        }
        return minLen;
    }
    private int bfs(int start){
        //最短环长度
        int minLen=Integer.MAX_VALUE;
        Queue<Integer> queue = new LinkedList<>();
        //distTo[i]:从起点到顶点i的距离
        int[] distTo=new int[graph.getVertexNum()];
        Arrays.fill(distTo,-1);
        //edgeTo[i]=v:从v可以到达i
        int[] edgeTo=new int[graph.getVertexNum()];
        queue.add(start);
        distTo[start]=0;
        while (!queue.isEmpty()){
            int tempStart=queue.poll();
            for (int vertex:graph.adj(tempStart)){
                //=-1说明地址没有被赋值，没有被访问过
                if (distTo[vertex]==-1){
                    queue.add(vertex);
                    edgeTo[vertex]=tempStart;
                    distTo[vertex]=distTo[tempStart]+1;
                    //如果这个顶点被标记过，而且不是上一层的顶点
                    // （从0 -> 1为上一层，在下一层中从1 -> 0又被访问一遍），
                    // 说明存在一条路径，让起点经过这个路径后又回到了起点（环）
                }else if (edgeTo[tempStart]!=vertex){
                    minLen=Math.min(minLen,distTo[vertex]+distTo[tempStart]+1);
                }
            }
        }
        return minLen;
    }
}
