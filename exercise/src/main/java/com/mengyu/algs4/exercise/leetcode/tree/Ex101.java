package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex101 {

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root.left);
        que.offer(root.right);
        while (!que.isEmpty()) {
            TreeNode node1 = que.poll();
            TreeNode node2 = que.poll();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null) {
                return false;
            }
            if (node1.val != node2.val){
                return false;
            }
            que.offer(node1.left);
            que.offer(node2.right);

            que.offer(node1.right);
            que.offer(node2.left);
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root.left, root.right);
    }

    private boolean check(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return check(node1.left, node2.right) && check(node1.right, node2.left);
    }
}
