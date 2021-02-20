package leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/1/26 上午10:09
 * TODO
 */
public class Ex105_1 {
    private Map<Integer, Integer> value2Idx;
    private int preIdx;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        value2Idx = new HashMap<>(inorder.length * 2);
        for (int i = 0; i < inorder.length; i++) {
            value2Idx.put(inorder[i], i);
        }
        return build(preorder,inorder,0,inorder.length-1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIdx]);
        int rootIdx = value2Idx.get(root.val);
        preIdx++;
        root.left = build(preorder, inorder, start, rootIdx - 1);
        root.right = build(preorder, inorder, rootIdx + 1, end);
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
