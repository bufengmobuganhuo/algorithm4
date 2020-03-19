package chapter3_Searching.chapter3_2_BinarySearchTrees.exercises;

import chapter3_Searching.chapter3_2_BinarySearchTrees.BinarySearchTree;
import chapter3_Searching.chapter3_2_BinarySearchTrees.TreeNode;
import sun.awt.SunHints;

import java.security.Key;

/**
 * @author zhangyu
 * 2020/3/19 9:54
 * 练习3.2.29：二叉树检查
 */
public class EX_3_2_29 {
    public static void main(String[] args) {
        BinarySearchTree<Integer,Integer> binarySearchTree=new BinarySearchTree<>();
        binarySearchTree.put(100,100);
        binarySearchTree.put(80,80);
        binarySearchTree.put(120,120);
        binarySearchTree.put(90,90);
        binarySearchTree.put(110,110);
        binarySearchTree.put(125,125);
        binarySearchTree.put(95,95);
        EX_3_2_29 ex_3_2_29=new EX_3_2_29();
        System.out.println(ex_3_2_29.isBinaryTree(binarySearchTree.root));
    }
    public <Key extends Comparable<Key>,Value> boolean isBinaryTree(TreeNode<Key,Value> node){
        if (node==null){
            return false;
        }
        return node.nodeCount==nodeCount(node);
    }
    private <Key extends Comparable<Key>,Value> int nodeCount(TreeNode<Key, Value> node){
        if (node==null){
            return 0;
        }
        int count=0;
        count+=nodeCount(node.left);
        count++;
        count+=nodeCount(node.right);
        return count;
    }
}
