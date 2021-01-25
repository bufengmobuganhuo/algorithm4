package leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/1/22 下午4:08
 * TODO
 */
public class Ex337 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        System.out.println(new Ex337().rob(root));
    }

    /**
     * 对于一个节点，有如下几种状态：
     * 0：偷当前节点，则左右子节点不能偷
     * dp[0] = node.val + left[1] + right[1]
     * 1：不偷当前节点，则（偷 or 不偷）左右子节点
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] dp = dp(root);
        return Math.max(dp[0], dp[1]);
    }

    private int[] dp(TreeNode root) {
        int[] dp = new int[2];
        if (root == null) {
            return dp;
        }
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        dp[0] = root.val + left[1] + right[1];
        dp[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
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
