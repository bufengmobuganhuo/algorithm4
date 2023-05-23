package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yuzhang
 * @date 2021/11/6 10:20 上午
 * TODO
 */
public class Ex814 {
    public TreeNode pruneTree(TreeNode root) {
        return dfs(root);
    }

    private TreeNode dfs(TreeNode node) {
        if (node == null) {
            return null;
        }
        node.left = dfs(node.left);
        node.right = dfs(node.right);
        if (node.left == null && node.right == null && node.val == 0) {
            return null;
        }
        return node;
    }
}
