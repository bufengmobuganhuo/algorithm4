package leetcode.tree;

/**
 * @author yuzhang
 * @date 2020/7/27 10:52 上午
 * TODO
 */
public class Ex669 {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(0);
        root.right=new TreeNode(5);
        root.left.right=new TreeNode(2);
        root.left.right.left=new TreeNode(1);
        Ex669 ex669=new Ex669();
        ex669.trimBST(root,1,3);
    }
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root==null){
            return null;
        }
        if (root.val<L){
            root=trimBST(root.right,L,R);
        }else if (root.val>R){
            root=trimBST(root.left,L,R);
        }else{
            root.left=trimBST(root.left,L,R);
            root.right=trimBST(root.right,L,R);
        }
        return root;
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
