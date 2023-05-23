package com.mengyu.algs4.exercise.leetcode.rank.year2022.august21;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.*;

/**
 * @author yuzhang
 * @date 2022/8/21 12:14
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(9);
        root.left.right.right = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(6);
        System.out.println(new Ex3().amountOfTime(root, 3));
    }

    private Set<Integer> marked;

    private Map<Integer, TreeNode> mapped;

    private Map<Integer, TreeNode> reverse;

    private int count;

    public int amountOfTime(TreeNode root, int start) {
        marked = new HashSet<>();
        mapped = new HashMap<>();
        reverse = new HashMap<>();
        dfs(root);
        Queue<TreeNode> que = new LinkedList<>();
        Queue<TreeNode> reverseQue = new LinkedList<>();
        marked.add(start);
        que.offer(mapped.get(start));
        reverseQue.offer(mapped.get(start));
        while (!que.isEmpty() || !reverseQue.isEmpty()) {
            boolean flag = false;
            int size = que.size();
            for (int i = 0; i < size; i++) {
                TreeNode parent = que.poll();
                if (parent.left != null && !marked.contains(parent.left.val)) {
                    que.offer(parent.left);
                    marked.add(parent.left.val);
                    flag = true;
                }
                if (parent.right != null && !marked.contains(parent.right.val)) {
                    que.offer(parent.right);
                    marked.add(parent.right.val);
                    flag = true;
                }
            }
            int reverseSize = reverseQue.size();
            for (int i = 0; i < reverseSize; i++) {
                TreeNode node = reverseQue.poll();
                TreeNode parent = reverse.get(node.val);
                if (parent != null && !marked.contains(parent.val)) {
                    marked.add(parent.val);
                    reverseQue.offer(parent);
                    que.offer(parent);
                    flag = true;
                }
            }
            count = flag ? count + 1 : count;
        }
        return count;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        mapped.put(root.val, root);
        if (root.left != null) {
            reverse.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            reverse.put(root.right.val, root);
            dfs(root.right);
        }
    }
}
