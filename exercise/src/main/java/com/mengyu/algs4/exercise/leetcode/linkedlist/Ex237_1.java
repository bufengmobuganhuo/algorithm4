package com.mengyu.algs4.exercise.leetcode.linkedlist;

/**
 * @author yu zhang
 */
public class Ex237_1 {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(5);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(9);

        Ex237_1 ex237_1 = new Ex237_1();
        ex237_1.deleteNode(head.next);
    }
    public void deleteNode(ListNode node) {
        ListNode pre = null;
        while (node.next != null) {
            ListNode next = node.next;
            node.val = next.val;
            pre = node;
            node = next;
        }
        pre.next = null;
    }
}
