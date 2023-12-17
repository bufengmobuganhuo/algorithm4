package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex117 {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);

        System.out.println(new Ex117().connect2(root));
    }

    private Node nextStart = null;

    private Node pre = null;

    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        while (start != null) {
            nextStart = null;
            pre = null;
            for (Node node = start; node != null; node = node.next) {
                if (node.left != null) {
                    handle(node.left);
                }
                if (node.right != null) {
                    handle(node.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    private void handle(Node node) {
        if (pre != null) {
            pre.next = node;
        }
        if (nextStart == null) {
            nextStart = node;
        }
        pre = node;
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int size = que.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node node = que.poll();
                if (pre != null) {
                    pre.next = node;
                }
                pre = node;
                if (node.left != null) {
                    que.offer(node.left);
                }
                if (node.right != null) {
                    que.offer(node.right);
                }
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
    };
}
