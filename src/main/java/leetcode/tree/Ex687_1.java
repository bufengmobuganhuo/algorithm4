package leetcode.tree;

/**
 * @author yu zhang
 */
public class Ex687_1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(5);
        Ex687_1 ex687_1 = new Ex687_1();
        System.out.println(ex687_1.longestUnivaluePath(root));
    }
    private int ans = Integer.MIN_VALUE;

    public int longestUnivaluePath(TreeNode root) {
        int res = maxPath(root);
        return Math.max(res, ans);
    }

    private int maxPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxPath(root.left);
        int rightDepth = maxPath(root.right);

        if (root.left != null && root.right != null && root.left.val == root.val && root.right.val == root.val){
            ans = Math.max(ans, leftDepth + rightDepth + 2);
            return Math.max(leftDepth, rightDepth) + 1;
        }else if (root.left != null && root.val == root.left.val) {
            ans = Math.max(ans, leftDepth + 1);
            return leftDepth + 1;
        }else if (root.right != null && root.val == root.right.val) {
            ans = Math.max(ans, rightDepth + 1);
            return rightDepth + 1;
        }
        return 0;
    }
}
