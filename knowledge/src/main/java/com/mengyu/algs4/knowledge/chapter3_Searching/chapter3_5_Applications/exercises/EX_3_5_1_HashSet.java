package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_5_Applications.exercises;

/**
 * @author zhangyu
 * 2020/4/10 17:05
 * TODO
 */
public class EX_3_5_1_HashSet<Key extends Comparable<Key>> {
    public static void main(String[] args) {
        EX_3_5_1_HashSet<Integer> hashSet=new EX_3_5_1_HashSet<>(3);
        hashSet.put(1);
        hashSet.put(3);
        hashSet.put(5);
        hashSet.put(6);
        hashSet.put(7);
        hashSet.put(2);
        System.out.println(hashSet.contains(2));
        System.out.println(hashSet.contains(4));
        hashSet.delete(2);
        hashSet.delete(4);
        System.out.println(hashSet.size);
        System.out.println(hashSet.contains(2));

    }
    private int hashTableSize;
    private int size;
    private Key[] keys;

    public EX_3_5_1_HashSet(int hashTableSize) {
        this.hashTableSize = hashTableSize;
        keys= (Key[]) new Comparable[hashTableSize];
    }

    public void put(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        if (size>=hashTableSize/2){
            resize(hashTableSize*2);
        }
        int idx=hashCode(key);
        for (;keys[idx]!=null;idx=(idx+1)%hashTableSize){
            if (keys[idx].equals(key)){
                return;
            }
        }
        keys[idx]=key;
        size++;
    }

    public boolean contains(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int idx=hashCode(key);
        while (keys[idx]!=null){
            if (keys[idx].equals(key)){
                return true;
            }
            idx=(idx+1)%hashTableSize;
        }
        return false;
    }

    public void delete(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        if (!contains(key)){
            return;
        }
        int idx=hashCode(key);
        while (!keys[idx].equals(key)){
            idx=(idx+1)%hashTableSize;
        }
        keys[idx]=null;
        idx=(idx+1)%hashTableSize;
        while (keys[idx]!=null){
            Key keyToMove=keys[idx];
            keys[idx]=null;
            size--;
            put(keyToMove);
            idx=(idx+1)%hashTableSize;
        }
        size--;
        if (size>0&&size<=hashTableSize/8){
            resize(hashTableSize/2);
        }
    }

    private void resize(int cap){
        EX_3_5_1_HashSet<Key> set=new EX_3_5_1_HashSet<>(cap);
        for (int i=0;i<hashTableSize;i++){
            if (keys[i]!=null){
                set.put(keys[i]);
            }
        }
        keys=set.keys;
        hashTableSize=cap;
    }

    public int hashCode(Key key){
        return (key.hashCode()&0x7fffffff)%hashTableSize;
    }
}