package leetcode.tree;

/**
 * @author yu zhang
 */
public class Ex543_2 {
    private int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans - 1;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        ans = Math.max(ans, left + right + 1);
        return Math.max(left + 1, right + 1);
    }
}
