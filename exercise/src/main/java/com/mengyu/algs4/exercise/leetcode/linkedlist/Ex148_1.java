package com.mengyu.algs4.exercise.leetcode.linkedlist;


/**
 * @author yuzhang
 * @date 2020/11/3 8:12 下午
 * TODO
 */
public class Ex148_1 {
    public static void main(String[] args) {
        int[] arr = {4,19,14,5,-3,1,8,5,11,15};
        ListNode head = new ListNode(4);
        ListNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next=new ListNode(arr[i]);
            cur = cur.next;
        }
        Ex148_1 ex148_1 = new Ex148_1();
        ex148_1.sortList(head);
    }
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }

        while (true) {
            ListNode leftPtr = head;
            ListNode mid = findNextBlockEnd(leftPtr);
            if (mid.next == null) {
                break;
            }

            while (mid != null && mid.next != null) {
                ListNode endNode = findNextBlockEnd(mid.next);
                if (head == leftPtr) {
                    head = merge(leftPtr, mid, endNode);
                } else {
                    leftPtr.next = merge(leftPtr.next, mid, endNode);
                }

                if (mid.val < endNode.val) {
                    leftPtr = endNode;
                } else {
                    leftPtr = mid;
                }

                mid = findNextBlockEnd(leftPtr.next);
            }
        }
        return head;
    }

    private ListNode merge(ListNode start, ListNode mid, ListNode end) {
        ListNode leftPtr = start, rightPtr = mid.next;
        mid.next = null;
        ListNode lastStartNode = end.next;
        end.next = null;

        ListNode head = null;
        ListNode cur = null;

        if (leftPtr.val < rightPtr.val) {
            head = leftPtr;
            leftPtr = leftPtr.next;
        } else {
            head = rightPtr;
            rightPtr = rightPtr.next;
        }
        cur = head;

        while (leftPtr != null && rightPtr != null) {
            if (leftPtr.val < rightPtr.val) {
                cur.next = leftPtr;
                leftPtr = leftPtr.next;
            } else {
                cur.next = rightPtr;
                rightPtr = rightPtr.next;
            }
            cur = cur.next;
        }

        if (leftPtr != null) {
            cur.next = leftPtr;
        } else {
            cur.next = rightPtr;
        }

        if (mid.val < end.val) {
            end.next = lastStartNode;
        } else {
            mid.next = lastStartNode;
        }
        return head;
    }

    private ListNode findNextBlockEnd(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode end = head;
        while (end.next != null && end.val <= end.next.val) {
            end = end.next;
        }
        return end;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
