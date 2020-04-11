package chapter3_Searching.chapter3_4_HashTables;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Random;

/**
 * @author zhangyu
 * 2020/4/1 18:54
 * TODO
 */
public class LinearProbingHashST<Key extends Comparable<Key>,Value> {

    public static void main(String[] args) {
        Random random=new Random();
        LinearProbingHashST<Integer,Integer> linearProbingHashST=new LinearProbingHashST<>(5);
        Integer keyToDel=null;
        Integer keyToLazyDel=null;
        for (int i=0;i<10;i++){
            int key=random.nextInt(10);
            if (i==5){
                keyToDel=key;
            }else if(i==6){
                keyToLazyDel=key;
            }
            linearProbingHashST.put(key,key);
            System.out.print(key+" ");
        }
        System.out.println(linearProbingHashST.get(keyToDel));
        linearProbingHashST.delete(keyToDel);
        System.out.println(linearProbingHashST.get(keyToDel));
        linearProbingHashST.lazyDelete(keyToLazyDel);
        System.out.println(linearProbingHashST.get(keyToLazyDel));
    }
    //键值对总数
    private int size;
    //哈希时的被除数
    private int hashTableSize;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST(int hashTableSize) {
        this.hashTableSize = hashTableSize;
        keys= (Key[]) new Comparable[hashTableSize];
        values= (Value[]) new Object[hashTableSize];
    }

    public int hashCode(Key key){
        return (key.hashCode()&0x7fffff)%hashTableSize;
    }

    public void put(Key key,Value value){
        if (key==null){
            throw new IllegalArgumentException();
        }
        if (size>=hashTableSize/2){
            resize(size*2);
        }
        int idx=hashCode(key);
        for (;keys[idx]!=null;idx=(idx+1)%hashTableSize){
            if (key.compareTo(keys[idx])==0){
                values[idx]=value;
                return;
            }
        }
        keys[idx]=key;
        values[idx]=value;
        size++;
    }

    public Value get(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int idx=hashCode(key);
        for (;keys[idx]!=null;idx=(idx+1)%hashTableSize){
            if (key.compareTo(keys[idx])==0){
                return values[idx];
            }
        }
        return null;
    }

    public void delete(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        //保证一定存在，则后面代码不需要判空
        if (!contains(key)){
            return;
        }
        int idx=hashCode(key);
        while (!key.equals(keys[idx])){
            idx=(idx+1)%hashTableSize;
        }
        keys[idx]=null;
        values[idx]=null;
        idx=(idx+1)%hashTableSize;
        //将后面的键往前移动，如果遇到null，说明这个hashCode的都已经被移动完毕，其他的hashCode与这个键无关
        while (keys[idx]!=null){
            Key keyToMove=keys[idx];
            Value valueToMove=values[idx];
            keys[idx]=null;
            values[idx]=null;
            //后面使用put操作，会对size+1，此处需要减回来
            size--;
            put(keyToMove, valueToMove);
            idx=(idx+1)%hashTableSize;
        }
        size--;
        if (size>0&&size==hashTableSize/8){
            resize(hashTableSize/2);
        }
    }

    public void lazyDelete(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }else if(contains(key)){
            int idx=hashCode(key);
            while (!key.equals(keys[idx])){
                idx=(idx+1)%hashTableSize;
            }
            size--;
            values[idx]=null;
        }
    }

    public boolean contains(Key key){
        return get(key)!=null;
    }

    //因为是hash，所以元素不一定是从0到size存储，不可以用arraycopy
    private void resize(int cap){
        LinearProbingHashST<Key,Value> linearProbingHashST=new LinearProbingHashST<>(cap);
        //延时删除时，只是把value=null，而key依然存在，此处只移动key,value都存在的
        for (int i=0;i<hashTableSize;i++){
            if (keys[i]!=null&&values[i]!=null){
                linearProbingHashST.put(keys[i],values[i]);
            }
        }
        keys=linearProbingHashST.keys;
        values=linearProbingHashST.values;
        hashTableSize=linearProbingHashST.hashTableSize;
    }
}
