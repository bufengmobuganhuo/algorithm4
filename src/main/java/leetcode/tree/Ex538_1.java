package leetcode.tree;

/**
 * @author yu zhang
 */
public class Ex538_1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-3);
        root.left = new TreeNode(-4);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(-2);
        root.right.right = new TreeNode(1);

        Ex538_1 ex538_1 = new Ex538_1();
        System.out.println(ex538_1.convertBST(root));
    }
    public TreeNode convertBST(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return sum;
        }
        int rightSum = dfs(root.right, sum);
        root.val += rightSum;
        return dfs(root.left, root.val);
    }
}
