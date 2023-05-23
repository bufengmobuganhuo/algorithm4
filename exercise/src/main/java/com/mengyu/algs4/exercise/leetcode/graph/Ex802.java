package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/8/14 4:26 下午
 * 查找最终的安装状态
 * 1. 对于一个出度为0的顶点，则一定是安全的
 * 2. 将上述顶点放入队列中(假设为vertex)，并循环遍历：
 *  1⃣️ 如果能到达该顶点(起点)的顶点(即一条边的起点)在去除了该顶点之后的出度为0，则说明这个起点也是安全的
 *  2⃣️ 因为要从终点定位到起点，则需要反向图来定位
 */
public class Ex802 {
    public static void main(String[] args) {
        int[][] G={{1,2},{2,3},{5},{0},{5},{},{}};
        Ex802 ex802=new Ex802();
        System.out.println(Arrays.toString(ex802.eventualSafeNodes(G).toArray()));
    }
    public List<Integer> eventualSafeNodes(int[][] G) {
        // 将数组变成背包形式，便于查找
        List<HashSet<Integer>> graph=new ArrayList<>(G.length);
        // 反向图
        List<HashSet<Integer>> reGraph=new ArrayList<>(G.length);
        // 初始化
        for (int i = 0; i < G.length; i++) {
            graph.add(new HashSet<>());
            reGraph.add(new HashSet<>());
        }
        List<Integer> ans=new ArrayList<>();
        Queue<Integer> queue=new LinkedList<>();
        // 构建反向图，筛选出度=0的顶点
        for (int i = 0; i < G.length; i++) {
            // 遇到出度=0的顶点，则是安全的
            if (G[i].length==0){
                queue.offer(i);
            }
            // 构建反向图
            for (int j = 0; j < G[i].length; j++) {
                graph.get(i).add(G[i][j]);
                reGraph.get(G[i][j]).add(i);
            }
        }
        while (!queue.isEmpty()){
            int safeVertex = queue.poll();
            ans.add(safeVertex);
            /**
             * 1. 删除安全结点：
             *   1⃣️ 现在已知的是安全结点，那么可以通过反向图定位到在原图中安全顶点的起始顶点，从他们的邻接表中删除这个顶点
             *   2⃣️ 如果删除安全顶点后，起始顶点的出度=0，则说明起始顶点    也是一个安全顶点
             */
            for (Integer startVertex : reGraph.get(safeVertex)) {
                graph.get(startVertex).remove(safeVertex);
                if (graph.get(startVertex).isEmpty()){
                    queue.offer(startVertex);
                }
            }
        }
        ans.sort((o1, o2) -> o1-o2);
        return ans;
    }


}
