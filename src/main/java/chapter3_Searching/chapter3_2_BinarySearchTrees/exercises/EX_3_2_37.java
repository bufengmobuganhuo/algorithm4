package chapter3_Searching.chapter3_2_BinarySearchTrees.exercises;

import chapter3_Searching.chapter3_2_BinarySearchTrees.BinarySearchTree;
import chapter3_Searching.chapter3_2_BinarySearchTrees.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author zhangyu
 * 2020/3/21 9:19
 * 练习3.2.37：二叉树的层级遍历
 */
public class EX_3_2_37 {
    public static void main(String[] args) {
        BinarySearchTree<Integer,Integer> binarySearchTree=new BinarySearchTree<>();
        binarySearchTree.put(100,100);
        binarySearchTree.put(80,80);
        binarySearchTree.put(120,120);
        binarySearchTree.put(90,90);
        binarySearchTree.put(110,110);
        binarySearchTree.put(125,125);
        binarySearchTree.put(95,95);
        EX_3_2_37 ex_3_2_37=new EX_3_2_37();
        ex_3_2_37.solution(binarySearchTree.root);
    }
    public <Key extends Comparable<Key>,Value> void solution(TreeNode<Key,Value> root){
        if (root==null){
            return;
        }
        Queue<TreeNode<Key,Value>> queue=new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode<Key,Value> node=queue.poll();
            System.out.println(node.key);
            if (node.left!=null){
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right);
            }
        }
    }

    private <Key extends Comparable<Key>,Value> void recursive(TreeNode<Key,Value> root,Queue<TreeNode<Key,Value>> queue){
        if (root==null){
            return;
        }
        queue.add(root.left);
        queue.add(root.right);
        recursive(root.left,queue);
        recursive(root.right,queue);
    }
}
