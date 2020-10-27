package leetcode.tree;

/**
 * @author yuzhang
 * @date 2020/10/21 9:45 上午
 * TODO
 */
public class Ex543_1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        Ex543_1 ex543_1=new Ex543_1();
        System.out.println(ex543_1.diameterOfBinaryTree(root));
    }

    private int ans=1;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root==null){
            return 0;
        }
        depth(root);
        return ans-1;
    }

    private int depth(TreeNode root){
        if (root==null){
            return 0;
        }
        // 左子树的路径长度
        int leftLen = depth(root.left);
        // 右子树的路径长度
        int rightLen = depth(root.right);
        ans = Math.max(ans,1+leftLen+rightLen);
        // 返回以该结点为根的子树的最大深度
        return Math.max(leftLen,rightLen)+1;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
    }
}
