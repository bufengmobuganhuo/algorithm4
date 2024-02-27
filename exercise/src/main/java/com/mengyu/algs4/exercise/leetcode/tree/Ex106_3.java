package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex106_3 {

    public static void main(String[] args) {
        new Ex106_3().buildTree(new int[]{3, 2, 1}, new int[]{3, 2, 1});
    }

    private Map<Integer, Integer> map;

    private int postIdx;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        postIdx = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(postorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] postorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postIdx--]);
        int idx = map.get(root.val);
        root.right = buildTree(postorder, idx + 1, end);
        root.left = buildTree(postorder, start, idx - 1);
        return root;
    }
}
