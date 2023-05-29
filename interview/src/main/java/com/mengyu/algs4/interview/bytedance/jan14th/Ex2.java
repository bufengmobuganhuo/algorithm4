package com.mengyu.algs4.interview.bytedance.jan14th;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/1/14 上午8:53
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {

    }
    private Map<Node, Node> old2New;

    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        old2New = new HashMap<>();
        Node newHead = new Node(head.val);
        old2New.put(head,newHead);
        Node tmp = newHead;
        Node tmpOldHead = head;
        while (tmpOldHead.next != null) {
            tmp.next = clone(tmpOldHead.next);
            if (tmpOldHead.random != null) {
                tmp.random = clone(tmpOldHead.random);
            }
            tmp = tmp.next;
            tmpOldHead = tmpOldHead.next;
        }
        if (tmpOldHead.random!=null){
            tmp.random = clone(tmpOldHead.random);
        }
        return newHead;
    }

    private Node clone(Node oldNode) {
        if (old2New.containsKey(oldNode)) {
            return old2New.get(oldNode);
        } else {
            Node newNode = new Node(oldNode.val);
            old2New.put(oldNode, newNode);
            return newNode;
        }
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
