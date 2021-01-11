package bytedance.jan26;

/**
 * @author yuzhang
 * @date 2021/1/26 上午9:33
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(0);
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.sumNumbers(root));
    }

    private int sum;

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        backtracking(root, new StringBuilder());
        return sum;
    }

    private void backtracking(TreeNode node, StringBuilder num) {
        if (node.left == null && node.right == null) {
            num.append(node.val);
            sum += Integer.parseInt(num.toString());
            return;
        }
        num.append(node.val);
        int lastLen = num.length();
        backtracking(node.left, num);
        num.delete(lastLen, num.length());
        backtracking(node.right, num);
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
