package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/3/1 上午10:07
 * TODO
 */
public class Ex863 {

    private Map<Integer, TreeNode> parents = new HashMap<>();

    private List<Integer> ans = new ArrayList<>();

    /**
     * 1. 如果这个二叉树变成了一张单向图，就可以从target开始DFS，遇到深度为K的就是要找的节点
     * 2. 可以构造一个Map，存储一个节点的父节点，这样在进行上述的DFS的时候，也从父节点找一下，就找到了所有的
     */
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        // 找到父节点
        findParents(root);
        dfs(target, null, 0, K);
        return ans;
    }

    private void findParents(TreeNode node) {
        if (node.left != null) {
            parents.put(node.left.val, node);
            findParents(node.left);
        }
        if (node.right != null) {
            parents.put(node.right.val, node);
            findParents(node.right);
        }
    }

    private void dfs(TreeNode node, TreeNode startNode, int depth, int K) {
        if (node == null) {
            return;
        }
        // DFS找到了深度相同的
        if (depth == K){
            ans.add(node.val);
            return;
        }
        // 往左右子节点找
        if (node.left != startNode){
            dfs(node.left, node, depth + 1, K);
        }
        if (node.right != startNode){
            dfs(node.right, node, depth + 1, K);
        }
        // 往父亲节点上找
        if (parents.get(node.val) != startNode){
            dfs(parents.get(node.val), node, depth + 1, K);
        }
    }

    private int K;

    /**
     * 对于dfs(node)，有如下几种情况：
     * 1. target=node，在node的左右子树中查找距离node为k的节点
     * 2. target在node的左子树中，假如target距离node的长度为L，则应该在node的右子树中查找距离node长度为K-L的节点
     * 3. target在node的右子树中，同左子树的情况
     * 4. target不在node的子树中，不需要处理
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        this.K = K;
        dfs(root,target);
        return ans;
    }

    private int dfs(TreeNode node, TreeNode target) {
        if (node == null) {
            return -1;
            // 情况一，target=node,则查找node左右子树中距离target长度为K的节点
        } else if (node == target) {
            subtree_add(node,0);
            // 上一层节点距离target的距离
            return 1;
        }else {
            int left = dfs(node.left,target);
            int right = dfs(node.right,target);
            // 情况二，target在node的左子树中
            if (left!=-1){
                if (left == K) {
                    ans.add(node.val);
                }
                subtree_add(node.right,left+1);
                // 上一层节点距离target的距离
                return left+1;
                // 情况三，target在node的右子树中
            }else if (right!=-1){
                if (right==K){
                    ans.add(node.val);
                }
                subtree_add(node.left,right+1);
                return right+1;
            }
        }
        return -1;
    }

    /**
     * 在已经距离node长度为dist的情况下，在node的子树中查找距离node长度为K的节点
     */
    private void subtree_add(TreeNode node, int dist) {
        if (node == null) {
            return;
        } else if (dist == K) {
            ans.add(node.val);
        } else {
            subtree_add(node.left, dist + 1);
            subtree_add(node.right, dist + 1);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
