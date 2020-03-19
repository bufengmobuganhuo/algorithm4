package chapter3_Searching.chapter3_2_BinarySearchTrees.exercises;

import chapter3_Searching.chapter3_2_BinarySearchTrees.BinarySearchTree;

import java.security.Key;

/**
 * @author zhangyu
 * 2020/3/19 19:21
 * 练习3.2.32：判读输入的节点是否是一个二叉查找树的根节点
 */
public class EX_3_2_32 {
    public static void main(String[] args) {
        BinarySearchTree<Integer,Integer> binarySearchTree=new BinarySearchTree<>();
        binarySearchTree.put(100,100);
        binarySearchTree.put(80,80);
        binarySearchTree.put(120,120);
        binarySearchTree.put(90,90);
        binarySearchTree.put(110,110);
        binarySearchTree.put(125,125);
        binarySearchTree.put(95,95);
        EX_3_2_32 ex_3_2_32=new EX_3_2_32();
        System.out.println(ex_3_2_32.isBST(binarySearchTree));
    }
    public boolean isBST(BinarySearchTree<Integer,Integer> tree){
        EX_3_2_29 ex_3_2_29=new EX_3_2_29();
        if (!ex_3_2_29.isBinaryTree(tree.root)){
            return false;
        }
        EX_3_2_30 ex_3_2_30=new EX_3_2_30();
        if (!ex_3_2_30.isOrdered(tree.root,tree.min().key,tree.max().key)){
            return false;
        }
        EX_3_2_31 ex_3_2_31=new EX_3_2_31();
        if (!ex_3_2_31.hasNoDuplicates(tree.root)){
            return false;
        }
        return true;
    }
}
