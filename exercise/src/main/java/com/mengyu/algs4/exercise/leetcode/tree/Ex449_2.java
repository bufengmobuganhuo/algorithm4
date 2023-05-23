package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex449_2 {
    private int[] preorder;
    private int idx;
    private Map<Integer, Integer> map;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.val).append("-");
        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] nodes = data.split("-");
        int[] preorder = new int[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            preorder[i] = Integer.parseInt(nodes[i]);
        }
        this.preorder = preorder;
        this.idx = 0;
        return build(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode build(int lower, int upper) {
        if (idx >= preorder.length) {
            return null;
        }
        int val = preorder[idx];
        if (val < lower || val > upper) {
            return null;
        }
        TreeNode root = new TreeNode(val);
        idx++;
        root.left = build(lower, val);
        root.right = build(val, upper);
        return root;
    }

    private TreeNode build(int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        int inorderIdx = map.get(preorder[preLeft]);
        root.left = build(preLeft + 1, preLeft + inorderIdx - inLeft, inLeft, inorderIdx - 1);
        root.right = build(preLeft + inorderIdx - inLeft + 1, preRight, inorderIdx + 1, inRight);
        return root;
    }
}
