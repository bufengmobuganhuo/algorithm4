package com.mengyu.algs4.exercise.chapter1_fundamentals;

import java.util.Arrays;
import java.util.Random;

/**
 * @author yu zhang
 */
public class SkipList {

    private final static int MAX_LEVEL = 16;

    private int levelCnt;

    private final static Node head = new Node();

    private final static Random random = new Random();

    private final static float SKIP_LIST_P = 0.5f;

    public Node find(int val) {
        Node node = head;
        for (int i = levelCnt - 1; i >= 0; i--) {
            while (node.next[i] != null && node.next[i].data < val) {
                node = node.next[i];
            }
        }
        if (node.next[0].data == val) {
            return node;
        }
        return null;
    }

    public void insert(int val) {
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = val;
        newNode.maxLevel = level;
        Node[] update = new Node[level];
        Arrays.fill(update, head);

        Node ptr = head;
        for (int i = level - 1; i >= 0; i--) {
            while (ptr.next[i] != null && ptr.next[i].data < val) {
                ptr = ptr.next[i];
            }
            update[i] = ptr;
        }

        for (int i = 0; i < level; i++) {
            newNode.next = update[i].next;
            update[i].next[i] = newNode;
        }
        if (level > levelCnt) {
            levelCnt = level;
        }
    }

    public void delete(int val) {
        Node[] update = new Node[levelCnt];
        Node ptr = head;
        for (int i = levelCnt - 1; i >= 0; i--) {
            while (ptr.next[i] != null && ptr.next[i].data < val) {
                ptr = ptr.next[i];
            }
            update[i] = ptr;
        }

        for (int i = 0; i < levelCnt; i++) {
            if (update[i].next[i] != null && update[i].next[i].data == val) {
                update[i].next[i] = update[i].next[i].next[i];
            }
        }
    }

    private int randomLevel() {
        int level = 1;
        while (random.nextFloat() < SKIP_LIST_P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    private static class Node {
        private int data;

        private Node[] next = new Node[MAX_LEVEL];

        private int maxLevel;
    }
}
