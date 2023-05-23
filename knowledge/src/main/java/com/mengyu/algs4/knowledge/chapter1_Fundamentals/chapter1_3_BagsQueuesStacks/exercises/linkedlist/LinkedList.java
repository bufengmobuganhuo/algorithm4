package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises.linkedlist;

import jdk.nashorn.internal.ir.ReturnNode;
import sun.plugin.cache.OldCacheEntry;

import java.util.Iterator;

/**
 * 链表相关练习
 */
public class LinkedList<T extends Comparable> implements Iterable {
    public Node first;
    private Node last;
    private int size;
    private T maxValue;

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    /**
     * @param item 练习1.3.26 删除指定值
     */
    public void remove(T item) {
        if (isEmpty()) {
            return;
        }

        //先删除头结点，如果头结点一直都是要删除的元素，则一直删除
        while (first != null) {
            if (!first.item.equals(item)) {
                break;
            }
            first = first.next;
            size--;
        }
        Node current = first;
        Node lastCurrent = first;
        while (current != null) {
            if (current.item.equals(item)) {
                lastCurrent.next = current.next;
                size--;
            } else {
                lastCurrent = current;
            }
            current = current.next;
        }
    }

    /**
     * @return 练习1.3.28 使用递归查找链表最大值
     */
    public T max() {
        if (first == null) {
            return null;
        }
        maxValue = first.item;
        return max(first);
    }

    private T max(Node currentHead) {
        if (currentHead == null) {
            return maxValue;
        }
        maxValue = currentHead.item.compareTo(maxValue) > 0 ? currentHead.item : maxValue;
        return max(currentHead.next);
    }

    /**
     * @param head 练习1.3.30 链表反转
     * @return 使用迭代方式反转链表
     */
    public Node reverseListByIterate(Node head) {
        Node first = head;
        //反转后的头结点
        Node reversedHead = null;
        while (first != null) {
            Node second = first.next;
            first.next = reversedHead;
            reversedHead = first;
            first = second;
        }
        return reversedHead;
    }

    public Node reverseByRecursive(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Node second = head.next;
        Node reversedHead = reverseByRecursive(second);
        head.next = null;
        second.next = head;
        return reversedHead;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            T item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public class Node {
        public T item;
        public Node next;
    }
}
