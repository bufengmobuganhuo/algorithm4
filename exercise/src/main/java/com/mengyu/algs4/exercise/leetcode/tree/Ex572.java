package com.mengyu.algs4.exercise.leetcode.tree;

/**
 * @author yuzhang
 * @date 2020/7/24 8:54 上午
 * TODO
 */
public class Ex572 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);

        TreeNode root2 = new TreeNode(1);

        Ex572 ex572 = new Ex572();
        System.out.println(ex572.isSubtree(root, root2));
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        }
        return (s.val == t.val && isDetical(s, t)) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isDetical(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null) {
            return false;
        } else if (t == null) {
            return false;
        }
        if (s.val == t.val) {
            return isDetical(s.left, t.left) && isDetical(s.right, t.right);
        }
        return false;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
