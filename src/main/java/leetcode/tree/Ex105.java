package leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/7/20 11:29 上午
 * TODO
 */
public class Ex105 {
    private int preIdx;
    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preIdx = 0;
        // 中序遍历中: 元素 -> 索引
        map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        // 前序遍历中：[根结点，左子树，右子树]
        TreeNode root = new TreeNode(preorder[preIdx]);
        int rootIdx = map.get(root.val);
        preIdx++;
        root.left = buildTree(preorder, start, rootIdx - 1);
        root.right = buildTree(preorder, rootIdx + 1, end);
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
