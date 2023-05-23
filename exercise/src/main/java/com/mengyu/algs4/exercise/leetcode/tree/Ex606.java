package com.mengyu.algs4.exercise.leetcode.tree;

/**
 * @author yuzhang
 * @date 2020/7/24 9:47 上午
 * TODO
 */
public class Ex606 {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        Ex606 ex606=new Ex606();
        System.out.println(ex606.tree2str(root));
    }
    public String tree2str(TreeNode t) {
        StringBuilder stringBuilder=new StringBuilder();
        preorder(t,stringBuilder);
        return stringBuilder.toString();
    }

    private void preorder(TreeNode node,StringBuilder stringBuilder){
        if (node==null){
           return;
        }
        stringBuilder.append(node.val);
        if (!(node.left==null&&node.right==null)){
            stringBuilder.append("(");
        }
        preorder(node.left,stringBuilder);
        if (!(node.left==null&&node.right==null)){
            stringBuilder.append(")");
        }
        if (node.right!=null){
            stringBuilder.append("(");
        }
        preorder(node.right,stringBuilder);
        if (node.right!=null){
            stringBuilder.append(")");
        }
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
