package com.mengyu.algs4.knowledge.chapter4_Graphs.chapter4_4_ShortestPaths;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author zhangyu
 * 2020/5/18 16:27
 * 并行任务调度
 */
public class CPM {
    public static void main(String[] args) {
        String path="F:\\Algorithm4\\src\\main\\resources\\jobsPC.txt";
        In in=new In(path);
        parallelTaskScheduling(in);
    }
    public static void parallelTaskScheduling(In in){
        int taskNum=in.readInt();
        in.readLine();
        //一个任务对应两个顶点（起始，结束）+ 整个图的两个起点和终点
        EdgeWeightDigraph weightDigraph = new EdgeWeightDigraph(2*taskNum+2);
        //图的起点和终点
        int start = 2*taskNum;
        int terminate = 2*taskNum+1;
        for (int i=0;i<taskNum;i++){
            String[] params=in.readLine().split("\\s+");
            //耗时
            double duration=Double.parseDouble(params[0]);
            //任务边
            weightDigraph.addEdge(new DirectedEdge(i,i+taskNum,duration));
            //图起点 -> 任务起始顶点
            weightDigraph.addEdge(new DirectedEdge(start,i,0.0));
            //任务结束顶点 -> 图终点
            weightDigraph.addEdge(new DirectedEdge(i+taskNum,terminate,0.0));
            for (int j=1;j<params.length;j++){
                //任务结束顶点 -> 优先级较低任务的任务起始顶点
                weightDigraph.addEdge(new DirectedEdge(i+taskNum,Integer.parseInt(params[j]),0.0));
            }
        }

        AcyclicLongestPath acyclicLongestPath = new AcyclicLongestPath(weightDigraph,start);
        StdOut.println("Start times:");
        for (int i=0;i<taskNum;i++){
            StdOut.printf("%4d: %5.1f\n",i,acyclicLongestPath.distTo(i));
        }
        StdOut.printf("Finish time: %5.1f\n",acyclicLongestPath.distTo(terminate));

    }
}
