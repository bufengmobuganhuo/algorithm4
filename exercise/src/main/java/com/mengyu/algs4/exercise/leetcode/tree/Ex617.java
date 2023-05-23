package com.mengyu.algs4.exercise.leetcode.tree;

/**
 * @author yuzhang
 * @date 2020/7/24 10:30 上午
 * TODO
 */
public class Ex617 {
    public static void main(String[] args) {
        TreeNode t1=new TreeNode(1);
        t1.left=new TreeNode(3);
        t1.right=new TreeNode(2);
        t1.left.left=new TreeNode(5);

        TreeNode t2=new TreeNode(2);
        t2.left=new TreeNode(1);
        t2.right=new TreeNode(3);
        t2.left.right=new TreeNode(4);
        t2.right.right=new TreeNode(7);

        Ex617 ex617=new Ex617();
        ex617.mergeTrees(t1,t2);
    }
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1==null&&t2==null){
            return null;
        }
        int value=t1!=null?t1.val:0;
        value+=t2!=null?t2.val:0;
        TreeNode node=new TreeNode(value);
        node.left=mergeTrees(t1!=null?t1.left:null,t2!=null?t2.left:null);
        node.right=mergeTrees(t1!=null?t1.right:null,t2!=null?t2.right:null);
        return node;
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
