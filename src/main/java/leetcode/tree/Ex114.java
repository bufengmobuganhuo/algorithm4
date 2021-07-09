package leetcode.tree;

/**
 * @author yu zhang
 */
public class Ex114 {
    public static void main(String[] args) {
        Ex114 ex114 = new Ex114();
        TreeNode root = new TreeNode(1);
        //root.left = new TreeNode(2);
        //root.left.left = new TreeNode(3);
        //root.left.right = new TreeNode(4);
        //root.left.left.left = new TreeNode(5);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left = new TreeNode(7);
        root.right.right.left = new TreeNode(3);
        root.right.right.left.right = new TreeNode(9);
        ex114.flatten(root);
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        pre(root);
    }

    private TreeNode pre(TreeNode root) {
        // 如果是叶子节点，直接返回
        if (root.left == null && root.right == null){
            return root;
            // 如果左子节点为空，则展开右子节点
        }else if (root.left == null){
            return pre(root.right);
        }

        TreeNode right = root.right;
        TreeNode left = root.left;

        // 展开左子节点，返回左子节点展开后的最后一个节点
        TreeNode nextLayer = pre(root.left);

        // 展开
        root.right = left;
        root.left = null;

        nextLayer.right = right;

        return right == null ? nextLayer : pre(right);
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
