package com.mengyu.algs4.exercise.leetcode.linkedlist;

/**
 * @author yuzhang
 * @date 2020/7/1 10:06 上午
 * leetcode328: 奇偶链表
 * 将后面的奇数结点移到前面
 */
public class Ex328 {
    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        head.next.next.next.next.next=new ListNode(6);
        head.next.next.next.next.next.next=new ListNode(7);
        head.next.next.next.next.next.next.next=new ListNode(8);
        Ex328 ex328=new Ex328();
        ex328.oddEvenList(head);
    }
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        // 奇数指针
        ListNode oddPtr = head;
        // 偶数指针
        ListNode evenPtr = head.next;

        int count=2;
        while (true){
            ListNode curPtr=oddPtr;
            for (int i = 0; i < count; i++) {
                if (curPtr==null){
                    return head;
                }
                curPtr=curPtr.next;
            }
            if (curPtr==null){
                return head;
            }
            // 取到下一个奇数结点，将其放到oddPtr和evenPtr之间
            exch(oddPtr,evenPtr,curPtr);
            oddPtr=oddPtr.next;
            evenPtr=evenPtr.next;
            count++;
        }
    }

    private void exch(ListNode oddPtr,ListNode evenPtr,ListNode curPtr){
        ListNode tmp1=oddPtr.next;
        oddPtr.next=curPtr;
        ListNode tmp2= curPtr.next;
        curPtr.next=tmp1;
        evenPtr.next=tmp2;
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
