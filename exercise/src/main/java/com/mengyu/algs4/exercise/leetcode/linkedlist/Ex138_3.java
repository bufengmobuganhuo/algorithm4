package com.mengyu.algs4.exercise.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/11/3 7:50 下午
 * TODO
 */
public class Ex138_3 {
    private Map<Node,Node> visited = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node oldNode = head;
        Node newNode = new Node(oldNode.val);
        newNode.next = getClonedNode(oldNode.next);
        visited.put(oldNode,newNode);
        newNode.random = getClonedNode(oldNode.random);
        newNode = newNode.next;
        oldNode = oldNode.next;
        while (oldNode != null) {
            newNode.next = getClonedNode(oldNode.next);
            newNode.random = getClonedNode(oldNode.random);

            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return visited.get(head);
    }

    private Node getClonedNode(Node oldNode){
        if (oldNode==null){
            return null;
        }
        if (visited.containsKey(oldNode)){
            return visited.get(oldNode);
        }else{
            Node newNode = new Node(oldNode.val);
            visited.put(oldNode,newNode);
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
