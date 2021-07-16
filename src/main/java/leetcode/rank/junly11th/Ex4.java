package leetcode.rank.junly11th;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/7/11 上午11:29
 * TODO
 */
public class Ex4 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(5);

        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(4);

        List<TreeNode> trees = Arrays.asList(root1,root2,root3);
        Ex4 ex4 = new Ex4();
        System.out.println(ex4.canMerge(trees));
    }
    public TreeNode canMerge(List<TreeNode> trees) {

        for (int i = 0; i < trees.size() - 1; i++) {
            TreeNode root = trees.get(i);
            TreeNode node = trees.get(i + 1);
            trees.set(i+1,root);
        }
        return trees.get(trees.size() - 1);
    }

    private TreeNode preorder(TreeNode root, TreeNode node) {
        if (node == null || root == null){
            return null;
        }
        root = insert(root, node);
        return root;
    }

    private TreeNode insert(TreeNode root, TreeNode node) {
        if (root == null || node == null) {
            return node;
        }
        if (root.val < node.val){
            root.right = insert(root.right, node);
        }else if (root.val > node.val){
            root.left = insert(root.left, node);
        }else {
            root = node;
        }
        return root;
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
