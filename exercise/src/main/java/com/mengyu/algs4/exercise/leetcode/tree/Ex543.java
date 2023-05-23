package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/7/23 10:26 上午
 * TODO
 */
public class Ex543 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.left.left.left = new TreeNode(3);

        root.right = new TreeNode(15);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(17);
        root.right.left.left = new TreeNode(11);
        root.right.left.right = new TreeNode(14);
        root.right.right.left = new TreeNode(16);

        Ex543 ex543=new Ex543();
        System.out.println(ex543.diameterOfBinaryTree(root));
    }

    /**
     * 逐层逐个遍历结点(root)，计算从root.left-> root -> root.right的最长路径
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root==null){
            return 0;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int depth=0;
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();
            int depthTmp=0;
            if (node.left!=null){
                queue.offer(node.left);
                depthTmp=dfs(node.left);
            }
            if (node.right!=null){
                queue.offer(node.right);
                depthTmp+=dfs(node.right);
            }
            depth=Math.max(depth,depthTmp);
        }
        return depth;
    }

    private int ans;
    public int diameterOfBinaryTree2(TreeNode root) {
        if (root==null){
            return 0;
        }
        depth(root);
        return ans;
    }

    private int depth(TreeNode node){
        if (node==null){
            return 0;
        }
        // 左子树的路径长度
        int leftDepth=depth(node.left);
        // 右子树的路径长度
        int rightDepth=depth(node.right);
        // 最长路径
        ans=Math.max(ans,leftDepth+rightDepth+1);
        // 返回以该结点为根的子树的最大深度
        return Math.max(leftDepth,rightDepth)+1;
    }

    private int dfs(TreeNode root){
        if (root==null){
            return 0;
        }
        int depth=1;
        depth+=Math.max(dfs(root.left),dfs(root.right));
        return depth;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
