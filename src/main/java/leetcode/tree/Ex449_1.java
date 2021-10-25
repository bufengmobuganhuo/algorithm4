package leetcode.tree;

import java.util.ArrayDeque;

/**
 * @author yu zhang
 */
public class Ex449_1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);
        Ex449_1 ex449_1 = new Ex449_1();
        String ser = ex449_1.serialize(root);
        System.out.println(ser);
        ex449_1.deserialize(ser);
    }

    private static final String SPLIT = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        postorder(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void postorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        postorder(node.left, sb);
        postorder(node.right, sb);
        sb.append(node.val);
        sb.append(SPLIT);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        ArrayDeque<Integer> que = new ArrayDeque<>();
        for (String num : data.split(SPLIT)) {
            que.offer(Integer.parseInt(num));
        }
        return buildTree(Integer.MIN_VALUE, Integer.MAX_VALUE, que);
    }

    private TreeNode buildTree(int lower, int upper, ArrayDeque<Integer> que) {
        if (que.isEmpty()) {
            return null;
        }
        int val = que.getLast();
        // 正常情况下 父节点 > 左子节点 && 父节点 < 右子节点，如果不满足，则说明对应的父节点没数据
        if (val < lower || val > upper) {
            return null;
        }
        que.removeLast();
        TreeNode root = new TreeNode(val);
        root.right = buildTree(val, upper, que);
        root.left = buildTree(lower, val, que);
        return root;
    }
}
