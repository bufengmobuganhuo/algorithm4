package com.mengyu.algs4.exercise.leetcode.linkedlist;

import com.mengyu.algs4.utils.leetcode.ListNode;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex2130 {
    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        head.next = new ListNode(4);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(7);
        head.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next = new ListNode(6);

        System.out.println(new Ex2130().pairSum2(head));
    }

    public int pairSum2(ListNode head) {
        if (head.next.next == null) {
            return head.val + head.next.val;
        }
        ListNode slower = head, faster = head;
        // 找到链表下半段的起始节点
        while (faster != null && faster.next != null) {
            slower = slower.next;
            faster = faster.next.next;
        }
        // 反转下半段链表
        ListNode cur = slower, prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        int ans = Integer.MIN_VALUE;
        while (head != null && prev != null) {
            ans = Math.max(ans, head.val + prev.val);
            head = head.next;
            prev = prev.next;
        }
        return ans;
    }

    public int pairSum(ListNode head) {
        if (head.next.next == null) {
            return head.val + head.next.val;
        }
        int ans = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        ListNode slower = head, faster = head.next.next;
        while (slower != null) {
            if (faster.next == null) {
                int num1 = stack.pop();
                ans = Math.max(ans, num1 + slower.val);
            } else if (faster.next.next != null) {
                stack.push(slower.val);
                faster = faster.next.next;
            } else {
                stack.push(slower.val);
                stack.push(slower.next.val);
                faster = faster.next;
                slower = slower.next;
            }
            slower = slower.next;
        }
        return ans;
    }
}
