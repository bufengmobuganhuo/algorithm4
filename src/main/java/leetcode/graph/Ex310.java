package leetcode.graph;

import com.sun.tools.corba.se.idl.constExpr.UnaryExpr;
import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/8/12 10:47 上午
 * TODO
 */
public class Ex310 {
    public static void main(String[] args) {
        int[][] edges =
            {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        Ex310 ex310 = new Ex310();
        System.out.println(Arrays.toString(ex310.findMinHeightTrees(6, edges).toArray()));
    }

    /**
     * 1. 在层级遍历的过程中每次都去除掉叶子节点（度数=1的节点），最后留下的就是根
     * 2. 对于一个叶子节点A，一个内部节点（非叶子节点）C，另一个叶子节点B：
     *  若选择A作为这个树的根，那么他到达B的路径长度一定>=max(C -> B，C -> A)的长度，因为他只能通过A -> C -> B到达
     *  所以应该是以内部节点作为根
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList();
        if (n == 1) {
            res.add(0);
            return res;
        }
        Graph graph = buildDigraph(n, edges);
        // 队列中存储度数=1的结点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.vertexNum; i++) {
            if (graph.degree[i] == 1) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            // 在每次循环时都创建一个list，最后留下的list中的数据就是在一层一层丢弃叶子结点后，剩下的顶点
            res = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // curVertex就是当前度数=1的叶子结点
                int curVertex = queue.poll();
                res.add(curVertex);
                for (int adjVertex : graph.adj(curVertex)) {
                    // 丢弃curVertex，那么adjVertex的度数-1
                    graph.degree[adjVertex]--;
                    // 如果经过丢弃当前叶子结点，导致adjVertex的度数=1，则放入队列
                    if (graph.degree[adjVertex] == 1) {
                        queue.offer(adjVertex);
                    }
                }
            }
        }
        return res;
    }

    private Graph buildDigraph(int n, int[][] edges) {
        Graph graph = new Graph(n);
        for (int i = 0; i < edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1]);
        }
        return graph;
    }

    static class Graph {
        private LinkedList<Integer>[] adj;
        private int vertexNum;
        private int[] degree;

        public Graph(int vertexNum) {
            this.vertexNum = vertexNum;
            adj = new LinkedList[vertexNum];
            for (int i = 0; i < vertexNum; i++) {
                adj[i] = new LinkedList<>();
            }
            degree = new int[vertexNum];
        }

        public void addEdge(int vertex1, int vertex2) {
            adj[vertex1].offer(vertex2);
            adj[vertex2].offer(vertex1);
            degree[vertex1]++;
            degree[vertex2]++;
        }

        public Iterable<Integer> adj(int vertex) {
            return adj[vertex];
        }
    }
}
