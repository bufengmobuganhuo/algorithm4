package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.*;

/**
 * @author yuzhang
 * @date 2021/4/7 上午9:21
 * TODO
 */
public class Ex103_1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Ex103_1 ex1031 = new Ex103_1();
        ex1031.zigzagLevelOrder(root);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        boolean isLeft = true;
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> tmp = new ArrayList<>(size);
            if (isLeft){
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.pollFirst();
                    tmp.add(node.val);
                    Optional.ofNullable(node.left).ifPresent(deque::offerLast);
                    Optional.ofNullable(node.right).ifPresent(deque::offerLast);
                }
            }else {
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.pollLast();
                    tmp.add(node.val);
                    Optional.ofNullable(node.right).ifPresent(deque::offerFirst);
                    Optional.ofNullable(node.left).ifPresent(deque::offerFirst);
                }
            }
            res.add(tmp);
            isLeft = !isLeft;
        }
        return res;
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
