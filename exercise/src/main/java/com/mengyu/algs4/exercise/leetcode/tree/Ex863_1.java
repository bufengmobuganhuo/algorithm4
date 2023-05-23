package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex863_1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        Ex863_1 ex863_1 = new Ex863_1();
        System.out.println(ex863_1.distanceK(root, root.left, 2));
    }

    private List<Integer> ans;

    private int k;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ans = new ArrayList<>();
        this.k = k;
        dfs(root, target);
        return ans;
    }

    private int dfs(TreeNode root, TreeNode target) {
        if (root == null) {
            return -1;
            // 已知root距离target为0，在root的左右子树中查找
        } else if (root == target) {
            find(root, 0);
            // 上一层节点距离target的距离
            return 1;
        } else {
            int left = dfs(root.left, target);
            int right = dfs(root.right, target);
            // 在root的左子树中
            if (left != -1) {
                // left表示当前节点到target的距离
                if (left == this.k) {
                    ans.add(root.val);
                }
                // 在node的右子树中查找，可知node.right距离target为left+1
                find(root.right, left + 1);
                // 上层节点距离target为left+1
                return left + 1;
                // 在root的右子树中
            } else if (right != -1) {
                if (right == this.k) {
                    ans.add(root.val);
                }
                find(root.left, right + 1);
                return right + 1;
            }
        }
        return -1;
    }

    // 已知node到target的距离为dis，在node的左右子树中查找到target的距离为k的节点
    private void find(TreeNode node, int dis) {
        if (node == null) {
            return;
        } else if (dis == k) {
            ans.add(node.val);
        } else {
            find(node.left, dis + 1);
            find(node.right, dis + 1);
        }
    }

}
