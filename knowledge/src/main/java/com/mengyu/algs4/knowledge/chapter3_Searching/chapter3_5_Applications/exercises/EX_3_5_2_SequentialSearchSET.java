package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_5_Applications.exercises;

/**
 * @author zhangyu
 * 2020/4/11 13:26
 * TODO
 */
public class EX_3_5_2_SequentialSearchSET<Key extends Comparable<Key>> {
    public static void main(String[] args) {
        EX_3_5_2_SequentialSearchSET<Integer> ex_3_5_2_sequentialSearchSET=new EX_3_5_2_SequentialSearchSET<>(5);
        ex_3_5_2_sequentialSearchSET.add(1);
        ex_3_5_2_sequentialSearchSET.add(3);
        ex_3_5_2_sequentialSearchSET.add(2);
        ex_3_5_2_sequentialSearchSET.add(5);
        ex_3_5_2_sequentialSearchSET.add(6);
        System.out.println(ex_3_5_2_sequentialSearchSET.contains(5));
        System.out.println(ex_3_5_2_sequentialSearchSET.contains(4));
        ex_3_5_2_sequentialSearchSET.delete(5);
        System.out.println(ex_3_5_2_sequentialSearchSET.contains(5));
    }
    private int size;
    private int hashTableSize;
    private LinkedList<Key>[] linkedLists;

    public EX_3_5_2_SequentialSearchSET(int hashTableSize) {
        this.hashTableSize = hashTableSize;
        linkedLists= new LinkedList[hashTableSize];
        for (int i=0;i<hashTableSize;i++){
            linkedLists[i]=new LinkedList<>();
        }
    }

    public void delete(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int idx=hashCode(key);
        if (linkedLists[idx].delete(key)){
            size--;
        }
    }

    public void add(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int idx=hashCode(key);
        size++;
        linkedLists[idx].put(key);
    }

    public boolean contains(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int idx=hashCode(key);
        return linkedLists[idx].get(key)!=null;
    }

    public int hashCode(Key key){
        return (key.hashCode()&0x7ffffff)%hashTableSize;
    }
}
