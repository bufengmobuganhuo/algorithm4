package com.mengyu.algs4.knowledge.chapter2_Sorting.chapter2_2_MergeSort.exercises;


/**
 * @author yuzhang
 * @date 2021/2/5 上午8:50
 * TODO
 */
public class Ex_2_2_17_2 {
    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(3);
        head.next = new LinkedListNode(4);
        head.next.next = new LinkedListNode(5);
        head.next.next.next = new LinkedListNode(1);
        head.next.next.next.next = new LinkedListNode(2);
        head.next.next.next.next.next = new LinkedListNode(1);
        head.next.next.next.next.next.next = new LinkedListNode(6);
        head.next.next.next.next.next.next.next = new LinkedListNode(3);
        LinkedListNode newHead = sort(head);
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }

    public static LinkedListNode sort(LinkedListNode head) {
        if (head == null) {
            return null;
        }
        LinkedListNode newHead = null, leftStart = head, mid = null, rightEnd = null;
        while (true) {
            if (mid == null) {
                mid = findNextSortedBlock(leftStart);
            }
            if (mid.next == null) {
                break;
            }
            rightEnd = findNextSortedBlock(mid.next);
            LinkedListNode node = merge(leftStart, mid, rightEnd);
            if (newHead == null) {
                newHead = node;

            }
            leftStart = node;
            if (mid.val < rightEnd.val) {
                mid = rightEnd;
            }
        }
        return newHead;
    }

    private static LinkedListNode merge(LinkedListNode leftStart, LinkedListNode mid, LinkedListNode rightEnd) {
        LinkedListNode rightStart = mid.next;
        LinkedListNode rest = rightEnd.next;

        // 打断链表
        mid.next = null;
        rightEnd.next = null;

        LinkedListNode tmpNode = null, newHead = null;
        if (leftStart.val < rightStart.val) {
            tmpNode = leftStart;
            leftStart = leftStart.next;
        } else {
            tmpNode = rightStart;
            rightStart = rightStart.next;
        }
        newHead = tmpNode;
        while (leftStart != null && rightStart != null) {
            if (leftStart.val < rightStart.val) {
                tmpNode.next = leftStart;
                leftStart = leftStart.next;
            } else {
                tmpNode.next = rightStart;
                rightStart = rightStart.next;
            }
            tmpNode = tmpNode.next;
        }
        if (leftStart == null) {
            tmpNode.next = rightStart;
        }
        if (rightStart == null) {
            tmpNode.next = leftStart;
        }
        if (mid.val > rightEnd.val) {
            mid.next = rest;
        } else {
            rightEnd.next = rest;
        }
        return newHead;
    }

    private static LinkedListNode findNextSortedBlock(LinkedListNode node) {
        LinkedListNode tmp = node;
        while (tmp.next != null && tmp.val < tmp.next.val) {
            tmp = tmp.next;
        }
        return tmp;
    }

    static class LinkedListNode {
        int val;
        LinkedListNode next;

        public LinkedListNode(int val) {
            this.val = val;
        }
    }
}
