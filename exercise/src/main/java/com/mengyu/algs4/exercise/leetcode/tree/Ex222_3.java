package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yu zhang
 */
public class Ex222_3 {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return count(root, 1);
    }

    private int count(TreeNode node, int count) {
        if (node.left == null && node.right == null) {
            return count;
        }
        int right = 0, left = 0;
        if (node.right != null) {
            right = count(node.right, 2 * count + 1);
        }
        left = count(node.left, 2 * count);
        return Math.max(left, right);
    }
}
