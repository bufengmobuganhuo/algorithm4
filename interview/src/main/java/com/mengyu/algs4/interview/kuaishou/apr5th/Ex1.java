package com.mengyu.algs4.interview.kuaishou.apr5th;

/**
 * @author yuzhang
 * @date 2021/4/5 上午10:41
 * TODO
 */
public class Ex1 {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slowPtr = head, fastPtr = head.next;
        while (slowPtr != null && fastPtr != null){
            if (slowPtr == fastPtr){
                return true;
            }
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
