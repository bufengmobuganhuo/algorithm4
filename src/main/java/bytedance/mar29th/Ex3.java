package bytedance.mar29th;

/**
 * @author yuzhang
 * @date 2021/3/29 上午9:51
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        Ex3 ex3 = new Ex3();
        System.out.println(ex3.sumNumbers(root));
    }

    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, new StringBuilder());
    }

    private int sum;

    private int sumNumbers(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return Integer.parseInt(sb.append(root.val).toString());
        }
        sb.append(root.val);
        StringBuilder tmp = new StringBuilder(sb.toString());
        int left = sumNumbers(root.left, sb);
        int right = sumNumbers(root.right, tmp);
        return left + right;
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
