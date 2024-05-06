package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2385 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.left.left.left.right = new TreeNode(5);
        System.out.println(new Ex2385().amountOfTime(root, 3));
    }

    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, LinkedList<Integer>> adj = new HashMap<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            TreeNode node = que.poll();
            if (node.left != null) {
                adj.compute(node.val, (key, val) -> {
                    if (val == null) {
                        val = new LinkedList<>();
                    }
                    val.offer(node.left.val);
                    return val;
                });
                adj.compute(node.left.val, (key, val) -> {
                    if (val == null) {
                        val = new LinkedList<>();
                    }
                    val.offer(node.val);
                    return val;
                });
                que.offer(node.left);
            }
            if (node.right != null) {
                adj.compute(node.val, (key, val) -> {
                    if (val == null) {
                        val = new LinkedList<>();
                    }
                    val.offer(node.right.val);
                    return val;
                });
                adj.compute(node.right.val, (key, val) -> {
                    if (val == null) {
                        val = new LinkedList<>();
                    }
                    val.offer(node.val);
                    return val;
                });
                que.offer(node.right);
            }
        }
        Set<Integer> marked = new HashSet<>();
        marked.add(start);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                int vertex = queue.poll();
                LinkedList<Integer> adjList = adj.get(vertex);
                if (adjList == null) {
                    continue;
                }
                for (int adjVertex : adjList) {
                    if (!marked.contains(adjVertex)) {
                        marked.add(adjVertex);
                        queue.offer(adjVertex);
                        flag = true;
                    }
                }
            }
            ans = flag ? ans + 1 : ans;
        }
        return ans;
    }
}
