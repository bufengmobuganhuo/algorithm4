package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/10/21 9:38 上午
 * TODO
 */
public class Ex652_1 {
    private int id;
    Map<String, Integer> idMap;
    Map<Integer, Integer> countMap;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        id = 0;
        idMap = new HashMap<>();
        countMap = new HashMap<>();
        ans = new ArrayList<>();
        lookup(root);
        return ans;
    }

    private int lookup(TreeNode node) {
        if (node == null) {
            return -1;
        }
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        int uid = idMap.computeIfAbsent(serial, x -> id++);
        countMap.put(uid, countMap.getOrDefault(uid, 0) + 1);
        if (countMap.get(uid) == 2) {
            ans.add(node);
        }
        return uid;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
