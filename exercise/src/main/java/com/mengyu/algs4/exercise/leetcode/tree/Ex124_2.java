package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @date 2023/10/4 15:48
 * TODO
 */
public class Ex124_2 {

    private int ans;

    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        visit(root);
        return ans;
    }

    private int visit(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(visit(node.left), 0);
        int right = Math.max(visit(node.right), 0);
        ans = Math.max(ans, node.val + left + right);
        return node.val + Math.max(left, right);
    }
}
