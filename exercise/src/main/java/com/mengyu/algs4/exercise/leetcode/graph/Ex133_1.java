package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/4/7 上午10:13
 * TODO
 */
public class Ex133_1 {
    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node.neighbors.add(node2);
        node.neighbors.add(node4);
        node2.neighbors.add(node);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node);
        node4.neighbors.add(node3);

        Ex133_1 ex1331 = new Ex133_1();
        ex1331.cloneGraph(node);
    }

    private Map<Integer, Node> map;

    public Node cloneGraph(Node node) {
        map = new HashMap<>();
        return dfs(node);
    }

    private Node dfs(Node node) {
        Node newNode = newNode(node);
        for (Node adjNode : node.neighbors) {
            if (!map.containsKey(adjNode.val)) {
                dfs(adjNode);
            }
            newNode.neighbors.add(newNode(adjNode));
        }
        return newNode;
    }

    private Node newNode(Node node) {
        if (map.containsKey(node.val)) {
            return map.get(node.val);
        } else {
            Node newNode = new Node(node.val);
            map.put(node.val, newNode);
            return newNode;
        }
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
