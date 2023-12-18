package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex2415 {


    public TreeNode reverseOddLevels2(TreeNode root) {
        dfs(root.left, root.right, true);
        return root;
    }

    private void dfs(TreeNode root1, TreeNode root2, boolean isOdd) {
        if (root1 == null || root2 == null) {
            return;
        }
        if (isOdd) {
            int tmp = root1.val;
            root1.val = root2.val;
            root2.val = tmp;
        }
        dfs(root1.left, root2.right, !isOdd);
        dfs(root1.right, root2.left, !isOdd);
    }

    public TreeNode reverseOddLevels(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offerFirst(root);
        int layer = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<TreeNode> list = null;
            if (layer % 2 == 1) {
                list = new ArrayList<>();
            }
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                if (node.left != null) {
                    deque.offerLast(node.left);
                }
                if (node.right != null) {
                    deque.offerLast(node.right);
                }
                if (layer % 2 == 1) {
                    list.add(node);
                }
            }
            if (list != null) {
                for (int i = 0; i < list.size() / 2; i++) {
                    TreeNode left = list.get(i);
                    TreeNode right = list.get(list.size() - i - 1);
                    int tmp = left.val;
                    left.val = right.val;
                    right.val = tmp;
                }
            }
            layer++;
        }
        return root;
    }
}
