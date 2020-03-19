package chapter3_Searching.chapter3_2_BinarySearchTrees.exercises;

import chapter3_Searching.chapter3_2_BinarySearchTrees.BinarySearchTree;
import chapter3_Searching.chapter3_2_BinarySearchTrees.TreeNode;

/**
 * @author zhangyu
 * 2020/3/19 15:53
 * 练习3.2.31：等值性检查
 */
public class EX_3_2_31 {
    public static void main(String[] args) {
        EX_3_2_31 ex_3_2_31=new EX_3_2_31();
        BinarySearchTree<Integer,Integer> binarySearchTree=new BinarySearchTree<>();
        binarySearchTree.put(100,100);
        binarySearchTree.put(80,80);
        binarySearchTree.put(120,120);
        binarySearchTree.put(90,90);
        binarySearchTree.put(110,110);
        binarySearchTree.put(125,125);
        binarySearchTree.put(95,95);
        binarySearchTree.put(95,95);
        System.out.println(ex_3_2_31.hasNoDuplicates(binarySearchTree.root));
    }
    public <Key extends Comparable<Key>,Value> boolean hasNoDuplicates(TreeNode<Key,Value> node){
        if (node==null){
            return true;
        }
        if (node.left!=null&&node.key.compareTo(node.left.key)==0){
            return false;
        }
        if (node.right!=null&&node.key.compareTo(node.right.key)==0){
            return false;
        }
        return hasNoDuplicates(node.left)&&hasNoDuplicates(node.right);
    }
}
