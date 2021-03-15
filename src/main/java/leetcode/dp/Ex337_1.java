package leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/2/22 上午10:13
 * TODO
 */
public class Ex337_1 {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] dp = robRecursive(root);
        return Math.max(dp[0], dp[1]);
    }

    private int[] robRecursive(TreeNode node) {
        if (node == null) {
            return new int[2];
        }
        int[] left = robRecursive(node.left);
        int[] right = robRecursive(node.right);
        int[] dp = new int[2];
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        dp[1] = left[0] + node.val + right[0];
        return dp;
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
