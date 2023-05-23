package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_2_BinarySearchTrees;

import com.mengyu.algs4.utils.TreeNode;

/**
 * @author zhangyu
 * 2020/3/16 10:52
 * TODO
 */
public interface BinarySearchTreeTemplate<Key extends Comparable<Key>,Value> {
    /**
     * @return 节点数量
     */
    int size();

    /**
     * @param key 查找
     * @return
     */
    TreeNode<Key,Value> get(Key key);

    /**
     * @param key 插入
     * @param value
     */
    void put(Key key,Value value);

    /**
     * @return 获取最小键
     */
    TreeNode<Key,Value> min();

    /**
     * @return 获取最大键
     */
    TreeNode<Key,Value> max();

    /**
     * @param key 向下取整
     * @return
     */
    TreeNode<Key,Value> floor(Key key);

    /**
     * @param key 向上取整
     * @return
     */
    TreeNode<Key,Value> ceiling(Key key);

    /**
     * @param rank 查找排名为rank的节点，从0开始
     * @return
     */
    TreeNode<Key,Value> select(int rank);

    /**
     * @param key 获取key在树中的排名
     * @return
     */
    int rank(Key key);

    /**
     * 删除最小键
     */
    void deleteMin();

    /**
     * 删除最大键
     */
    void deleteMax();

    /**
     * @param key 删除键
     */
    void delete(Key key);

    /**
     * @param lo
     * @param hi
     * @return 查找在某个范围内的所有键
     */
    Iterable<Key> keys(Key lo,Key hi);

    /**
     * @return 返回二叉树的高度
     * 高度：高度是从下往上数，从0开始
     */
    int height();

    /**
     * @return 返回（树的内部路径长度）/树的大小+1
     * 内部路径长度：所有节点的深度之和
     * 深度：从上往下数，从0开始
     */
    double avgCompares();

    /**
     * @return 随机返回一个键
     */
    Key randomKey();
}
