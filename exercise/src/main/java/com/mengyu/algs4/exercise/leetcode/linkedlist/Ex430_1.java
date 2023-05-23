package com.mengyu.algs4.exercise.leetcode.linkedlist;

/**
 * @author yu zhang
 */
public class Ex430_1 {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;
        head.next.next = new Node(3);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(4);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.prev = head.next.next.next;
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.prev = head.next.next.next.next;

        head.next.next.child = new Node(7);
        head.next.next.child.next = new Node(8);
        head.next.next.child.next.prev = head.next.next.child;
        /*head.next.next.child.next.next = new Node(9);
        head.next.next.child.next.next.prev = head.next.next.child.next;
        head.next.next.child.next.next.next = new Node(10);
        head.next.next.child.next.next.next.prev = head.next.next.child.next.next;*/

        head.next.next.child.next.child = new Node(11);
        head.next.next.child.next.child.next = new Node(12);
        head.next.next.child.next.child.next.prev = head.next.next.child.next.child;

        /*head.child = new Node(2);
        head.child.child = new Node(3);*/

        Ex430_1 ex430_1 = new Ex430_1();
        head = ex430_1.flatten(head);
        while (head != null) {
            System.out.print("val: " + head.val);
            if (head.next != null) {
                System.out.print(" next: " + head.next.val);;
            }
            if (head.prev != null) {
                System.out.print(" pre: " + head.prev.val);
            }
            System.out.println();
            head = head.next;
        }
    }
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    private Node dfs(Node cur) {
        if (cur == null) {
            return null;
        }
        while (cur != null) {
            while (cur.next != null && cur.child == null) {
                cur = cur.next;
            }
            if (cur.child == null) {
                return cur;
            }
            Node next = cur.next;
            Node child = cur.child;
            cur.next = cur.child;
            child.prev = cur;
            cur.child = null;
            Node last = dfs(child);
            if (next != null) {
                next.prev = last;
            }
            if (last != null) {
                last.next = next;
            }
            // next为空，可能上一层返回的不为空
            cur = next == null ? last : next;
        }
        return cur;
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }
    }
}
