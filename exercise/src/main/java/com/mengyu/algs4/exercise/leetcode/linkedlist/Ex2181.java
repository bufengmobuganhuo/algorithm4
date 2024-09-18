package com.mengyu.algs4.exercise.leetcode.linkedlist;

import com.mengyu.algs4.utils.leetcode.ListNode;

/**
 * @author yu zhang
 */
public class Ex2181 {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(0);
//        head.next.next.next.next = new ListNode(4);
//        head.next.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next.next = new ListNode(2);
//        head.next.next.next.next.next.next.next = new ListNode(0);
        System.out.println(new Ex2181().mergeNodes(head));
    }

    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode operationNode = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;

        while (head != null && head.next != null) {
            if (head.val == 0) {
                operationNode = head;
                pre = operationNode;
            } else {
                operationNode.val = operationNode.val + head.val;
            }

            head = head.next;
        }

        pre.next = null;
        return dummy.next;
    }
}
