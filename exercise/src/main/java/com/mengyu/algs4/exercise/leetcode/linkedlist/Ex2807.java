package com.mengyu.algs4.exercise.leetcode.linkedlist;

import com.mengyu.algs4.utils.leetcode.ListNode;

/**
 * @author yu zhang
 */
public class Ex2807 {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode left = head, right = head.next;
        while (right != null) {
            int gcd = gcd(left.val, right.val);
            left.next = new ListNode(gcd);
            left.next.next = right;

            left = left.next.next;
            right = right.next;
        }
        return head;
    }

    private int gcd(int a, int b) {
        int mod = 0;
        do {
            mod = a % b;
            a = b;
            b = mod;
        } while (b != 0);
        return a;
    }
}
