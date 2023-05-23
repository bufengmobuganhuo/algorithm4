package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/8/11 9:17 上午
 * TODO
 */
public class Ex133 {
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        Ex133 ex133=new Ex133();
        Node root = ex133.cloneGraph(node1);
        System.out.println(root.val);
    }
    // 旧结点 -> 被克隆结点
    private Map<Node,Node> markedMap=new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node==null){
            return null;
        }
        // 如果已经被访问过，说明已经被克隆，直接返回被克隆的结点
        if (markedMap.containsKey(node)){
            return markedMap.get(node);
        }
        Node clonedNode=new Node(node.val,new ArrayList<>());
        markedMap.put(node,clonedNode);
        for (Node adjNode : node.neighbors) {
            clonedNode.neighbors.add(cloneGraph(adjNode));
        }
        return clonedNode;
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
