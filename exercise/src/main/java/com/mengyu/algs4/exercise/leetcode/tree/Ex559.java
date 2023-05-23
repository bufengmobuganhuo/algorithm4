package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/27 11:39 上午
 * TODO
 */
public class Ex559 {
    public static void main(String[] args) {

    }
    public int maxDepth(Node root) {
        if (root==null){
            return 0;
        }
        return getDepth(root,1);
    }

    private int getDepth(Node node,int depth){
        if (node==null){
            return depth-1;
        }
        int maxDepth=depth;
        for (Node child:node.children) {
            int childDepth = getDepth(child,depth+1);
            if (childDepth>maxDepth){
                maxDepth=childDepth;
            }
        }
        return maxDepth;
    }

    static class Node {
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
}
