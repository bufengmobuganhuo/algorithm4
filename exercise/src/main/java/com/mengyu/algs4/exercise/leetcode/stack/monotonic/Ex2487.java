package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import com.mengyu.algs4.utils.leetcode.ListNode;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex2487 {

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        head.next = new ListNode(2);
        head.next.next = new ListNode(13);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(8);
        System.out.println(new Ex2487().removeNodes(head));
    }

    public ListNode removeNodes(ListNode head) {
        Deque<ListNode> stack = new LinkedList<>();
        while (head != null) {
            while (!stack.isEmpty() && stack.peekLast().val < head.val) {
                ListNode node = stack.pollLast();
                node.next = null;
            }
            if (stack.isEmpty()) {
                stack.offerLast(head);
            } else {
                stack.peekLast().next = head;
                stack.offerLast(head);
            }
            head = head.next;
        }
        return stack.isEmpty() ? null : stack.peekFirst();
    }
}
