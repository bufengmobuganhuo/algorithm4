package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_3_BalancedSearchTrees;

import java.util.Iterator;
import java.util.Random;

/**
 * @author yuzhang
 * @date 2021/8/1 上午9:08
 * TODO
 */
public class Treap<Key extends Comparable<Key>, Value> implements Iterable<Key>{
    public static void main(String[] args) {
        Treap<Integer, Integer> treap = new Treap<>();
        treap.put(100, 100);
        treap.put(80, 80);
        treap.put(120, 120);
        treap.put(90, 90);
        treap.put(110, 110);
        treap.put(125, 125);
        treap.put(95, 95);
        System.out.println(treap.size());

        System.out.println(treap.get(90));
        System.out.println(treap.get(1));
        System.out.println(treap.contains(100));

        treap.delete(100);
        System.out.println(treap.contains(100));
        System.out.println(treap.get(100));
        System.out.println(treap.size());
        Iterator<Integer> integerIterator = treap.iterator();
        while (integerIterator.hasNext()){
            System.out.println(integerIterator.next());
        }
    }

    private Node root;

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        root = deleteRecursive(root, key);
    }

    private Node deleteRecursive(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareRes = key.compareTo(node.key);
        if (compareRes > 0) {
            node.right = deleteRecursive(node.right, key);
        } else if (compareRes < 0) {
            node.left = deleteRecursive(node.left, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // 一直让目标节点往下沉，并且在下沉的过程中保证堆的性质
            // 那么到最后，目标节点要么是只有左子树，或者只有右子树
            if (node.left.fix > node.right.fix) {
                node = rotateRight(node);
            } else {
                node = rotateLeft(node);
            }
            node = deleteRecursive(node, key);
        }
        if (node != null) {
            node.size = size(node.left) + size(node.right) + 1;
        }
        return node;
    }


    public boolean contains(Key key) {
        Node cur = root;
        while (cur != null) {
            int compareRes = cur.key.compareTo(key);
            if (compareRes > 0) {
                cur = cur.left;
            } else if (compareRes < 0) {
                cur = cur.right;
            } else {
                break;
            }
        }
        return cur != null;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        return getRecursive(root, key);
    }

    private Value getRecursive(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareRes = key.compareTo(node.key);
        if (compareRes > 0) {
            return getRecursive(node.right, key);
        } else if (compareRes < 0) {
            return getRecursive(node.left, key);
        }
        return node.val;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        root = putRecursive(root, key, val);
    }

    private Node putRecursive(Node node, Key key, Value val) {
        if (node == null) {
            return new Node(key, val, 1);
        }
        int compareRes = key.compareTo(node.key);
        if (compareRes > 0) {
            node.right = putRecursive(node.right, key, val);
            if (node.fix < node.right.fix) {
                node = rotateLeft(node);
            }
        } else if (compareRes < 0) {
            node.left = putRecursive(node.left, key, val);
            if (node.fix < node.left.fix) {
                node = rotateRight(node);
            }
        } else {
            node.key = key;
            node.val = val;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node rotateLeft(Node father) {
        Node tmp = father.right;
        father.right = tmp.left;
        tmp.left = father;
        tmp.size = father.size;
        father.size = size(father.left) + size(father.right) + 1;
        return tmp;
    }

    private Node rotateRight(Node father) {
        Node tmp = father.left;
        father.left = tmp.right;
        tmp.right = father;
        tmp.size = father.size;
        father.size = size(father.left) + size(father.right) + 1;
        return tmp;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    @Override
    public Iterator<Key> iterator() {
        return new It(root);
    }

    private class It implements Iterator<Key> {
        private final Object[] arr;
        private int pointer;
        private int idx;

        public It(Node root) {
            this.arr = new Object[size()];
            this.pointer = 0;
            this.idx = 0;
            inOrder(root);
        }

        private void inOrder(Node node) {
            if (node == null) {
                return;
            }
            inOrder(node.left);
            arr[idx++] = node.key;
            inOrder(node.right);
        }

        @Override
        public boolean hasNext() {
            return pointer < arr.length;
        }

        @Override
        public Key next() {
            return (Key) arr[pointer++];
        }
    }


    private class Node {
        private Random random = new Random();
        private Key key;
        private Value val;
        private double fix;
        Node left;
        Node right;
        int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
            fix = random.nextDouble();
        }
    }
}
