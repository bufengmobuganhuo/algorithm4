package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/10/28 上午10:42
 * TODO
 */
public class Ex652_3 {
    private Map<String, Integer> idMap;

    private Map<Integer, Integer> countMap;

    private int id;

    private List<TreeNode> res;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        idMap = new HashMap<>();
        countMap = new HashMap<>();
        id = 0;
        res = new ArrayList<>();
        lookup(root);
        return res;
    }

    private int lookup(TreeNode node) {
        if (node == null) {
            return -1;
        }
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        int serialId = idMap.computeIfAbsent(serial, key -> id++);
        int count = countMap.getOrDefault(serialId, 0);
        countMap.put(serialId, count + 1);
        if (count == 1) {
            res.add(node);
        }
        return serialId;
    }


}
