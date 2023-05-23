package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/7/26 4:44 下午
 * TODO
 */
public class Ex655 {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.right=new TreeNode(4);
        Ex655 ex655=new Ex655();
        ex655.printTree(root);
    }
    private List<List<String>> ans;
    private int nodeNum;
    private int depth;
    public List<List<String>> printTree(TreeNode root) {
        if (root==null){
            return new ArrayList<>();
        }
        depth=getDepth(root,0);
        ans=new ArrayList<>(depth);
        nodeNum= (int) (Math.pow(2,depth)+ Math.pow(2,depth)-1);
        for (int i = 0; i < depth+1; i++) {
            List<String> list=new ArrayList<>(nodeNum);
            for (int j = 0; j < nodeNum; j++) {
                list.add("");
            }
            ans.add(list);
        }
        buildTree(root,0,nodeNum-1,0);
        return ans;
    }

    private void buildTree(TreeNode root,int start,int end,int layer){
        if (start>end||root==null){
            return;
        }
        int mid=start+(end-start)/2;
        List<String> list=ans.get(layer);
        list.set(mid,root==null?"":String.valueOf(root.val));
        if (layer<depth){
            buildTree(root==null?null:root.left,start,mid,layer+1);
            buildTree(root==null?null:root.right,mid+1,end,layer+1);
        }
    }

    private int getDepth(TreeNode root,int depth){
        if (root==null){
            return depth-1;
        }
        int res= Math.max(getDepth(root.left,depth+1),getDepth(root.right,depth+1));
        return res;
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
