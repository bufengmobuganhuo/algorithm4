package chapter3_Searching.chapter3_5_Applications.exercises;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author zhangyu
 * 2020/4/12 10:12
 * TODO
 */
public class EX_3_5_8_LinearProbingHashST<Key extends Comparable<Key>,Value extends Comparable<Value>> {
    public static void main(String[] args) {
        EX_3_5_8_LinearProbingHashST<Integer,Integer> linearProbingHashST=new EX_3_5_8_LinearProbingHashST<>(3);
        linearProbingHashST.put(1,1);
        linearProbingHashST.put(1,3);
        linearProbingHashST.put(3,2);
        linearProbingHashST.put(2,5);
        linearProbingHashST.put(6,4);
        linearProbingHashST.put(3,7);
        linearProbingHashST.put(7,9);
        linearProbingHashST.put(8,0);
        System.out.println(Arrays.toString(linearProbingHashST.get(1).toArray()));
        System.out.println(linearProbingHashST.get(4));
        linearProbingHashST.delete(1);
        linearProbingHashST.delete(4);
        System.out.println(linearProbingHashST.get(4));
        linearProbingHashST.put(1,1);
        linearProbingHashST.put(1,3);
        System.out.println(linearProbingHashST.get(1));
    }
    private int hashTableSize;
    private int size;
    private DuplicationHashST[] duplicationHashSTS;

    public EX_3_5_8_LinearProbingHashST(int hashTableSize) {
        this.hashTableSize = hashTableSize;
        duplicationHashSTS= (DuplicationHashST[]) Array.newInstance(DuplicationHashST.class,hashTableSize);
    }

    public void put(Key key,Value value){
        if (key==null||value==null){
            throw new IllegalArgumentException();
        }
        if (size>=hashTableSize/2){
            resize(hashTableSize*2);
        }
        int idx=hashCode(key);
        while (duplicationHashSTS[idx]!=null){
            if (duplicationHashSTS[idx].key.equals(key)){
                duplicationHashSTS[idx].value.add(value);
                size++;
                return;
            }
            idx=(idx+1)%hashTableSize;
        }
        List<Value> values=new ArrayList<>();
        values.add(value);
        duplicationHashSTS[idx]=new DuplicationHashST(key,values);
        size++;
    }

    public List<Value> get(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int idx=hashCode(key);
        while (duplicationHashSTS[idx]!=null){
            if (key.equals(duplicationHashSTS[idx].key)){
                return duplicationHashSTS[idx].value;
            }
            idx=(idx+1)%hashTableSize;
        }
        return null;
    }

    public void delete(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        if (!contains(key)){
            return ;
        }
        int idx=hashCode(key);

        while (!key.equals(duplicationHashSTS[idx].key)){

            idx=(idx+1)%hashTableSize;
        }
        duplicationHashSTS[idx]=null;
        idx=(idx+1)%hashTableSize;
        while (duplicationHashSTS[idx]!=null){
            Key keyToMove=duplicationHashSTS[idx].key;
            List<Value> valuesToMove=duplicationHashSTS[idx].value;
            duplicationHashSTS[idx]=null;
            for (Value value:valuesToMove){
                size--;
                put(keyToMove,value);
            }
            idx=(idx+1)%hashTableSize;
        }
        size--;
        if (size>0&&size<=hashTableSize/4){
            resize(hashTableSize/2);
        }
    }

    public boolean contains(Key key){
        return get(key)!=null;
    }

    private void resize(int cap){
        EX_3_5_8_LinearProbingHashST<Key,Value> linearProbingHashST=new EX_3_5_8_LinearProbingHashST<>(cap);
        for (int i=0;i<hashTableSize;i++){
            for (int j=0;duplicationHashSTS[i]!=null&&j<duplicationHashSTS[i].value.size();j++){
                linearProbingHashST.put(duplicationHashSTS[i].key,duplicationHashSTS[i].value.get(j));
            }
        }
        duplicationHashSTS=linearProbingHashST.duplicationHashSTS;
        hashTableSize=cap;
    }

    public int hashCode(Key key){
        return (key.hashCode()&0x7fffffff)%hashTableSize;
    }


    class DuplicationHashST{
        Key key;
        List<Value> value;

        public DuplicationHashST(Key key, List<Value> value) {
            this.key = key;
            this.value = value;
        }
    }
}
