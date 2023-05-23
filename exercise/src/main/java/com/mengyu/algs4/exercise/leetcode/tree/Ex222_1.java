package com.mengyu.algs4.exercise.leetcode.tree;

/**
 * @author yuzhang
 * @date 2020/10/21 10:18 上午
 * TODO
 */
public class Ex222_1 {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = getDepth(root);
        if (depth==0){
            return 1;
        }
        // 给最后一层从左到右编号0,1,2,...
        int left =0,right= (int) (Math.pow(2,depth)-1);
        while (left<right){
            int midIdx = left+(right-left)/2;
            if (exists(midIdx,depth,root)){
                left=midIdx+1;
            }else{
                right=midIdx-1;
            }
        }
        return (int) (Math.pow(2,depth)-1+left);
    }

    private boolean exists(int idx,int depth,TreeNode root){
        int left=0,right= (int) (Math.pow(2,depth)-1);
        for (int i = 0; i < depth; i++) {
            int midIdx=left+(right-left)/2;
            if (idx<=midIdx){
                root=root.left;
                right=midIdx;
            }else{
                root=root.right;
                left=midIdx+1;
            }
        }
        return root!=null;
    }

    private int getDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            root = root.left;
            depth++;
        }
        return depth;
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
