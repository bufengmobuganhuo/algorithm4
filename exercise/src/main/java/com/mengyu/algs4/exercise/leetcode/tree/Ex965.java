package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2020/11/9 8:53 上午
 * TODO
 */
public class Ex965 {
    private Set<Integer> set;
    public boolean isUnivalTree(TreeNode root) {
        set = new HashSet<>();
        recursive(root);
        return set.size() == 1;
    }

    private void recursive(TreeNode root){
        if (root == null){
            return;
        }
        set.add(root.val);
        recursive(root.left);
        recursive(root.right);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
