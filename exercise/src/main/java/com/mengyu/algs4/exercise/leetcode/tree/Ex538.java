package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/7/23 8:58 上午
 * TODO
 */
public class Ex538 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.left.left.left = new TreeNode(3);

        root.right = new TreeNode(15);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(17);
        root.right.left.left = new TreeNode(11);
        root.right.left.right = new TreeNode(14);
        root.right.right.left = new TreeNode(16);

        Ex538 ex538 = new Ex538();

    }

    private int sum;

    /**
     * 将树按照树中结点值的降序遍历，并记录累加的值：反中序遍历
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    /**
     * 非递归方式：使用栈实现
     *
     * @param root
     * @return
     */
    public TreeNode convertBST2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        int sum = 0;
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
            node=stack.pop();
            sum+=node.val;
            node.val=sum;

            node=node.left;
        }
        return root;
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
