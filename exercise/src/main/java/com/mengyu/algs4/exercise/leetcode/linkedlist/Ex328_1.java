package com.mengyu.algs4.exercise.leetcode.linkedlist;

/**
 * @author yuzhang
 * @date 2020/7/1 11:02 上午
 * leetcode328:奇偶链表，第二种解法
 * 将奇偶链表分开，最后再合起来
 */
public class Ex328_1 {
    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        head.next.next.next.next.next=new ListNode(6);
        head.next.next.next.next.next.next=new ListNode(7);
        // head.next.next.next.next.next.next.next=new ListNode(8);

        Ex328_1 ex328_1=new Ex328_1();
        ex328_1.oddEvenList(head);
    }
    public ListNode oddEvenList(ListNode head) {
        if (head==null){
            return null;
        }
        ListNode oddPtr=head;
        ListNode evenPtr=head.next;

        ListNode dummy=new ListNode();
        ListNode curPtr=new ListNode();
        dummy.next=curPtr;

        while(evenPtr!=null){
            curPtr.next=evenPtr;
            oddPtr.next=evenPtr.next;
            evenPtr.next=null;

            oddPtr=(oddPtr.next!=null)?oddPtr.next:oddPtr;
            evenPtr=oddPtr.next;
            curPtr=curPtr.next;
        }
        oddPtr.next=dummy.next.next;
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode(int val) {
            this.val = val;
        }
    }
}
