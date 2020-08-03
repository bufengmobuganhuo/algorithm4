package leetcode.tree;

/**
 * @author yuzhang
 * @date 2020/7/21 9:02 上午
 * TODO
 */
public class Ex112 {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(4);
        root.right=new TreeNode(8);
        root.left.left=new TreeNode(11);
        root.left.left.left=new TreeNode(7);
        root.left.left.right=new TreeNode(2);
        root.right.left=new TreeNode(13);
        root.right.right=new TreeNode(4);
        root.right.right.right=new TreeNode(1);
        Ex112 ex112=new Ex112();
        System.out.println(ex112.hasPathSum(root,22));
    }
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root==null){
            return false;
        }
        return dfs(root,sum,0);
    }

    private boolean dfs(TreeNode node,int target,int source){
        if (node==null){
            return false;
        }
        if (node.left==null&&node.right==null&&target==source+node.val){
            return true;
        }
        return dfs(node.left,target,source+node.val)||dfs(node.right,target,source+node.val);
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
