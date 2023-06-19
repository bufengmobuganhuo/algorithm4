package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex105_2 {

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7}, inorder = {9,3,15,20,7};
        new Ex105_2().buildTree(preorder, inorder);
    }

    private int preIdx;

    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preIdx = 0;
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1);
    }
    private TreeNode build(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIdx]);
        int rootIdx = map.get(root.val);
        preIdx++;
        root.left = build(preorder, start, rootIdx - 1);
        root.right = build(preorder, rootIdx + 1, end);
        return root;
    }
}
