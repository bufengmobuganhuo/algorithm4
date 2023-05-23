package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_4_HashTables.exercises;

import com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_4_HashTables.SequentialSearchST;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Random;

/**
 * @author zhangyu
 * 2020/4/2 10:32
 * TODO
 */
public class EX_3_4_3_SeparateChainingHashST<Key extends Comparable<Key>,Value> implements Iterable<Key>{
    public static void main(String[] args) {
        EX_3_4_3_SeparateChainingHashST<Integer,Integer> ex_3_4_3_separateChainingHashST=new EX_3_4_3_SeparateChainingHashST<>(5);
        Random random=new Random();
        Integer delte=0;
        for (int i=0;i<10;i++){
            int key=random.nextInt();
            if (i==5){
                delte=key;
            }
            System.out.print(key+" ");
            ex_3_4_3_separateChainingHashST.put(key,key);
        }
        ex_3_4_3_separateChainingHashST.put(5,5);
        ex_3_4_3_separateChainingHashST.delete((Integer) delte);
        ex_3_4_3_separateChainingHashST.delete(5);
        Iterator<Integer> iterator=ex_3_4_3_separateChainingHashST.iterator();
        System.out.println();
        while (iterator.hasNext()){
            System.out.println(iterator.next()+" ");
        }
    }
    private int size;
    private int hashTableSize;
    private SequentialSearchST<Key,Value>[] sequentialSearchSTS;

    public EX_3_4_3_SeparateChainingHashST(int hashTableSize) {
        this.hashTableSize = hashTableSize;
        sequentialSearchSTS=(SequentialSearchST<Key, Value>[]) Array.newInstance(SequentialSearchST.class,hashTableSize);
        for (int i=0;i<hashTableSize;i++){
            sequentialSearchSTS[i]=new SequentialSearchST();
        }
    }

    public void put(Key key, Value value){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int idx=hashCode(key);
        sequentialSearchSTS[idx].put(key,value,size++);
    }

    public void delete(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int idx=hashCode(key);
        size--;
        sequentialSearchSTS[idx].delete(key);
    }

    public void delete(int sizeAtTime){
        size=0;
        for (int i=0;i<hashTableSize;i++){
            sequentialSearchSTS[i].delete(sizeAtTime);
            size+=sequentialSearchSTS[i].size;
        }
    }


    public int hashCode(Key key){
        return (key.hashCode()&0x7fffffff)%hashTableSize;
    }

    @Override
    public Iterator<Key> iterator() {
        return new iterator();
    }

    class iterator implements Iterator<Key>{
        private int idxOuter;
        private int idxInner;
        private int count;
        @Override
        public boolean hasNext() {
            return count<size;
        }

        @Override
        public Key next() {
            count++;
            if (sequentialSearchSTS[idxOuter]!=null&&idxInner<sequentialSearchSTS[idxOuter].size){
                return sequentialSearchSTS[idxOuter].get(idxInner++);
            }
            idxOuter++;
            while (sequentialSearchSTS[idxOuter].size==0){
                idxOuter=(idxOuter+1)%hashTableSize;
            }
            idxInner=0;
            return sequentialSearchSTS[idxOuter].get(idxInner++);
        }
    }
}
