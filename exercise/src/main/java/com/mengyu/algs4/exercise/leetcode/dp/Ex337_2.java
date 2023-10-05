package com.mengyu.algs4.exercise.leetcode.dp;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @date 2023/10/4 11:54
 * TODO
 */
public class Ex337_2 {

    public int rob(TreeNode root) {
        int[] dp = robRecursive(root);
        return Math.max(dp[0], dp[1]);
    }

    private int[] robRecursive(TreeNode node) {
        if (node == null) {
            return new int[2];
        }
        int[] left = robRecursive(node.left);
        int[] right = robRecursive(node.right);
        int[] dp = new int[2];
        dp[0] = node.val + left[1] + right[1];
        dp[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return dp;
    }
}
