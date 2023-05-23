package com.mengyu.algs4.exercise.offer.linkedlist;

/**
 * @author yu zhang
 */
public class Ex22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slowPtr = head, fastPtr = head;
        for (int i = 0; i < k; i++) {
            fastPtr = fastPtr.next;
        }
        while (fastPtr != null) {
            slowPtr = slowPtr.next;
            for (int i = 0; i < k; i++) {
                fastPtr = fastPtr.next;
            }
        }
        return slowPtr;
    }
}
