package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yuzhang
 * @date 2021/11/28 9:48 上午
 * TODO
 */
public class Ex897 {
    private TreeNode pre;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        pre = dummy;
        inorder(root);
        return dummy.right;
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        pre.right = node;
        pre.left = null;
        pre = pre.right;
        node.left = null;
        inorder(node.right);
    }
}
