package com.mengyu.algs4.exercise.leetcode.tree;


/**
 * @author yuzhang
 * @date 2020/10/13 8:57 上午
 * TODO
 */
public class Ex687 {
    private int max;

    public int longestUnivaluePath(Ex103.TreeNode root) {
        if (root == null) {
            return max;
        }
        recursive(root);
        return max;
    }

    private int recursive(Ex103.TreeNode root) {
        int left = root.left == null ? 0 : recursive(root.left);
        int right = root.right == null ? 0 : recursive(root.right);

        // 看左右子节点是否与跟节点相同
        int resLeft = root.left != null && root.left.val == root.val ? left + 1 : left;
        int resRight = root.right != null && root.right.val == root.val ? right + 1 : right;

        max = Math.max(max, resLeft + resRight);
        return Math.max(resLeft, resRight);
    }
}
