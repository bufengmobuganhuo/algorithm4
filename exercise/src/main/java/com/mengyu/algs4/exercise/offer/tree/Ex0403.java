package com.mengyu.algs4.exercise.offer.tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex0403 {
    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> nodes = new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(tree);
        while (!que.isEmpty()) {
            int size = que.size();
            ListNode head = null, tmp = null;
            for (int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                if (tmp == null) {
                    tmp = new ListNode(node.val);
                    head = tmp;
                } else {
                    tmp.next = new ListNode(node.val);
                    tmp = tmp.next;
                }
                if (node.left != null) {
                    que.offer(node.left);
                }
                if (node.right != null) {
                    que.offer(node.right);
                }
            }
            nodes.add(head);
        }
        ListNode[] ans = new ListNode[nodes.size()];
        nodes.toArray(ans);
        return ans;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
