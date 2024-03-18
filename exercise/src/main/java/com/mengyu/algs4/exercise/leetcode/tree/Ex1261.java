package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex1261 {
    private final TreeNode root;

    public Ex1261(TreeNode root) {
        this.root = root;
        this.root.val = 0;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(this.root);
        while (!que.isEmpty()) {
            TreeNode node = que.poll();
            if (node.left != null) {
                node.left.val = node.val * 2 + 1;
                que.offer(node.left);
            }
            if (node.right != null) {
                node.right.val = node.val * 2 + 2;
                que.offer(node.right);
            }
        }
    }

    public boolean find(int target) {
        TreeNode node = find(this.root, target);
        return node != null;
    }

    private TreeNode find(TreeNode node, int target) {
        if (node == null) {
            return null;
        } else if (node.val == target) {
            return node;
        }
        int left = node.val * 2 + 1;
        int right = node.val * 2 + 2;
        TreeNode leftNode = null, rightNode = null;
        if (target >= left) {
            leftNode = find(node.left, target);
        }
        if (leftNode != null) {
            return leftNode;
        }
        if (target >= right) {
            rightNode = find(node.right, target);
        }
        return rightNode;
    }
}
