package com.mengyu.algs4.exercise.leetcode.tree;

import com.mengyu.algs4.utils.leetcode.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/11/6 10:19 上午
 * TODO
 */
public class Ex863_2 {
    private int K;
    private List<Integer> ans;

    /**
     * 对于dfs(node)，有如下几种情况：
     * 1. target=node，在node的左右子树中查找距离node为k的节点
     * 2. target在node的左子树中，假如target距离node的长度为L，则应该在node的右子树中查找距离node长度为K-L的节点
     * 3. target在node的右子树中，同左子树的情况
     * 4. target不在node的子树中，不需要处理
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        this.K = K;
        ans = new ArrayList<>();
        dfs(root,target);
        return ans;
    }

    private int dfs(TreeNode node, TreeNode target) {
        if (node == null) {
            return -1;
            // 情况一，target=node,则查找node左右子树中距离target长度为K的节点
        } else if (node == target) {
            subtree_add(node,0);
            return 1;
        }else {
            int left = dfs(node.left,target);
            int right = dfs(node.right,target);
            // 情况二，target在node的左子树中
            if (left!=-1){
                if (left==K){
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
}
