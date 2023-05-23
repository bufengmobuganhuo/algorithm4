package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_5_Applications.exercises;

import java.security.spec.KeySpec;

/**
 * @author zhangyu
 * 2020/4/11 14:34
 * TODO
 */
public class InsertSort<Key extends Comparable<Key>> {
    public void sort(Key[] keys){
        if(keys==null||keys.length==0){
            return;
        }
        for (int i=1;i<keys.length;i++){
            for (int j = i; j>0&& keys[j].compareTo(keys[j-1])<0; j--){
                exch(keys,j,j-1);
            }
        }
    }
    private void exch(Key[] keys,int i,int j){
        Key temp=keys[i];
        keys[i]=keys[j];
        keys[j]=temp;
    }

}
