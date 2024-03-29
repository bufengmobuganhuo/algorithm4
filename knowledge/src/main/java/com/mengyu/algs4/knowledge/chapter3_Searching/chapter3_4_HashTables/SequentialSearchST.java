package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_4_HashTables;

import java.util.Random;

/**
 * @author zhangyu
 * 2020/3/31 15:38
 * 链表
 */
public class SequentialSearchST<Key extends Comparable<Key>, Value> {
    public static void main(String[] args) {
        SequentialSearchST<Integer, Integer> sequentialSearchST = new SequentialSearchST<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sequentialSearchST.put(i, i);
        }
        sequentialSearchST.delete(0);
        sequentialSearchST.get(0);
    }

    private SequentialSearchNode first;
    public int size;

    public void put(Key key, Value value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        for (SequentialSearchNode temp = first; temp != null; temp = temp.next) {
            if (key.compareTo(temp.key) == 0) {
                temp.value = value;
                return;
            }
        }
        SequentialSearchNode node = new SequentialSearchNode(key, value, first);
        first = node;
        size++;
    }

    public void put(Key key, Value value, int sizeAtTime) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        for (SequentialSearchNode temp = first; temp != null; temp = temp.next) {
            if (key.compareTo(temp.key) == 0) {
                temp.value = value;
                return;
            }
        }
        SequentialSearchNode node = new SequentialSearchNode(key, value, sizeAtTime, first);
        first = node;
        size++;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        for (SequentialSearchNode temp = first; temp != null; temp = temp.next) {
            if (key.compareTo(temp.key) == 0) {
                return temp.value;
            }
        }
        return null;
    }

    public Key get(int idx) {
        if (idx > size) {
            throw new IndexOutOfBoundsException();
        }
        int index = 0;
        SequentialSearchNode temp = first;
        while (index < size && index < idx) {
            temp = temp.next;
            index++;
        }
        return temp.key;
    }


    public void reverse() {
        SequentialSearchNode prev = null;
        while (first != null) {
            SequentialSearchNode next = first.next;
            first.next = prev;
            prev = first;
            first = next;
        }
        first = prev;
    }

    public void delete(Key key) {
        if (first == null) {
            throw new IndexOutOfBoundsException();
        }
        //因为哈希表中的key不重复，所以不需要循环
        if (key.compareTo(first.key) == 0) {
            size--;
            first = first.next;
            return;
        }
        SequentialSearchNode cur = first;
        SequentialSearchNode lastCur = first;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                size--;
                lastCur.next = cur.next;
                break;
            } else {
                lastCur = cur;
            }
            cur = cur.next;
        }
    }

    public void delete(int sizeAtTime) {
        SequentialSearchNode node = first;
        while (node != null) {
            if (sizeAtTime < node.sizeAtTime) {
                delete(node.key);
                node = node.next;
            } else {
                break;
            }
        }
    }

    class SequentialSearchNode {
        Key key;
        Value value;
        int sizeAtTime;
        SequentialSearchNode next;

        public SequentialSearchNode(Key key, Value value, int sizeAtTime, SequentialSearchNode next) {
            this.key = key;
            this.value = value;
            this.sizeAtTime = sizeAtTime;
            this.next = next;
        }

        public SequentialSearchNode(Key key, Value value, SequentialSearchNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
