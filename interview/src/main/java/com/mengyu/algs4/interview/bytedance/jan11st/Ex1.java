package com.mengyu.algs4.interview.bytedance.jan11st;

/**
 * @author yuzhang
 * @date 2021/1/11 上午9:31
 * TODO
 */
public class Ex1 {
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        ListNode lastNode = null;
        ListNode curNode = node;
        while (curNode.next != null) {
            lastNode = curNode;
            curNode.val = curNode.next.val;
            curNode = curNode.next;
        }
        lastNode.next = null;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
