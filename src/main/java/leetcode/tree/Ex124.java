package leetcode.tree;

/**
 * @author yu zhang
 */
public class Ex124 {
    private int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        //root.left.left = new TreeNode(2);
        //root.left.right = new TreeNode(3);
        //root.left.left.left = new TreeNode(4);
        Ex124 ex124 = new Ex124();
        System.out.println(ex124.maxPathSum(root));
    }

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxGain(root);
        return ans;
    }

    private int maxGain(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 如果左边返回的路径和<0，就不让他对当前层和上层产生影响
        int left = Math.max(maxGain(root.left), 0);
        int right = Math.max(maxGain(root.right), 0);

        // 以当前节点为结束，能取到的最大路径和
        ans = Math.max(ans, root.val + left + right);
        // 往上层走，与上层的节点组成路径，那么只能取左边，或者右边
        return root.val + Math.max(left, right);
    }
}
