package com.mengyu.algs4.exercise.interview.tree;

import com.mengyu.algs4.exercise.offer.tree.TreeNode;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex0406 {

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        // 如果p的右子树不为空，则说明后继节点为右子树的最小节点
        if (p.right != null) {
            successor = p.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }
        // 如果p的右子树为空，则说明后继节点是p的祖先节点
        TreeNode node = root;
        while (node != null) {
            // 说明后继节点可以是node
            if (node.val > p.val) {
                successor = node;
                // 向左子树找，看能否让successor更小
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return successor;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null, cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (prev == p) {
                return cur;
            }
            prev = cur;
            cur = cur.right;
        }
        return null;
    }
}
