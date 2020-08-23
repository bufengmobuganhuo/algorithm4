package leetcode.graph;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/8/13 9:08 上午
 * TODO
 */
public class Ex332 {
    public static void main(String[] args) {
        List<List<String>> tickets=new ArrayList<>();
        tickets.add(Arrays.asList("MUC","LHR"));
        tickets.add(Arrays.asList("JFK","MUC"));
        tickets.add(Arrays.asList("SFO","SJC"));
        tickets.add(Arrays.asList("LHR","SFO"));

        Ex332 ex332=new Ex332();
        System.out.println(Arrays.toString(ex332.findItinerary(tickets).toArray()));
    }
    public List<String> findItinerary(List<List<String>> tickets) {
        Digraph digraph=buildDigraph(tickets);
        String nonIsolateVertex="JFK";
        List<String> res=new LinkedList<>();
        Stack<String> stack=new Stack<>();
        stack.push(nonIsolateVertex);
        while (!stack.isEmpty()){
            String vertex=stack.pop();
            while (digraph.adj.get(vertex)!=null&&!digraph.adj.get(vertex).isEmpty()){
                stack.push(vertex);
                vertex=digraph.adj.get(vertex).poll();
            }
            res.add(0,vertex);
        }
        return res;
    }

    private Digraph buildDigraph(List<List<String>> tickets){
        Digraph digraph=new Digraph();
        for (List<String> ticket : tickets) {
            digraph.addEdge(ticket.get(0),ticket.get(1));
        }
        return digraph;
    }

    static class Digraph{
        // 使用优先队列存储邻接表，保证每次取到的都是字典序最小值
        Map<String,PriorityQueue<String>> adj;
        Map<String,Integer> outDegree;

        public Digraph() {
            adj=new HashMap<>();
            outDegree =new HashMap<>();
        }

        public void addEdge(String vertex1,String vertex2){
            PriorityQueue<String> adjVertexes=adj.getOrDefault(vertex1,new PriorityQueue<>());
            adjVertexes.add(vertex2);
            adj.put(vertex1,adjVertexes);
            outDegree.put(vertex1,outDegree.getOrDefault(vertex1,0)+1);
        }
    }
}
