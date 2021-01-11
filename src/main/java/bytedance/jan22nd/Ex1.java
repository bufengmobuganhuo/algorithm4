package bytedance.jan22nd;

/**
 * @author yuzhang
 * @date 2021/1/22 上午8:52
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.hasPathSum(root,1));
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return dfs(root, targetSum, 0);
    }

    private boolean dfs(TreeNode node, int target, int sum) {
        if (node==null){
            return false;
        }
        sum += node.val;
        if (node.left == null && node.right==null) {
            return sum == target;
        }
        return dfs(node.left, target, sum) || dfs(node.right, target, sum);
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
