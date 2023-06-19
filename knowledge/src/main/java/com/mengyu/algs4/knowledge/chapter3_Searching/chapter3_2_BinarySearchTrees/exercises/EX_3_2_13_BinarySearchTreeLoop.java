package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_2_BinarySearchTrees.exercises;

import com.mengyu.algs4.utils.BinarySearchTreeTemplate;
import com.mengyu.algs4.utils.TreeNode;
import java.util.Random;
import java.util.Stack;

/**
 * @author zhangyu
 * 2020/3/17 11:39
 * 练习3.2.13-14：非递归版本的二叉查找树
 */
public class EX_3_2_13_BinarySearchTreeLoop<Key extends Comparable<Key>, Value> implements BinarySearchTreeTemplate<Key, Value> {
    public static void main(String[] args) {
        EX_3_2_13_BinarySearchTreeLoop<Integer, Integer> binarySearchTree = new EX_3_2_13_BinarySearchTreeLoop<>();
        binarySearchTree.put(100, 100);
        binarySearchTree.put(80, 80);
        binarySearchTree.put(120, 120);
        binarySearchTree.put(90, 90);
        binarySearchTree.put(110, 110);
        binarySearchTree.put(125, 125);
        binarySearchTree.put(95, 95);
        System.out.println(binarySearchTree.get(125));
        System.out.println(binarySearchTree.min().key);
        System.out.println(binarySearchTree.max().key);
        System.out.println(binarySearchTree.floor(105).key);
        System.out.println(binarySearchTree.ceiling(123).key);
        System.out.println(binarySearchTree.rank(110));
        System.out.println(binarySearchTree.select(4).key);

    }

    private TreeNode<Key, Value> root;

    @Override
    public int size() {
        return size(root);
    }

    private int size(TreeNode<Key, Value> node) {
        return node == null ? 0 : node.nodeCount;
    }

    @Override
    public TreeNode<Key, Value> get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        TreeNode<Key, Value> node = root;
        while (node != null) {
            int compareRes = node.key.compareTo(key);
            if (compareRes == 0) {
                return node;
            } else if (compareRes > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        if (root == null) {
            TreeNode<Key, Value> newNode = new TreeNode<>(key, value, 1);
            root = newNode;
            return;
        }
        // 保存节点遍历的路径
        Stack<TreeNode<Key, Value>> paths = new Stack<>();
        TreeNode<Key, Value> temp = root;
        while (temp != null) {
            paths.push(temp);
            int compareRes = temp.key.compareTo(key);
            if (compareRes > 0) {
                temp = temp.left;
            } else if (compareRes < 0) {
                temp = temp.right;
            } else {
                temp.value = value;
                return;
            }
        }
        TreeNode<Key, Value> newNode = new TreeNode<>(key, value, 1);
        //路径最上面的是新插入节点的父节点
        TreeNode<Key, Value> fatherNode = paths.peek();
        if (fatherNode.key.compareTo(key) > 0) {
            fatherNode.left = newNode;
        } else {
            fatherNode.right = newNode;
        }
        //额外遍历一次，更新nodeCount
        while (!paths.isEmpty()) {
            paths.pop().nodeCount++;
        }
    }

    @Override
    public TreeNode<Key, Value> min() {
        TreeNode<Key, Value> temp = root;
        while (temp != null && temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    @Override
    public TreeNode<Key, Value> max() {
        TreeNode<Key, Value> temp = root;
        while (temp != null && temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    @Override
    public TreeNode<Key, Value> floor(Key key) {
        TreeNode<Key, Value> floorNode = null;
        TreeNode<Key, Value> temp = root;
        while (temp != null) {
            int compareRes = temp.key.compareTo(key);
            //如果相等，则一定是<=key的最大值
            if (compareRes == 0) {
                return temp;
                //如果temp.key>key,则<=key的最大值在左子树中
            } else if (compareRes > 0) {
                temp = temp.left;
                //temp.key<key,则只有在temp节点的右子树中存在键<=key时，才是结果，
                // 否则<=key的最大键就是temp.key
            } else {
                floorNode = temp;
                temp = temp.right;
            }
        }
        return floorNode;
    }

    @Override
    public TreeNode<Key, Value> ceiling(Key key) {
        TreeNode<Key, Value> ceilNode = null;
        TreeNode<Key, Value> temp = root;
        while (temp != null) {
            int compareRes = temp.key.compareTo(key);
            //如果temp.key=key,则temp就是>=key的最小键
            if (compareRes == 0) {
                return temp;
                //如果temp.key>key,则只有在temp节点的左子树中存在键>=key时，才是结果
                //否则<=key的最小键就是temp.key
            } else if (compareRes > 0) {
                ceilNode = temp;
                temp = temp.left;
                //如果temp.key<key，则>=key的最小键一定在temp的右子树中
            } else {
                temp = temp.right;
            }
        }
        return ceilNode;
    }

    @Override
    public TreeNode<Key, Value> select(int rank) {
        TreeNode<Key, Value> temp = root;
        while (temp != null) {
            int nodeCount = size(temp.left);
            //如果temp.left.nodeCount=rank，则就是要查找的键
            if (nodeCount == rank) {
                return temp;
                //如果temp.left.nodeCount>rank，则向temp的左子树查找
            } else if (nodeCount > rank) {
                temp = temp.left;
            } else {
                //如果temp.left.nodeCount<rank，
                // 则向temp的右子树查找排名为rank-temp.left.nodeCount-1的键
                rank = rank - nodeCount - 1;
                temp = temp.right;
            }
        }
        return null;
    }

    @Override
    public int rank(Key key) {
        int rank = 0;
        TreeNode<Key, Value> temp = root;
        while (temp != null) {
            int compareRes = temp.key.compareTo(key);
            //如果temp.key>key，则key的排名需要在temp的左子树中查找
            if (compareRes > 0) {
                temp = temp.left;
                //如果temp.key<key，则key的排名=size(temp.left)+1+key在temp右子树中的排名
            } else if (compareRes < 0) {
                rank += 1 + size(temp.left);
                temp = temp.right;
                //如果temp.key=key，则key的排名=size(temp.left)
            } else {
                rank += size(temp.left);
                return rank;
            }
        }
        return -1;
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
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
        int randomRank = random.nextInt(size());
        return select(randomRank).key;
    }


}
