package leetcode.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2020/12/18 上午10:57
 * TODO
 */
public class Ex1315 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(50);
        //root.left = new TreeNode(7);
        root.right = new TreeNode(54);
        //root.left.left = new TreeNode(2);
        //root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(98);
        root.right.right = new TreeNode(6);
        //root.left.left.left = new TreeNode(9);
        //root.left.right.left = new TreeNode(1);
        //root.left.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(34);
        Ex1315 ex1315 = new Ex1315();
        System.out.println(ex1315.sumEvenGrandparent2(root));
    }

    /**
     * 当遇到节点为偶数的节点时，直接将其孙子节点进行计算
     *
     * @param root
     * @return
     */
    public int sumEvenGrandparent2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val % 2 == 0) {
                if (node.left != null) {
                    sum += node.left.left == null ? 0 : node.left.left.val;
                    sum += node.left.right == null ? 0 : node.left.right.val;
                }
                if (node.right != null){
                    sum += node.right.left == null ? 0 : node.right.left.val;
                    sum += node.right.right == null ? 0 : node.right.right.val;
                }
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return sum;
    }

    /**
     * 1. 层级遍历，为每个节点编号，记录下那些需要被计算的孙子节点的编号，当遍历到这些节点时，进行计算
     *
     * @param root
     * @return
     */
    public int sumEvenGrandparent1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        // 哪些编号的节点需要被计算
        Set<Integer> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        // 编号从1开始
        queue.offer(new Node(1, root));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.treeNode.val % 2 == 0) {
                // 应该被计算的孙子节点的编号
                set.add(4 * node.id);
                set.add(4 * node.id + 1);
                set.add(4 * node.id + 2);
                set.add(4 * node.id + 3);
            }
            if (node.treeNode.left != null) {
                int id = node.id * 2;
                queue.offer(new Node(id, node.treeNode.left));
                if (set.contains(id)) {
                    sum += node.treeNode.left.val;
                }
            }
            if (node.treeNode.right != null) {
                int id = node.id * 2 + 1;
                queue.offer(new Node(id, node.treeNode.right));
                if (set.contains(id)) {
                    sum += node.treeNode.right.val;
                }
            }
        }
        return sum;
    }

    static class Node {
        int id;
        TreeNode treeNode;

        public Node(int id, TreeNode treeNode) {
            this.id = id;
            this.treeNode = treeNode;
        }
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
