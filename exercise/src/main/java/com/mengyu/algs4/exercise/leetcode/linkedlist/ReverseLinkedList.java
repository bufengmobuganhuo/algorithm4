package com.mengyu.algs4.exercise.leetcode.linkedlist;

import com.mengyu.algs4.utils.leetcode.ListNode;

/**
 * @author yuzhang
 * @date 2021/3/24 上午9:27
 * TODO
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        ListNode newHead = reverseLinkedList.reverseList(head);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    private ListNode newNode;

    public ListNode reverseList(ListNode head) {
        reverseListRecursive(head);
        return newNode;
    }

    private ListNode reverseListIterable(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode lastNode = null;
        ListNode curNode = head;
        while (curNode != null) {
            ListNode nextNode = curNode.next;
            curNode.next = lastNode;
            lastNode = curNode;
            curNode = nextNode;
        }
        return head;
    }

    private ListNode reverseListRecursive(ListNode head) {
        if (head.next == null) {
            newNode = head;
            return head;
        }
        ListNode node = reverseListRecursive(head.next);
        node.next = head;
        head.next = null;
        return head;
    }
}
