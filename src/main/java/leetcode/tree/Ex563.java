package leetcode.tree;

/**
 * @author yuzhang
 * @date 2020/7/23 11:21 上午
 * TODO
 */
public class Ex563 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.left.left.left = new TreeNode(3);

        root.right = new TreeNode(15);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(17);
        root.right.left.left = new TreeNode(11);
        root.right.left.right = new TreeNode(14);
        root.right.right.left = new TreeNode(16);

        Ex563 ex563=new Ex563();
        System.out.println(ex563.findTilt(root));
    }
    private int delta;
    public int findTilt(TreeNode root) {
        if (root==null){
            return 0;
        }
        sum(root);
        return delta;
    }

    private int sum(TreeNode root){
        if (root==null){
            return 0;
        }
        int leftSum=sum(root.left);
        int rightSum=sum(root.right);
        delta+=Math.abs(leftSum-rightSum);
        return leftSum+rightSum+root.val;
    }
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
