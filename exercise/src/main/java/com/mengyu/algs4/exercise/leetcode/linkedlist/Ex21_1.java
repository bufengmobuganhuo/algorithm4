package com.mengyu.algs4.exercise.leetcode.linkedlist;

import com.mengyu.algs4.utils.leetcode.ListNode;

/**
 * @author yu zhang
 */
public class Ex21_1 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode newHead = null;
        if (l1.val < l2.val) {
            newHead = l1;
            l1 = l1.next;
        } else {
            newHead = l2;
            l2 = l2.next;
        }
        ListNode tmp = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tmp.next = l1;
                l1 = l1.next;
            } else {
                tmp.next = l2;
                l2 = l2.next;
            }
            tmp = tmp.next;
        }
        if (l2 != null) {
            tmp.next = l2;
        }
        if (l1 != null) {
            tmp.next = l1;
        }
        return newHead;
    }
}
