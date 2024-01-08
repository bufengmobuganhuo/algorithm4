package com.mengyu.algs4.exercise.leetcode.linkedlist;

import com.mengyu.algs4.utils.leetcode.ListNode;

/**
 * @author yuzhang
 * @date 2021/3/23 上午10:39
 * TODO
 */
public class Ex92_1 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        Ex92_1 ex92_1 = new Ex92_1();
        System.out.println(ex92_1.reverseBetween(head, 2, 4));
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        } else if (left == right) {
            return head;
        }
        int idx = 0;
        ListNode currentNode = head;
        ListNode lastNode = null;
        while (currentNode != null) {
            idx++;
            if (idx < left) {
                lastNode = currentNode;
                currentNode = currentNode.next;
            } else if (idx < right) {
                ListNode lastTmp = null;
                ListNode leftStart = currentNode;
                while (idx <= right) {
                    ListNode nextNode = currentNode.next;
                    currentNode.next = lastTmp;

                    lastTmp = currentNode;
                    currentNode = nextNode;
                    idx++;
                }
                if (lastNode == null) {
                    leftStart.next = currentNode;
                    return lastTmp;
                } else {
                    lastNode.next = lastTmp;
                    leftStart.next = currentNode;
                }
                break;
            }
        }
        return head;
    }
}
