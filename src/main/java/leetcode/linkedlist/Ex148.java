package leetcode.linkedlist;

import java.util.List;

/**
 * @author yuzhang
 * @date 2020/6/30 8:59 下午
 * 链表排序，同算法练习2.2.17
 */
public class Ex148 {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(4);
        //head.next.next.next = new ListNode(3);
        Ex148 ex148=new Ex148();
        ex148.sortList(head);
    }

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }

        while (true) {
            //找到第一个有序子链表
            ListNode lo = head;
            ListNode mid = findSortedBlock(head);
            if (mid.next == null) {
                break;
            }
            while (mid!=null&&mid.next != null) {
                ListNode hi = findSortedBlock(mid.next);
                if (lo == head) {
                    head = merge(lo, mid, hi);
                } else {
                    lo.next = merge(lo.next, mid, hi);
                }

                // 跳到有序链表的结尾
                if (mid.val < hi.val) {
                    lo = hi;
                } else {
                    lo = mid;
                }

                mid = findSortedBlock(lo.next);
            }
        }
        return head;
    }

    private ListNode merge(ListNode lo, ListNode mid, ListNode hi) {
        ListNode leftPtr = lo;
        ListNode rightPtr = mid.next;
        // 将原链表截成三部分：左有序子链表，右有序子链表，剩余链表
        ListNode last = hi.next;
        mid.next = null;
        hi.next=null;
        ListNode current = null;

        //决定新链表的起点
        if (leftPtr.val < rightPtr.val) {
            current = leftPtr;
            leftPtr = leftPtr.next;
        } else {
            current = rightPtr;
            rightPtr = rightPtr.next;
        }
        // 保存新链表的起点
        ListNode sortedHead = current;

        // 归并
        while (leftPtr != null && rightPtr != null) {
            if (leftPtr.val < rightPtr.val) {
                current.next = leftPtr;
                leftPtr = leftPtr.next;
            } else {
                current.next = rightPtr;
                rightPtr = rightPtr.next;
            }
            current = current.next;
        }
        // 链接剩余有序链表
        current.next = leftPtr != null ? leftPtr : rightPtr;

        //找到有序子链表的最后一个结点,并将其与剩余链表相连
        if (mid.val < hi.val) {
            hi.next = last;
        } else {
            mid.next = last;
        }
        return sortedHead;
    }

    private ListNode findSortedBlock(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode res = node;
        while (res.next != null) {
            if (res.val > res.next.val) {
                break;
            }
            res = res.next;
        }
        return res;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
