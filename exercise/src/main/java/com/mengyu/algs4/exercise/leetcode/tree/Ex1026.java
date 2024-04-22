package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yu zhang
 */
public class Ex1026 {

    private int ans;

    public int maxAncestorDiff(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};
        }
        int tmpLeft = 0;
        int[] left = dfs(node.left);
        if (left[0] != Integer.MIN_VALUE) {
            tmpLeft = Math.max(Math.abs(node.val - left[0]), Math.abs(node.val - left[1]));
        }
        int tmpRight = 0;
        int[] right = dfs(node.right);
        if (right[0] != Integer.MIN_VALUE) {
            tmpRight = Math.max(Math.abs(node.val - right[0]), Math.abs(node.val - right[1]));
        }
        ans = Math.max(ans, Math.max(tmpLeft, tmpRight));
        int max = Math.max(node.val, Math.max(left[0], right[0]));
        int min = Math.min(node.val, Math.min(left[1], right[1]));
        return new int[]{max, min};
    }
}
