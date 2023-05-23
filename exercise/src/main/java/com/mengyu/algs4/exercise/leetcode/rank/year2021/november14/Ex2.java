package com.mengyu.algs4.exercise.leetcode.rank.year2021.november14;

/**
 * @author yuzhang
 * @date 2021/11/14 10:41 上午
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(4);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        head.next.next.next.next = new ListNode(3);
 /*       head.next.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next.next = new ListNode(4);*/

        Ex2 ex2 = new Ex2();
        ListNode newHead = ex2.reverseEvenLengthGroups(head);
        System.out.println(newHead);

    }
    public ListNode reverseEvenLengthGroups(ListNode head) {
        if (head.next == null) {
            return head;
        }
        int len = 1;
        ListNode tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
            len++;
        }
        int countLen = 1;
        int idx = 2;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            ListNode[] reverseRes;
            if (countLen + idx > len) {
                if ((len - countLen) % 2 == 0) {
                    reverseRes = reverse(cur, idx);
                } else {
                    reverseRes = iteral(cur, idx);
                }
            }else if (idx % 2 == 0) {
                reverseRes = reverse(cur, idx);
            } else {
                reverseRes = iteral(cur, idx);
            }
            countLen += idx;
            pre.next = reverseRes[0];
            pre = reverseRes[1];
            cur = reverseRes[1].next;
            idx++;
        }
        return head;
    }

    private ListNode[] iteral(ListNode head, int num) {
        ListNode cur = head;
        for (int i = 0; i < num - 1 && cur.next != null; i++) {
            cur = cur.next;
        }
        return new ListNode[]{head, cur};
    }

    private ListNode[] reverse(ListNode head, int num) {
        int count = 0;
        ListNode pre = null;
        ListNode cur = head;
        while (count < num && cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            count++;
        }
        head.next = cur;
        return new ListNode[]{pre, head};
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
