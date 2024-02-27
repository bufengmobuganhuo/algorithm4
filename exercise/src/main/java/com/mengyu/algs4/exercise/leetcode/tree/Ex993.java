package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yu zhang
 */
public class Ex993 {

    private int depth1;

    private int depth2;

    private TreeNode father1;

    private TreeNode father2;

    public boolean isCousins(TreeNode root, int x, int y) {
        depth1 = -1;
        depth2 = -1;
        father1 = null;
        father2 = null;
        dfs(root, null, x, y, 0);
        return depth1 != -1 && depth2 != -1 && depth1 == depth2 && father1 != father2;
    }

    private void dfs(TreeNode node, TreeNode father, int x, int y, int depth) {
        if (node == null) {
            return;
        }
        if (node.val == x) {
            depth1 = depth;
            father1 = father;
        } else if (node.val == y) {
            depth2 = depth;
            father2 = father;
        }
        if (depth1 != -1 && depth2 != -1) {
            return;
        }
        dfs(node.left, node, x, y, depth + 1);
        dfs(node.right, node, x, y, depth + 1);
    }
}
