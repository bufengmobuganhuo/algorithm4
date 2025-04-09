package com.mengyu.algs4.interview.tokensight;

/**
 * @date 2025/3/1 20:47
 * TODO
 */
public class Ex1 {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        Node newHead = reverse(head);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    /**
     * a -> b -> c
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        Node cur = head;
        Node next = head.next;
        cur.next = null;
        while (cur != null && next != null) {
            Node tmp = next.next;
            next.next = cur;
            cur = next;
            next = tmp;
        }
        return cur;
    }

    private static class Node {
        private int val;

        private Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
