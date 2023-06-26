package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex589_2 {
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        preorder(root, ans);
        return ans;
    }

    private void preorder(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        for (Node child : node.children) {
            preorder(child, list);
        }
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
