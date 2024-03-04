package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex235 {

    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode leftSon = lowestCommonAncestor3(root.left, p, q);
        TreeNode rightSon = lowestCommonAncestor3(root.right, p, q);
        if (leftSon != null && rightSon != null) {
            return root;
        } else if (leftSon == null) {
            return rightSon;
        } else {
            return leftSon;
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> list1 = new LinkedList<>();
        find(root, p, list1);
        LinkedList<TreeNode> list2 = new LinkedList<>();
        find(root, q, list2);
        TreeNode ans = null;
        while (!list1.isEmpty() && !list2.isEmpty()) {
            TreeNode node1 = list1.pollFirst();
            TreeNode node2 = list2.pollFirst();
            if (node1 != node2) {
                return ans;
            }
            ans = node1;
        }
        return ans;
    }

    private void find(TreeNode root, TreeNode target, LinkedList<TreeNode> track) {
        if (root == null) {
            return;
        }
        track.offerLast(root);
        if (root.val == target.val) {
            return;
        }
        if (root.val < target.val) {
            find(root.right, target, track);
        } else {
            find(root.left, target, track);
        }
    }
}
