package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_3_BalancedSearchTrees;

/**
 * @author yuzhang
 * @date 2020/11/14 9:18 上午
 * TODO
 */
public class RedBlackBST2<Key extends Comparable<Key>, Value> {
    private final boolean RED = true;
    private RedBlackTreeNode<Key, Value> root;

    public void put(Key key, Value value) {
        if (key == null) {
            return;
        }
        root = putRecursive(root, key, value);
        root.color = !RED;
    }

    private RedBlackTreeNode<Key, Value> putRecursive(RedBlackTreeNode<Key, Value> root, Key key, Value value) {
        if (root == null) {
            return new RedBlackTreeNode<>(key, value, RED, 1);
        }
        int compareRes = root.key.compareTo(key);
        if (compareRes == 0) {
            root.value = value;
        } else if (compareRes > 0) {
            root.left = putRecursive(root.left, key, value);
        } else {
            root.right = putRecursive(root.right, key, value);
        }
        if (isRed(root.right) && !isRed(root.left)) {
            root = rotateLeft(root);
        }
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rotateRight(root);
        }
        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }
        root.nodeCount = size(root.left) + size(root.right) + 1;
        return root;
    }

    private void flipColors(RedBlackTreeNode<Key, Value> node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private boolean isRed(RedBlackTreeNode<Key, Value> node) {
        if (node == null) {
            return false;
        }
        return node.color;
    }

    private RedBlackTreeNode<Key, Value> rotateLeft(RedBlackTreeNode<Key, Value> father) {
        RedBlackTreeNode<Key, Value> tmp = father.right;
        father.right = tmp.left;
        tmp.left = father;
        tmp.color = father.color;
        father.color = RED;
        tmp.nodeCount = father.nodeCount;
        father.nodeCount = size(father.left) + size(father.right) + 1;
        return tmp;
    }

    private RedBlackTreeNode<Key, Value> rotateRight(RedBlackTreeNode<Key, Value> father) {
        RedBlackTreeNode<Key, Value> tmp = father.left;
        father.left = tmp.right;
        tmp.right = father;
        tmp.color = father.color;
        father.color = true;
        tmp.nodeCount = father.nodeCount;
        father.nodeCount = size(father.left) + size(father.right) + 1;
        return tmp;
    }

    private int size(RedBlackTreeNode<Key, Value> node) {
        if (node == null) {
            return 0;
        }
        return node.nodeCount;
    }
}
