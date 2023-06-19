package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yu zhang
 */
public class Ex1008_1 {

    public static void main(String[] args) {
        int[] preorder = {8,5,1,7,10,12};
        new Ex1008_1().bstFromPreorder(preorder);
    }

    private int idx = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        idx = 0;
        return build(preorder, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int upper, int lower) {
        if (idx >= preorder.length) {
            return null;
        }
        int val = preorder[idx];
        if (val > upper || val < lower) {
            return null;
        }
        idx++;
        TreeNode root = new TreeNode(val);
        root.left = build(preorder, val, lower);
        root.right = build(preorder, upper, val);
        return root;
    }
}
