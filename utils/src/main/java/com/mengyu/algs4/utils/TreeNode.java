package com.mengyu.algs4.utils;

/**
 * @author zhangyu
 * 2020/3/17 14:26
 * TODO
 */
public class TreeNode<Key extends Comparable<Key>,Value> {
    public Key key;
    public Value value;
    public TreeNode<Key,Value> left;
    public TreeNode<Key,Value> right;
    public int nodeCount;
    public int height;
    private TreeNode<Key,Value> pred;
    private TreeNode<Key,Value> succ;

    public TreeNode<Key, Value> getPred() {
        return pred;
    }

    public void setPred(TreeNode<Key, Value> pred) {
        this.pred = pred;
    }

    public TreeNode<Key, Value> getSucc() {
        return succ;
    }

    public void setSucc(TreeNode<Key, Value> succ) {
        this.succ = succ;
    }

    public TreeNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public TreeNode(Key key, Value value, int nodeCount) {
        this.key = key;
        this.value = value;
        this.nodeCount = nodeCount;
    }

    public TreeNode(Key key, Value value, int nodeCount, int height) {
        this.key = key;
        this.value = value;
        this.nodeCount = nodeCount;
        this.height=height;
    }
}
