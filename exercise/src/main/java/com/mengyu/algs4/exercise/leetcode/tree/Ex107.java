package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<List<Integer>> ans = new LinkedList<>();
        Queue<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int size = que.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                layer.add(node.val);
                if (node.left != null) {
                    que.offer(node.left);
                }
                if (node.right != null) {
                    que.offer(node.right);
                }
            }
            ans.offerFirst(layer);
        }
        return ans;
    }
}
