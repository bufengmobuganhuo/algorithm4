package com.mengyu.algs4.exercise.leetcode.linkedlist;

/**
 * @author yuzhang
 * @date 2021/1/6 上午10:44
 * TODO
 */
public class Ex237 {

    /**
     * 对于一个链表：4 -> 5 -> 1 -> 9，如果要删除5，则只需要把后面节点的值复制给前一个节点即可
     */
    public void deleteNode(ListNode node) {
        if (node == null || node.next == null) {
            return;
        }
        ListNode lastNode = null;
        while (node.next != null) {
            // 把后面节点的值复制给前一个节点
            node.val = node.next.val;
            lastNode=node;
            node = node.next;
        }
        lastNode.next=null;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
