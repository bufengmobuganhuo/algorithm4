package com.mengyu.algs4.exercise.leetcode.tree;

/**
 * @author yuzhang
 * @date 2020/7/22 11:35 上午
 * TODO
 */
public class Ex450 {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(10);
        root.left=new TreeNode(8);
        root.left.left=new TreeNode(7);
        root.left.right=new TreeNode(9);
        root.left.left.left=new TreeNode(6);
        root.right=new TreeNode(12);
        root.right.left=new TreeNode(11);
        root.right.right=new TreeNode(15);
        root.right.right.left=new TreeNode(13);
        root.right.right.right=new TreeNode(17);
        Ex450 ex450=new Ex450();
        ex450.deleteNode(root,12);
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root==null){
            return null;
        }
        if (root.val>key){
            root.left= deleteNode(root.left,key);
        }else if (root.val<key){
            root.right=deleteNode(root.right,key);
        }else{
            // root只有单个子结点的情况
            if (root.left==null){
                return root.right;
            }
            if (root.right==null){
                return root.left;
            }
            // 找到后继结点
            TreeNode susNode=minRecursive(root.right);
            root.right=deleteMinRecursive(root.right);
            root.val=susNode.val;
        }
        return root;
    }

    private TreeNode deleteMinRecursive(TreeNode node){
        if (node.left==null){
            return node.right;
        }
        node.left = deleteMinRecursive(node.left);
        return node;
    }

    private TreeNode minRecursive(TreeNode node){
        if (node.left==null){
            return node;
        }
        return minRecursive(node.left);
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
