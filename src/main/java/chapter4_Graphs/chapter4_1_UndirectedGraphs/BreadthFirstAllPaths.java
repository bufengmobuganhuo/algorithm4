package chapter4_Graphs.chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/8/25 8:48 上午
 * 广度优先搜索，获取到所有最短路径
 */
public class BreadthFirstAllPaths {
    public static void main(String[] args) {
        String path = "/Volumes/F/Algorithm4/src/main/resources/tinyG.txt";
        Digraph graph = new Digraph(new In(path));
        BreadthFirstAllPaths bfs = new BreadthFirstAllPaths(graph, 0);
        bfs.pathsTo(7);
    }

    private int[] distTo;
    /**
     * edgeTo[i]：所有到达顶点i的最短路径
     */
    private List<List<Integer>>[] edgeTo;

    public BreadthFirstAllPaths(Digraph graph, int start) {
        distTo = new int[graph.getVertexNum()];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        edgeTo = new List[graph.getVertexNum()];
        for (int i = 0; i < graph.getVertexNum(); i++) {
            edgeTo[i] = new ArrayList<>();
        }
        distTo[start] = 0;
        bfs(graph, start);
    }

    private void bfs(Digraph graph, int start) {
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> beginList = new ArrayList<>();
        beginList.add(start);
        queue.offer(beginList);
        while (!queue.isEmpty()) {
            // 当前正在访问到的路径
            List<Integer> nowPath = queue.poll();
            // 当前路径的最后一个顶点
            int lastVertex = nowPath.get(nowPath.size() - 1);
            for (int adjVertex : graph.adj(lastVertex)) {
                // 如果到达adjVertex的路径比之前的短，则放入队列
                if (distTo[lastVertex] + 1 <= distTo[adjVertex]) {
                    List<Integer> tmp = new ArrayList<>(nowPath);
                    tmp.add(adjVertex);
                    queue.offer(tmp);
                    edgeTo[adjVertex].add(tmp);
                    distTo[adjVertex] = distTo[lastVertex] + 1;
                }
            }
        }
    }

    private List<List<Integer>> pathsTo(int target) {
        return edgeTo[target];
    }
}
