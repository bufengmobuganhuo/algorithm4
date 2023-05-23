package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_2_BinarySearchTrees.exercises;

import com.mengyu.algs4.utils.TreeNode;

/**
 * @author yuzhang
 * @date 2020/8/6 10:05 上午
 * 求二叉树的最近共同祖先
 */
public class Ex_lca {
    public static void main(String[] args) {
        TreeNode<Integer,Integer> root=new TreeNode<>(0,0);
        root.left=new TreeNode<>(1,1);
        root.right=new TreeNode<>(2,2);
        root.left.left=new TreeNode<>(3,3);
        root.left.right=new TreeNode<>(4,4);
        root.right.left=new TreeNode<>(5,5);
        root.right.right=new TreeNode<>(6,6);
        root.left.left.left=new TreeNode<>(7,7);
        root.right.left.right=new TreeNode<>(8,8);
        Ex_lca lca=new Ex_lca();
        System.out.println(lca.lca(root,7,4).key);
    }
    public TreeNode<Integer, Integer> lca(TreeNode<Integer, Integer> root, int node1, int node2) {
        if (root == null) {
            return null;
        }
        // 如果找到了则返回对应结点
        if (root.key == node1 || root.key == node2) {
            return root;
        }
        TreeNode<Integer, Integer> left = lca(root.left, node1, node2);
        TreeNode<Integer, Integer> right = lca(root.right, node1, node2);
        // 如果都找到了，则说明root是这两个结点的最近共同祖先
        if (left != null && right != null) {
            return root;
        } else if (left == null) {
            return right;
        } else {
            return left;
        }
    }
}
