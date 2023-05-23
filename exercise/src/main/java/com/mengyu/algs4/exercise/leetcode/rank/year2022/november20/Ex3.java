package com.mengyu.algs4.exercise.leetcode.rank.year2022.november20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2022/11/20 11:35
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[][] roads = {{3, 1}, {3, 2}, {1, 0}, {0, 4}, {0, 5}, {4, 6}, {3, 7}, {7, 9}, {7, 8}, {8, 11}, {9, 10}};
        System.out.println(new Ex3().minimumFuelCost(roads, 5));
    }

    /**
     * 存储node 编号 --> node的映射关系，保证每个城市（节点）只有一个对象
     */
    private Map<Integer, Node> map;

    private boolean[] market;

    public long minimumFuelCost(int[][] roads, int seats) {
        map = new HashMap<>();
        market = new boolean[roads.length + 1];
        // 构建无向图
        List<Node>[] adj = adj(roads);
        // 从首都开始，DFS
        Node calculated = dfs(getOrCreate(0), adj, seats);
        return calculated.cost;
    }

    private List<Node>[] adj(int[][] roads) {
        int n = roads.length + 1;
        List<Node>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            Node thisNode = getOrCreate(road[0]);
            Node otherNode = getOrCreate(road[1]);
            adj[thisNode.number].add(otherNode);
            adj[otherNode.number].add(thisNode);
        }
        return adj;
    }

    private Node dfs(Node node, List<Node>[] adj, int seats) {
        market[node.number] = true;
        for (Node adjNode : adj[node.number]) {
            if (market[adjNode.number]) {
                continue;
            }
            // 当前Node的上一个节点产生的消耗和总人数
            Node calculatedNode = dfs(adjNode, adj, seats);
            // 从相邻节点到达当前node时，人数增加
            node.human += calculatedNode.human;
            // 如果相邻节点的人数不超过座位数，则可以一次性到达
            if (calculatedNode.human <= seats) {
                node.cost += calculatedNode.cost + 1;
            } else {
                // 否则，需要分多次到达
                node.cost += (Math.ceil((double) calculatedNode.human / seats)) + calculatedNode.cost;
            }
        }
        return node;
    }

    private Node getOrCreate(int number) {
        return map.computeIfAbsent(number, key -> new Node(number, 1, 0));
    }

    private static class Node {
        // 当前Node的编号
        private int number;
        // 到达当前Node时的总人数（包含当前Node）
        private long human;
        // 到达当前Node时的消耗
        private long cost;

        public Node(int number, long human, long cost) {
            this.number = number;
            this.human = human;
            this.cost = cost;
        }
    }
}
