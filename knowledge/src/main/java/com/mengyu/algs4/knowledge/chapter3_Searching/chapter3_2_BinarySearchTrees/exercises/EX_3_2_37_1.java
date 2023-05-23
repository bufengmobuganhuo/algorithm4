package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_2_BinarySearchTrees.exercises;

import com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_2_BinarySearchTrees.BinarySearchTree;
import com.mengyu.algs4.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuzhang
 * @date 2020/7/6 11:39 上午
 * 二叉树层级便利，第一次尝试
 */
public class EX_3_2_37_1 {
    public static void main(String[] args) {
        BinarySearchTree<Integer,Integer> binarySearchTree=new BinarySearchTree<>();
        binarySearchTree.put(100,100);
        binarySearchTree.put(80,80);
        binarySearchTree.put(120,120);
        binarySearchTree.put(90,90);
        binarySearchTree.put(110,110);
        binarySearchTree.put(125,125);
        binarySearchTree.put(95,95);
        EX_3_2_37_1 ex_3_2_37=new EX_3_2_37_1();
        ex_3_2_37.solution(binarySearchTree.root);
    }
    public <Key extends Comparable<Key>,Value> void solution(TreeNode<Key,Value> root){
        if (root==null){
            return;
        }
        Queue<TreeNode<Key,Value>> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode<Key,Value> node=queue.poll();
            System.out.println(node.key);
            if (node.left!=null){
                queue.offer(node.left);
            }
            if (node.right!=null){
                queue.offer(node.right);
            }
        }
    }
}
