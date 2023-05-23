package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex116 {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
    }
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int size = que.size();
            Node last = null;
            for (int i = 0; i < size; i++) {
                Node node = que.poll();
                if (last != null) {
                    last.next = node;
                }
                if (node.left != null) {
                    que.offer(node.left);
                }
                if (node.right != null) {
                    que.offer(node.right);
                }
                last = node;
            }
        }
        return root;
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
