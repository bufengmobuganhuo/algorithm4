package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex106_2 {

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7}, postorder = {9,15,7,20,3};
        new Ex106_2().buildTree(inorder, postorder);
    }

    private int postIdx;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIdx = postorder.length - 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(postorder, map, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] postorder, Map<Integer, Integer> map, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postIdx]);
        int rootIdx = map.get(root.val);
        postIdx--;
        root.right = buildTree(postorder, map, rootIdx + 1, end);
        root.left = buildTree(postorder, map, start, rootIdx - 1);
        return root;
    }
}
