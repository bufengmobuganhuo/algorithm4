package com.mengyu.algs4.exercise.leetcode.linkedlist;

/**
 * @author yuzhang
 * @date 2020/7/3 6:01 下午
 * 扁平化链表
 */
public class Ex430 {
    public static void main(String[] args) {

    }

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node newHead = new Node();
        newHead.next = head;
        dfs(newHead, head);
        newHead.next.pre = null;
        return newHead.next;
    }

    private Node dfs(Node prev, Node cur) {
        if (cur == null) {
            return prev;
        }
        // 将child与prev通过next关联起来
        prev.next = cur;
        cur.pre = prev;
        Node tempNext = cur.next;
        // 将该链表看成一个二叉树，child为左子树
        Node tail = dfs(cur, cur.child);
        cur.child = null;
        // next为右子树
        return dfs(tail, tempNext);
    }

    static class Node {
        int val;
        Node next;
        Node pre;
        Node child;

        public Node() {
        }

        public Node(int val, Node pre) {
            this.val = val;
            this.pre = pre;
        }
    }
}
