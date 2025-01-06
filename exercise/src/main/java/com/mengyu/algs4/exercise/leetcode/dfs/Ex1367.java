package com.mengyu.algs4.exercise.leetcode.dfs;

import com.mengyu.algs4.utils.leetcode.ListNode;
import com.mengyu.algs4.utils.leetcode.TreeNode;

/**
 * @author yu zhang
 */
public class Ex1367 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(4);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(1);

        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        System.out.println(new Ex1367().isSubPath(head, root));
    }

    private ListNode head;

    public boolean isSubPath(ListNode head, TreeNode root) {
        this.head = head;
        return dfs(head, root);
    }

    private boolean dfs(ListNode listNode, TreeNode treeNode) {
        if (listNode == null) {
            return true;
        }
        if (treeNode == null) {
            return false;
        }
        boolean result = false;
        if (listNode.val == treeNode.val) {
            result = dfs(listNode.next, treeNode.left) || dfs(listNode.next, treeNode.right);
        }
        if (result) {
            return true;
        }
        // 因为上层还在等待这里的返回，如果这里直接从头开始，则还需要从上层走一遍。
        return head == listNode && (dfs(head, treeNode.left) || dfs(head, treeNode.right));
    }
}
