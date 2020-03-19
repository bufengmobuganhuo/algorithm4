package chapter3_Searching.chapter3_2_BinarySearchTrees.exercises;

import chapter3_Searching.chapter3_2_BinarySearchTrees.BinarySearchTree;
import chapter3_Searching.chapter3_2_BinarySearchTrees.TreeNode;

import javax.swing.text.html.MinimalHTMLWriter;

/**
 * @author zhangyu
 * 2020/3/19 10:07
 * 练习3.2.30：有序性检查
 */
public class EX_3_2_30 {
    public static void main(String[] args) {
        EX_3_2_30 ex_3_2_30=new EX_3_2_30();
        BinarySearchTree<Integer,Integer> binarySearchTree=new BinarySearchTree<>();
        binarySearchTree.put(100,100);
        binarySearchTree.put(80,80);
        binarySearchTree.put(120,120);
        binarySearchTree.put(90,90);
        binarySearchTree.put(110,110);
        binarySearchTree.put(125,125);
        binarySearchTree.put(95,95);
        System.out.println(ex_3_2_30.isOrdered(binarySearchTree.root,85,125));
    }
    public <Key extends Comparable<Key>,Value> boolean isOrdered(TreeNode<Key,Value> node, Key min,Key max){
        if (node==null){
            return true;
        }
        return traversal(node,min,max);
    }
    private <Key extends Comparable<Key>,Value> boolean traversal(TreeNode<Key,Value> node,Key min,Key max){
        if (node==null){
            return true;
        }
        return traversal(node.left,min,max)&&
                node.key.compareTo(min)>=0&&
                node.key.compareTo(max)<=0&&
                traversal(node.right,min,max);
    }

}
