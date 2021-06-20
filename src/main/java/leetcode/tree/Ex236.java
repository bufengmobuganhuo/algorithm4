package leetcode.tree;

/**
 * @author yu zhang
 */
public class Ex236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 如果找到了要找的子节点，则返回
        if (root == p || root == q) {
            return root;
        }
        // 分别向左右子节点找p,q
        TreeNode leftSon = lowestCommonAncestor(root.left, p, q);
        TreeNode rightSon = lowestCommonAncestor(root.right, p, q);
        // 如果左右子节点的返回不为空，说明root就是他们的共同祖先
        if (leftSon != null && rightSon != null) {
            return root;
            // 左边没找到，那么返回右边
        } else if (leftSon == null) {
            return rightSon;
        } else {
            return leftSon;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
