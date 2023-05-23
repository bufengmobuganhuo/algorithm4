package com.mengyu.algs4.exercise.leetcode.rank.year2022.may8;

import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yuzhang
 * @date 2022/5/8 10:11
 * TODO
 */
public class Ex2 {
    private int count;
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return count;
    }

    private NodeInfo dfs(TreeNode node) {
        if (node == null) {
            return new NodeInfo(0, 0);
        }
        NodeInfo left = dfs(node.left);
        NodeInfo right = dfs(node.right);
        if ((left.sum + right.sum + node.val) / (left.count + right.count + 1) == node.val) {
            count++;
        }
        return new NodeInfo(left.count + right.count + 1, left.sum + right.sum + node.val);
    }

    private static class NodeInfo {
        int count;
        int sum;

        public NodeInfo(int count, int sum) {
            this.count = count;
            this.sum = sum;
        }
    }
}
