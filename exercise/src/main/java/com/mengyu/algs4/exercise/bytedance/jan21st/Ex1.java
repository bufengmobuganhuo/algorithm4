package com.mengyu.algs4.exercise.bytedance.jan21st;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/1/21 上午8:45
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.addAll(Arrays.asList(node2,node4));
        node2.neighbors.addAll(Arrays.asList(node1,node3));
        node3.neighbors.addAll(Arrays.asList(node2,node4));
        node4.neighbors.addAll(Arrays.asList(node1,node3));
        Ex1 ex1 = new Ex1();
        ex1.cloneGraph(node1);
    }
    private Map<Node, Node> old2New;
    private Set<Node> visited;

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        old2New = new HashMap<>();
        visited = new HashSet<>();
        dfs(node);
        return old2New.get(node);
    }

    private void dfs(Node node) {
        visited.add(node);
        Node newNode = cloneNode(node);
        for (Node oldAdj : node.neighbors) {
            newNode.neighbors.add(cloneNode(oldAdj));
            if (!visited.contains(oldAdj)) {
                dfs(oldAdj);
            }
        }
    }

    private Node cloneNode(Node old) {
        if (old2New.containsKey(old)) {
            return old2New.get(old);
        }
        Node newNode = new Node(old.val);
        old2New.put(old, newNode);
        return newNode;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
