package com.mengyu.algs4.exercise.leetcode.linkedlist;

/**
 * @author yuzhang
 * @date 2020/12/4 上午11:12
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(9);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        l2.next.next.next = new ListNode(9);
        Ex2 ex2 = new Ex2();
        ex2.addTwoNumbers(l1, l2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode head = null;
        ListNode res = null;
        int flag = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val;
            int value = (sum + flag) % 10;
            flag = (sum + flag) / 10;
            if (head == null) {
                head = new ListNode(value);
                res = head;
            } else {
                ListNode node = new ListNode(value);
                res.next = node;
                res = res.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int value = (flag + l1.val) % 10;
            flag = (flag + l1.val) / 10;
            ListNode node = new ListNode(value);
            res.next = node;
            res = node;
            l1 = l1.next;
        }
        while (l2 != null) {
            int value = (flag + l2.val) % 10;
            flag = (flag + l2.val) / 10;
            ListNode node = new ListNode(value);
            res.next = node;
            res = node;
            l2 = l2.next;
        }
        if (flag > 0) {
            ListNode node = new ListNode(flag);
            res.next = node;
            res = node;
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
