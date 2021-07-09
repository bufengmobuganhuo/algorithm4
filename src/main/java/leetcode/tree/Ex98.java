package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex98 {

    public boolean isValidBST(TreeNode root) {
        return inorder(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 中序遍历，每次都更新当前节点的上下界
     */
    private boolean inorder(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return inorder(node.left, lower, node.val) && inorder(node.right, node.val, upper);
    }

    public class TreeNode {
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
