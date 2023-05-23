package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_2_BinarySearchTrees.exercises;

import com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_2_BinarySearchTrees.BinarySearchTree;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 * @author zhangyu
 * 2020/3/18 19:54
 * 练习3.2.5：完美平衡
 */
public class EX_3_2_25 {
    public static void main(String[] args) {
        EX_3_2_25 ex_3_2_25=new EX_3_2_25();
        Map.Entry<Integer,Integer>[] entries=new Map.Entry[7];
        for (int i=0;i<entries.length;i++){
            Map.Entry<Integer,Integer> entry=new AbstractMap.SimpleEntry<Integer, Integer>(i,i);
            entries[i]=entry;
        }
        BinarySearchTree<Integer,Integer> tree = ex_3_2_25.solution(entries);
        tree.size();
    }
    public <Key extends Comparable<Key>,Value> BinarySearchTree<Key,Value> solution(Map.Entry<Key,Value>[] entries){
        if (entries==null||entries.length==0){
            return null;
        }
        Arrays.sort(entries, new Comparator<Map.Entry<Key, Value>>() {
            @Override
            public int compare(Map.Entry<Key, Value> o1, Map.Entry<Key, Value> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        BinarySearchTree<Key,Value> tree=new BinarySearchTree<>();
        createTree(entries,tree,0,entries.length-1);
        return tree;
    }
    private <Key extends Comparable<Key>,Value> void createTree(Map.Entry<Key,Value>[] entries,BinarySearchTree<Key,Value> binarySearchTree,int lo,int hi){
        if (lo>hi){
            return;
        }
        int mid=lo+(hi-lo)/2;
        binarySearchTree.put(entries[mid].getKey(),entries[mid].getValue());
        createTree(entries,binarySearchTree,lo,mid-1);
        createTree(entries,binarySearchTree,mid+1,hi);
    }

}
