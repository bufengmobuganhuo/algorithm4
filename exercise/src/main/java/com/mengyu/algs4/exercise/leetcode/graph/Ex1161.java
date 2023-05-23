package com.mengyu.algs4.exercise.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/8/16 6:21 下午
 * TODO
 */
public class Ex1161 {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(7);
        root.right=new TreeNode(0);
        root.left.left=new TreeNode(7);
        root.left.right=new TreeNode(-8);
        Ex1161 ex1161=new Ex1161();
        System.out.println(ex1161.maxLevelSum(root));
    }
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int sum= root.val;
        TreeNode lastNodeInLayer=root;
        TreeNode tmpNodeInLayer=root;
        int depth=1;
        int resDepth=1;
        int tmpSum=0;
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();
            if (node.left!=null){
                queue.offer(node.left);
                tmpNodeInLayer=node.left;
                tmpSum+=node.left.val;
            }
            if (node.right!=null){
                queue.offer(node.right);
                tmpNodeInLayer=node.right;
                tmpSum+=node.right.val;
            }
            if (lastNodeInLayer==node){
                lastNodeInLayer=tmpNodeInLayer;
                depth++;
                if (sum<tmpSum){
                    sum=tmpSum;
                    resDepth=depth;
                }
                tmpSum=0;
            }
        }
        return resDepth;
    }

  static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
