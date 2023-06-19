package com.mengyu.algs4.exercise.chapter3_searching;

import com.mengyu.algs4.utils.BinarySearchTreeTemplate;
import com.mengyu.algs4.utils.TreeNode;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

/**
 * @author yu zhang
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTreeTemplate<Key, Value>  {

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.put(100, 100);
        binarySearchTree.put(80, 80);
        binarySearchTree.put(120, 120);
        binarySearchTree.put(90, 90);
        binarySearchTree.put(110, 110);
        binarySearchTree.put(125, 125);
        binarySearchTree.put(95, 95);
        System.out.println(binarySearchTree.min().key);
        System.out.println(binarySearchTree.max().key);
        System.out.println(binarySearchTree.rank(110));
        System.out.println(binarySearchTree.select(4).key);
        System.out.println(binarySearchTree.height());
        System.out.println(binarySearchTree.avgCompares());
        binarySearchTree.delete(100);
        binarySearchTree.deleteMax();
        binarySearchTree.deleteMin();
        System.out.println(binarySearchTree.get(125));
        System.out.println(binarySearchTree.height());
        for (Integer key : binarySearchTree.keys(70, 130)) {
            System.out.print(key + " ");
        }
        System.out.println(binarySearchTree.randomKey());
    }

    private TreeNode<Key, Value> root;

    @Override
    public int size() {
        return size(root);
    }

    private int size(TreeNode<Key, Value> node) {
        if (node == null) {
            return 0;
        }
        return node.nodeCount;
    }

    @Override
    public TreeNode<Key, Value> get(Key key) {
        return get(key, root);
    }

    private TreeNode<Key, Value> get(Key key, TreeNode<Key, Value> node) {
        if (node == null) {
            return null;
        }
        int compareRes = node.key.compareTo(key);
        if (compareRes == 0) {
            return node;
        } else if (compareRes < 0) {
            return get(key, node.right);
        } else {
            return get(key, node.left);
        }
    }

    @Override
    public void put(Key key, Value value) {
        root = put(key, value, root);
    }

    private TreeNode<Key, Value> put(Key key, Value value, TreeNode<Key, Value> node) {
        if (node == null) {
            return new TreeNode<>(key, value, 1);
        }
        int compareRes = node.key.compareTo(key);
        if (compareRes < 0) {
            node.right = put(key, value, node.right);
        } else if (compareRes > 0) {
            node.left = put(key, value, node.left);
        } else {
            node.value = value;
        }
        node.nodeCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public TreeNode<Key, Value> min() {
        if (root == null) {
            return null;
        }
        return min(root);
    }

    private TreeNode<Key, Value> min(TreeNode<Key, Value> node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    @Override
    public TreeNode<Key, Value> max() {
        if (root == null) {
            return null;
        }
        return max(root);
    }

    private TreeNode<Key, Value> max(TreeNode<Key, Value> node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    @Override
    public TreeNode<Key, Value> floor(Key key) {
        return floor(key, root);
    }

    private TreeNode<Key, Value> floor(Key key, TreeNode<Key, Value> node) {
        if (node == null) {
            return null;
        }
        int compareRes = node.key.compareTo(key);
        if (compareRes == 0) {
            return node;
        } else if (compareRes > 0) {
            return floor(key, node.left);
        } else {
            TreeNode<Key, Value> res = floor(key, node.right);
            return res != null ? res : node;
        }
    }

    @Override
    public TreeNode<Key, Value> ceiling(Key key) {
        return ceiling(key, root);
    }

    private TreeNode<Key, Value> ceiling(Key key, TreeNode<Key, Value> node) {
        if (node == null) {
            return null;
        }
        int compareRes = node.key.compareTo(key);
        if (compareRes == 0) {
            return node;
        } else if (compareRes < 0) {
            return ceiling(key, node.right);
        } else {
            TreeNode<Key, Value> res = ceiling(key, node.left);
            return res != null ? res : node;
        }
    }

    @Override
    public TreeNode<Key, Value> select(int rank) {
        return select(rank, root);
    }

    private TreeNode<Key, Value> select(int rank, TreeNode<Key, Value> node) {
        if (node == null) {
            return null;
        }
        int nodeRank = size(node.left);
        if (nodeRank == rank) {
            return node;
        } else if (nodeRank < rank) {
            return select(rank - nodeRank - 1, node.right);
        } else {
            return select(rank, node.left);
        }
    }

    @Override
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, TreeNode<Key, Value> node) {
        if (node == null) {
            return -1;
        }
        int compareRes = node.key.compareTo(key);
        if (compareRes == 0) {
            return size(node.left);
        } else if (compareRes > 0) {
            return rank(key, node.left);
        } else {
            return rank(key, node.right) + size(node.left) + 1;
        }
    }

    @Override
    public void deleteMin() {
        if (root == null) {
            return;
        }
        root = deleteMin(root);
    }

    private TreeNode<Key, Value> deleteMin(TreeNode<Key, Value> node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.nodeCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void deleteMax() {
        if (root == null) {
            return;
        }
        root = deleteMax(root);
    }

    private TreeNode<Key, Value> deleteMax(TreeNode<Key, Value> node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = deleteMax(node.right);
        node.nodeCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void delete(Key key) {
        root = delete(key, root);
    }

    private TreeNode<Key, Value> delete(Key key, TreeNode<Key, Value> node) {
        if (node == null) {
            return null;
        }
        int compareRes = node.key.compareTo(key);
        if (compareRes < 0) {
            node.right = delete(key, node.right);
        } else if (compareRes > 0) {
            node.left = delete(key, node.left);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            TreeNode<Key, Value> deletedNode = node;
            node = min(node.right);
            node.right = deleteMin(deletedNode.right);
            node.left = deletedNode.left;
        }
        node.nodeCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> que = new ArrayDeque<>();
        keys(lo, hi, que, root);
        return que;
    }

    private void keys(Key lo, Key hi, Queue<Key> que, TreeNode<Key, Value> node) {
        if (node == null) {
            return;
        }
        int loCompareRes = lo.compareTo(node.key);
        int hiCompareRes = hi.compareTo(node.key);
        if (loCompareRes < 0) {
            keys(lo, hi, que, node.left);
        }
        if (loCompareRes <= 0 && hiCompareRes >= 0) {
            que.offer(node.key);
        }
        if (hiCompareRes > 0) {
            keys(lo, hi, que, node.right);
        }
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public double avgCompares() {
        return 0;
    }

    @Override
    public Key randomKey() {
        Random random = new Random();
        int rank = random.nextInt(size(root));
        return select(rank).key;
    }
}
