package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yu zhang
 */
public class Ex671 {
    private long ans = Long.MAX_VALUE;

    public int findSecondMinimumValue(TreeNode root) {
        int min = root.val;
        dfs(root, min);
        return ans == Long.MAX_VALUE ? -1 : (int) ans;
    }

    private void dfs(TreeNode root, int min) {
        if (root == null) {
            return;
        }
        if (root.val > min && root.val < ans) {
            ans = root.val;
        }
        dfs(root.left, min);
        dfs(root.right, min);
    }
}
