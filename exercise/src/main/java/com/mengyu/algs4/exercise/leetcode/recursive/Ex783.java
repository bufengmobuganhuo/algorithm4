package com.mengyu.algs4.exercise.leetcode.recursive;

/**
 * @author yuzhang
 * @date 2020/7/9 8:57 上午
 * leetcode783
 */
public class Ex783 {
    private TreeNode prev;
    public static void main(String[] args) {

    }
    public int minDiffInBST(TreeNode root) {
        if (root==null){
            return Integer.MAX_VALUE;
        }
        int res=Integer.MAX_VALUE;
        res=minDiffInBST(root.left);
        if (prev!=null){
            res=Math.min(res,Math.abs(root.val-prev.val));
        }
        prev=root;
        return Math.min(res,minDiffInBST(root.right));
    }

    private int recursive(TreeNode prev,TreeNode cur){
        if (cur==null){
            return Integer.MAX_VALUE;
        }
        int res=recursive(cur,cur.left);
        if (prev!=null){
            res=Math.min(Math.abs(prev.val-cur.val),res);
        }
        return Math.min(res,recursive(cur,cur.right));
    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
