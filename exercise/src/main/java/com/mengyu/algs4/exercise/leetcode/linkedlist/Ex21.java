package com.mengyu.algs4.exercise.leetcode.linkedlist;

/**
 * @author yuzhang
 * @date 2021/5/13 上午9:17
 * TODO
 */
public class Ex21 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(5);

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(6);

        Ex21 ex21 = new Ex21();
        ex21.mergeTwoLists2(head, head2);
    }

    /**
     * 利用哨兵节点和连表的特性进行归并
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode preNode = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                preNode.next = l1;
                l1 = l1.next;
            }else {
                preNode.next = l2;
                l2 = l2.next;
            }
            preNode = preNode.next;
        }
        preNode.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    /**
     * 归并思想
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode head = null;
        ListNode next = null;
        ListNode tmp = null;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                tmp = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                tmp = l1;
                l1 = l1.next;
            } else if (l1.val < l2.val) {
                tmp = l1;
                l1 = l1.next;
            } else {
                tmp = l2;
                l2 = l2.next;
            }
            if (head == null) {
                head = new ListNode(tmp.val);
                next = head;
            } else {
                next.next = new ListNode(tmp.val);
                next = next.next;
            }
            tmp = tmp.next;
        }
        return head;
    }

    private ListNode findHead(ListNode l1, ListNode l2) {
        ListNode head = null;
        if (l1 == null) {
            head = l2;
        } else if (l2 == null) {
            head = l1;
        } else if (l1.val < l2.val) {
            head = l1;
        } else {
            head = l2;
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
