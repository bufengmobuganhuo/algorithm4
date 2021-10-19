package leetcode.tree;

/**
 * @author yu zhang
 */
public class Ex124_1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Ex124_1 ex124_1 = new Ex124_1();
        System.out.println(ex124_1.maxPathSum(root));
    }
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);

        max = Math.max(max, left + right + root.val);

        return Math.max(left + root.val, right + root.val);
    }
}
