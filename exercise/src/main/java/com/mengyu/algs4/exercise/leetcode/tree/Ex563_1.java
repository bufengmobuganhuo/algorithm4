package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yuzhang
 * @date 2021/11/28 9:44 上午
 * TODO
 */
public class Ex563_1 {
    private int delta;
    public int findTilt(TreeNode root) {
        if (root==null){
            return 0;
        }
        sum(root);
        return delta;
    }

    private int sum(TreeNode root){
        if (root==null){
            return 0;
        }
        int leftSum=sum(root.left);
        int rightSum=sum(root.right);
        delta+=Math.abs(leftSum-rightSum);
        return leftSum+rightSum+root.val;
    }
}
