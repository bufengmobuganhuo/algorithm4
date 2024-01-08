package com.mengyu.algs4.exercise.leetcode.linkedlist;

import com.mengyu.algs4.utils.leetcode.ListNode;

/**
 * @author yu zhang
 */
public class Ex725 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next.next.next = new ListNode(10);
        Ex725 ex725 = new Ex725();
        System.out.println(ex725.splitListToParts(head, 3));
    }
    public ListNode[] splitListToParts(ListNode head, int k) {
        int size = 0;
        ListNode tmp = head;
        while (tmp != null) {
            size++;
            tmp = tmp.next;
        }
        ListNode[] res = new ListNode[k];
        tmp = head;
        if (size <= k) {
            for (int i = 0; i < size; i++) {
                res[i] = tmp;
                ListNode next = tmp.next;
                tmp.next = null;
                tmp = next;
            }
            return res;
        }
        int len = size / k;
        int mod = size % k;
        for (int i = 0; i < k; i++) {
            ListNode newHead = tmp;
            int right = mod > 0 ? len + 1 : len;
            for (int j = 0; j < right - 1; j++) {
                tmp = tmp.next;
            }
            res[i] = newHead;
            ListNode next = tmp.next;
            tmp.next = null;
            tmp = next;
            mod--;
        }
        return res;
    }
}
