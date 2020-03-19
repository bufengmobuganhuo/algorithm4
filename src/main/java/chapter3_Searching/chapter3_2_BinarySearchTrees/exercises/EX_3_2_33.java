package chapter3_Searching.chapter3_2_BinarySearchTrees.exercises;

import chapter3_Searching.chapter3_2_BinarySearchTrees.BinarySearchTree;

/**
 * @author zhangyu
 * 2020/3/19 19:35
 * 练习3.2.33：选择排名检查
 */
public class EX_3_2_33 {
    public static void main(String[] args) {
        BinarySearchTree<Integer,Integer> binarySearchTree=new BinarySearchTree<>();
        binarySearchTree.put(100,100);
        binarySearchTree.put(80,80);
        binarySearchTree.put(120,120);
        binarySearchTree.put(90,90);
        binarySearchTree.put(110,110);
        binarySearchTree.put(125,125);
        binarySearchTree.put(95,95);
        EX_3_2_33 ex_3_2_33=new EX_3_2_33();
        System.out.println(ex_3_2_33.solution(binarySearchTree));
    }
    public <Key extends Comparable<Key>,Value> boolean solution(BinarySearchTree<Key,Value> binarySearchTree){
        for (int i=0;i<binarySearchTree.size();i++){
            if (i!=binarySearchTree.rank(binarySearchTree.select(i).key)){
                return false;
            }
        }
        return true;
    }

}
