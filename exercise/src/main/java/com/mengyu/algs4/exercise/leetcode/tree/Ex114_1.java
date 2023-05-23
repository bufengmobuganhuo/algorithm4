package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yu zhang
 */
public class Ex114_1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);

        Ex114_1 ex114_1 = new Ex114_1();
        ex114_1.flatten(root);
    }
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        preorder(root);
    }

    private TreeNode preorder(TreeNode father) {
        if (father == null) {
            return null;
        }
        // 叶子结点
        if (father.left == null && father.right == null) {
            return father;
        }
        TreeNode rightChild = father.right;
        TreeNode leftChild = father.left;
        // 改成链表
        father.right = leftChild;
        father.left = null;
        TreeNode leftTail = null;
        // 如果左子节点不为空，则需要往左边继续构造链表
        if (leftChild != null) {
            leftTail = preorder(leftChild);
            leftTail.right = rightChild;
            // 如果没有，则直接取右子节点
        } else {
            father.right = rightChild;
        }
        // 向右子节点找
        TreeNode rightTail = preorder(rightChild);
        // 返回当前链表的最后一个节点：如果右子节点为空，则当前链表的最后一个节点就是leftTail
        return rightTail == null ? leftTail : rightTail;
    }
}
