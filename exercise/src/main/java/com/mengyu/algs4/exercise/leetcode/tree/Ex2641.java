package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex2641 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(10);
        root.right.right = new TreeNode(7);
        new Ex2641().replaceValueInTree(root);
    }

    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int sum = 0;
            Queue<TreeNode> que2 = new ArrayDeque<>();
            for (TreeNode node : que) {
                if (node.left != null) {
                    sum += node.left.val;
                    que2.offer(node.left);
                }
                if (node.right != null) {
                    sum += node.right.val;
                    que2.offer(node.right);
                }
            }
            for (TreeNode node : que) {
                int childrenSum = (node.left == null ? 0 : node.left.val) + (node.right == null ? 0 : node.right.val);
                if (node.left != null) {
                    node.left.val = sum - childrenSum;
                }
                if (node.right != null) {
                    node.right.val = sum - childrenSum;
                }
            }
            que = que2;
        }
        root.val = 0;
        return root;
    }
}
