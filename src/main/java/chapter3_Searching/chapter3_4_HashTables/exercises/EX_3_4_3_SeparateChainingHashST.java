package chapter3_Searching.chapter3_4_HashTables.exercises;

import chapter3_Searching.chapter3_4_HashTables.SequentialSearchST;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * @author zhangyu
 * 2020/4/2 10:32
 * TODO
 */
public class EX_3_4_3_SeparateChainingHashST<Key extends Comparable<Key>,Value> {
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

}
