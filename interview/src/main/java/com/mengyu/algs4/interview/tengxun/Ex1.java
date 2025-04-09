package com.mengyu.algs4.interview.tengxun;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @date 2025/4/2 19:21
 * TODO
 */
public class Ex1 {

    /**
     *  1
     * / \
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        inorder1(root);
    }

    private static void inorder1(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                System.out.println(stack.peek().val);
                root = stack.pop().right;
            }
        }
    }

    private static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }

    // 1, 1, 2, 3, 5, 8
    private static List<Integer> solution(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        int num1 = 0, num2 = 1;
        for (int i = 1; i < n; i++) {
            num2 = ans.get(ans.size() - 1);
            ans.add(num1 + num2);
            num1 = num2;
        }
        return ans;
    }

    private static void find(List<Integer> list, int n, int num1, int num2) {
        if (list.size() == n) {
            return;
        }
        list.add(num1 + num2);
        find(list, n, num2, list.get(list.size() - 1));
    }

    private static class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
