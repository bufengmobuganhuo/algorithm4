package bytedance.jan15th;

/**
 * @author yuzhang
 * @date 2021/1/15 上午9:21
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(ex1.isBalanced(root));
    }
    private boolean res = true;

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        depth(root);
        return res;
    }

    private int depth(TreeNode father) {
        if (father == null) {
            return 0;
        }
        int leftDepth = 0;
        int rightDepth = 0;
        leftDepth += depth(father.left);
        rightDepth += depth(father.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            res = false;
        }
        return Math.max(leftDepth, rightDepth) + 1;
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
