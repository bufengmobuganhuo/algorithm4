package leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/5/25 上午8:42
 * TODO
 */
public class Ex146_1 {
    private final int MAX_SIZE;
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;

    public Ex146_1(int capacity) {
        this.MAX_SIZE = capacity;
        cache = new HashMap<>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            if (cache.size() == MAX_SIZE) {
                removeLast();
            }
            node = new Node(key, value);
        }
        node.value = value;
        cache.put(key, node);
        moveToHead(node);
    }

    private void removeLast() {
        if (tail != null) {
            int key = tail.key;
            tail = tail.pre;
            if (tail == null) {
                head = null;
            }
            cache.remove(key);
        }
    }

    private void moveToHead(Node node) {
        if (head == node) {
            return;
        }
        //先删除
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        // 如果待删除的是最后一个节点
        if (tail == node) {
            tail = node.pre;
        }
        // 如果删除后链表为空
        if (tail == null || head == null) {
            head = tail = node;
            return;
        }
        // 移动到前面
        node.next = head;
        head.pre = node;
        head = node;
        node.pre = null;
    }

    static class Node {
        int key;
        int value;
        Node next;
        Node pre;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
