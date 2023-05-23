package com.mengyu.algs4.exercise.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/6/30 11:21 上午
 * 复制带随机指针的链表,解法一
 */
public class Ex138_1 {
    /**
     * 旧node -> 拷贝出的新node
     */
    private Map<Node, Node> visited = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node oldNode = head;
        Node newNode = new Node(oldNode.val);
        newNode.next = getClonedNode(head.next);
        visited.put(oldNode, newNode);
        newNode.random = getClonedNode(head.random);
        oldNode = oldNode.next;
        newNode = newNode.next;
        while (oldNode != null) {
            newNode.next = getClonedNode(oldNode.next);
            newNode.random = getClonedNode(oldNode.random);

            newNode = newNode.next;
            oldNode = oldNode.next;
        }
        return visited.get(head);
    }

    private Node getClonedNode(Node node) {
        if (node == null) {
            return null;
        }
        if (visited.containsKey(node)) {
            return visited.get(node);
        } else {
            Node newNode = new Node(node.val);
            visited.put(node, newNode);
            return newNode;
        }
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
        }
    }
}
