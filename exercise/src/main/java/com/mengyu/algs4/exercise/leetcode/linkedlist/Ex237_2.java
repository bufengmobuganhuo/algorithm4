package com.mengyu.algs4.exercise.leetcode.linkedlist;

import com.mengyu.algs4.utils.leetcode.ListNode;

/**
 * @author yuzhang
 * @date 2021/11/6 10:22 上午
 * TODO
 */
public class Ex237_2 {
    public void deleteNode(ListNode node) {
        if (node == null || node.next == null) {
            return;
        }
        ListNode lastNode = null;
        while (node.next != null) {
            node.val = node.next.val;
            lastNode=node;
            node = node.next;
        }
        lastNode.next=null;
    }
}
