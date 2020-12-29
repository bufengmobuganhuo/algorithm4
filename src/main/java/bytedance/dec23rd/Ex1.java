package bytedance.dec23rd;

/**
 * @author yuzhang
 * @date 2020/12/23 上午9:20
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(4);
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.hasPathSum(root,22));
    }
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return find(root, 0, sum);
    }

    private boolean find(TreeNode root, int sum, int target) {
        if (root.left == null && root.right == null) {
            return sum + root.val == target;
        }
        if (root.left != null && find(root.left, sum + root.val, target)) {
            return true;
        }
        return root.right != null && find(root.right, sum + root.val, target);
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
