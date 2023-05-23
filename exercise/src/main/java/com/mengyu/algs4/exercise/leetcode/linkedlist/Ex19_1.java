package com.mengyu.algs4.exercise.leetcode.linkedlist;

/**
 * @author yu zhang
 */
public class Ex19_1 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        /*head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);*/

        Ex19_1 ex19_1 = new Ex19_1();
        ex19_1.removeNthFromEnd(head, 1);
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slowPtr = head, fastPtr = head;
        ListNode pre = null;
        for (int i = 0; i < n - 1; i++) {
            fastPtr = fastPtr.next;
        }
        while (fastPtr.next != null) {
            fastPtr = fastPtr.next;
            pre = slowPtr;
            slowPtr = slowPtr.next;
        }
        if (pre != null) {
            pre.next = slowPtr.next;
        } else {
            head = head.next;
        }
        slowPtr.next = null;
        return head;
    }
}
