package leetcode.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2021/2/3 下午4:29
 * TODO
 */
public class Ex951 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(8);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(6);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        root2.right.right.left = new TreeNode(8);
        root2.right.right.right = new TreeNode(7);

        Ex951 ex951 = new Ex951();
        System.out.println(ex951.flipEquiv(root1, root2));
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null) {
            return false;
        } else if (root2 == null) {
            return false;
        } else if (root1.left==null&&root1.right==null&&root2.left==null&&root2.right==null){
            return true;
        } else if (root1.val!=root2.val){
            return false;
        }
        int left1 = root1.left == null ? Integer.MAX_VALUE : root1.left.val;
        int right1 = root1.right == null ? Integer.MAX_VALUE : root1.right.val;
        int left2 = root2.left == null ? Integer.MAX_VALUE : root2.left.val;
        int right2 = root2.right == null ? Integer.MAX_VALUE : root2.right.val;
        if (!((left1 == left2 && right1 == right2) || (left1 == right2 && right1 == left2))) {
            return false;
        }
        if (left1 == left2) {
            return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
        } else {
            return flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
        }
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
