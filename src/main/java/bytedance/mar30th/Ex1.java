package bytedance.mar30th;

import bytedance.mar29th.Ex3;

/**
 * @author yuzhang
 * @date 2021/3/30 上午8:53
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.minDiffInBST(root));
    }

    private int lastNodeVal = Integer.MAX_VALUE;
    private int min = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        inOrder(root);
        return min;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        min = Math.min(min, Math.abs(root.val - lastNodeVal));
        lastNodeVal = root.val;
        inOrder(root.right);
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
