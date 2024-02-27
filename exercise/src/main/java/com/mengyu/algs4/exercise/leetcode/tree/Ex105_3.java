package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex105_3 {

    private int preIdx;

    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preIdx = 0;
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIdx++]);
        int idx = map.get(root.val);
        root.left = build(preorder, start, idx - 1);
        root.right = build(preorder, idx + 1, end);
        return root;
    }
}
