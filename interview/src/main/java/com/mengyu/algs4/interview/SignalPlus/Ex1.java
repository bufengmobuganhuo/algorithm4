package com.mengyu.algs4.interview.SignalPlus;

/**
 * @date 2025/3/17 19:13
 * TODO
 */
public class Ex1 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        ListNode newHead = reverse(head);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    /**
     * 1 -> 2 -> 3
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head){
        ListNode tmp = head;
        ListNode next = head.next;
        tmp.next = null;
        while (next != null) {
            ListNode tmpNext = next.next;
            next.next = tmp;
            tmp = next;
            next = tmpNext;
        }
        return tmp;
    }

    public static class ListNode {
        private int val;

        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
