package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/7/26 3:16 下午
 * TODO
 */
public class Ex652 {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.right.left=new TreeNode(2);
        root.right.right=new TreeNode(4);
        root.right.left.left=new TreeNode(4);
        Ex652 ex652=new Ex652();
        ex652.findDuplicateSubtrees(root);
    }
    int t;
    Map<String,Integer> trees;
    Map<Integer, Integer> count;
    List<TreeNode> ans;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        t=1;
        trees=new HashMap<>();
        count=new HashMap<>();
        ans=new ArrayList<>();
        lookup(root);
        return ans;
    }

    private int lookup(TreeNode node){
        if (node==null){
            return 0;
        }
        String serial=node.val+","+lookup(node.left)+","+lookup(node.right);
        int uid=trees.computeIfAbsent(serial,x -> t++);
        count.put(uid,count.getOrDefault(uid,0)+1);
        if (count.get(uid)==2){
            ans.add(node);
        }
        return uid;
    }


    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
