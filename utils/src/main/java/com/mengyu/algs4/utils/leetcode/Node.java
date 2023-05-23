package com.mengyu.algs4.utils.leetcode;

import java.util.List;

/**
 * @author yuzhang
 * @date 2021/11/6 10:21 上午
 * TODO
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
