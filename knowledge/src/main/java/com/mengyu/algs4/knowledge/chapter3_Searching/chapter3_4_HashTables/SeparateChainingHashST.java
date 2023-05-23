package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_4_HashTables;

import java.lang.reflect.Array;

/**
 * @author zhangyu
 * 2020/3/31 16:12
 * TODO
 */
public class SeparateChainingHashST<Key extends Comparable<Key>,Value> {
    public static void main(String[] args) {
        SeparateChainingHashST<Integer,Integer> separateChainingHashST=new SeparateChainingHashST<>(1);
        for (int i=0;i<10;i++){
            separateChainingHashST.put(i,i);
        }
        System.out.println(separateChainingHashST.get(5));
        separateChainingHashST.delete(5);
        System.out.println(separateChainingHashST.get(5));
    }
    //键值对总数
    private int size;
    //散列表大小
    private int hashTableSize;

    private SequentialSearchST<Key,Value>[] sequentialSearchSTS;

    public SeparateChainingHashST(int hashTableSize) {
        this.hashTableSize=hashTableSize;
        sequentialSearchSTS= (SequentialSearchST<Key, Value>[]) Array.newInstance(SequentialSearchST.class,hashTableSize);
        for (int i=0;i<hashTableSize;i++){
            sequentialSearchSTS[i]=new SequentialSearchST<>();
        }
    }

    public int hashCode(Key key) {
        //屏蔽符号位，使其变为无符号整数
        return (key.hashCode()&0x7fffffff)%hashTableSize;
    }

    public void put(Key key,Value value){
        sequentialSearchSTS[hashCode(key)].put(key,value);
    }

    public Value get(Key key){
        return sequentialSearchSTS[hashCode(key)].get(key);
    }

    public void delete(Key key){
        sequentialSearchSTS[hashCode(key)].delete(key);
    }
}
