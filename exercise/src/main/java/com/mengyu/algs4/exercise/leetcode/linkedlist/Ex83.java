package com.mengyu.algs4.exercise.leetcode.linkedlist;

import com.mengyu.algs4.utils.leetcode.ListNode;

/**
 * @author yu zhang
 */
public class Ex83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(101, head);
        ListNode cur = dummy, next = cur.next;
        while (next != null) {
            if (cur.val == next.val) {
                cur.next = next.next;
                next = cur.next;
            } else {
                cur = next;
                next = next.next;
            }
        }
        return dummy.next;
    }
}
