package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex23 {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode list3 = new ListNode(2);
        list3.next = new ListNode(6);

        ListNode[] lists = {list1, list2, list3};

        Ex23 ex23 = new Ex23();
        System.out.println(ex23.mergeKLists2(lists));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 使用优先队列存储头节点，从而每次可以取出最小的头节点进行连接
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                priorityQueue.offer(list);
            }
        }
        ListNode res = new ListNode(-1);
        ListNode head = res;
        while (!priorityQueue.isEmpty()) {
            ListNode nextNode = priorityQueue.poll();
            if (nextNode.next != null) {
                priorityQueue.offer(nextNode.next);
                nextNode.next = null;
            }
            res.next = nextNode;
            res = res.next;
        }
        return head.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left >= right) {
            return lists[left];
        }
        // 分治，将链表两两合并
        int mid = left + (right - left) / 2;
        return merge(merge(lists, left, mid), merge(lists, mid + 1, right));
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode i = list1, j = list2;
        ListNode res = new ListNode(-1);
        ListNode head = res;
        while (i != null || j != null) {
            if (i == null) {
                res.next = j;
                j = j.next;
            } else if (j == null) {
                res.next = i;
                i = i.next;
            } else if (i.val <= j.val) {
                res.next = i;
                i = i.next;
            } else {
                res.next = j;
                j = j.next;
            }
            res = res.next;
        }
        return head.next;
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
