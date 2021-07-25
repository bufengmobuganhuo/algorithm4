package leetcode.tree;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/7/22 10:15 上午
 * TODO
 */
public class Ex449 {
    public static void main(String[] args) {
        String data = "6/7/9/8/11/13/17/15/12/10";
        Ex449 ex449 = new Ex449();
        ex449.deserialize(data);
    }

    private static final String SPLIT = "/";

    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        postorder(root, stringBuilder);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private void postorder(TreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            return;
        }
        postorder(root.left, stringBuilder);
        postorder(root.right, stringBuilder);
        stringBuilder.append(root.val);
        stringBuilder.append(SPLIT);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        ArrayDeque<Integer> nums = new ArrayDeque<>();
        for (String num : data.split(SPLIT)) {
            nums.offer(Integer.parseInt(num));
        }
        return buildTree(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
    }

    private TreeNode buildTree(Integer lower, Integer upper, ArrayDeque<Integer> nums) {
        if (nums.isEmpty()) {
            return null;
        }
        int val = nums.getLast();
        // 原来中序遍历的作用只是为了通过根结点定位左右子树，
        // 对于二叉搜索树，可以知道根结点的左子树<根结点的值，右子树>根结点的值
        if (val < lower || val > upper) {
            return null;
        }
        nums.removeLast();
        TreeNode root = new TreeNode(val);
        root.right = buildTree(val, upper, nums);
        root.left = buildTree(lower, upper, nums);
        return root;
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
