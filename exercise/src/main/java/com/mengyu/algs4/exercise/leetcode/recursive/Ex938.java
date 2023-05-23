package com.mengyu.algs4.exercise.leetcode.recursive;

/**
 * @author yuzhang
 * @date 2020/7/8 2:20 下午
 * TODO
 */
public class Ex938 {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root==null){
            return 0;
        }
        int sum=0;
        // root.val>L,说明左子树有满足条件的
        if (root.val>L){
            sum+=rangeSumBST(root.left,L,R);
        }
        // root.val<R,说明右子树中又满足条件的
        if (root.val<R){
            sum+=rangeSumBST(root.right,L,R);
        }
        if (root.val>=L&&root.val<=R){
            sum+=root.val;
        }
        return sum;
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
