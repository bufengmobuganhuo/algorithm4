package com.mengyu.algs4.exercise.leetcode.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/7/21 2:26 下午
 * TODO
 */
public class Ex437 {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(10);
        root.left=new TreeNode(5);
        root.right=new TreeNode(-3);
        root.left.left=new TreeNode(3);
        root.left.left.left=new TreeNode(3);
        root.left.left.right=new TreeNode(-2);
        root.left.right=new TreeNode(2);
        root.left.right.right=new TreeNode(1);
        root.right.left=new TreeNode(11);
        Ex437 ex437=new Ex437();
        System.out.println(ex437.pathSum(root,8));
    }
    private int count;
    public int pathSum(TreeNode root, int sum) {
        bfs(root,sum);
        return count;
    }

    private void bfs(TreeNode root,int sum){
        if (root==null){
            return;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        dfs(root,sum,0);
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();
            if (node.left!=null){
                dfs(node.left,sum,0);
                queue.offer(node.left);
            }
            if (node.right!=null){
                dfs(node.right,sum,0);
                queue.offer(node.right);
            }
        }
    }

    private void dfs(TreeNode root,int target,int source){
        if (root==null){
            return;
        }
        if (source+root.val==target){
            count++;
        }
        dfs(root.left,target,source+root.val);
        dfs(root.right,target,source+root.val);
    }

    public int pathSum2(TreeNode root, int sum) {
        Map<Integer,Integer> countMap=new HashMap<>();
        // 不包含根结点的情况
        countMap.put(0,1);
        return dfs(root,countMap,sum,0);
    }

    /**
     * 1. 如果 "从根结点到当前结点的路径和=sum+从根结点到之前某个结点的路径和"，说明存在一个子路径的和=sum
     * 2. 可以使用map记录下"从根结点到当前遍历到的结点的路径和"出现次数，以备后面结点使用
    * */
    private int dfs(TreeNode root, Map<Integer,Integer> countMap,int target,int source){
        if (root==null){
            return 0;
        }
        // 从根结点到当前结点的路径和
        source+=root.val;
        int cnt= countMap.getOrDefault(source-target,0);
        countMap.put(source,countMap.getOrDefault(source,0)+1);
        cnt+=dfs(root.left,countMap,target,source);
        cnt+=dfs(root.right,countMap,target,source);
        // 为了上一层遍历，清空当前层的值
        countMap.put(source,countMap.get(source)-1);
        return cnt;
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
