package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex590_1 {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        postorder(root, ans);
        return ans;
    }

    private void postorder(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        for (Node child : node.children) {
            postorder(child, list);
        }
        list.add(node.val);
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
