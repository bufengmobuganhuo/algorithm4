package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex652_4 {

    public static void main(String[] args) {

    }

    private Map<String, Integer> idMap;

    private Map<Integer, Integer> countMap;

    private List<TreeNode> ans;

    private int id;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        idMap = new HashMap<>();
        countMap = new HashMap<>();
        ans = new ArrayList<>();
        id = 0;
        find(root);
        return ans;
    }

    private int find(TreeNode node) {
        if (node == null) {
            return -1;
        }
        String serial = node.val + "," + find(node.left) + "," + find(node.right);
        int serialId = idMap.computeIfAbsent(serial, key -> id++);
        int count = countMap.getOrDefault(serialId, 0);
        countMap.put(serialId, count + 1);
        if (count == 1) {
            ans.add(node);
        }

        return serialId;
    }
}
