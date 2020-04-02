package chapter3_Searching.chapter3_4_HashTables.exercises;

import java.lang.reflect.Array;

/**
 * @author zhangyu
 * 2020/4/2 14:21
 * 练习3.4.10：重新实现基于线性探测法的散列表
 */
public class EX_3_4_10<Key extends Comparable<Key>,Value> {
    public static void main(String[] args) {
        EX_3_4_10<String,String> ex_3_4_10=new EX_3_4_10<>(16);
        ex_3_4_10.put("E","E");
        ex_3_4_10.put("A","E");
        ex_3_4_10.put("S","E");
        ex_3_4_10.put("Y","E");
        ex_3_4_10.put("Q","E");
        ex_3_4_10.put("U","E");
        ex_3_4_10.put("T","E");
        ex_3_4_10.put("I","E");
        ex_3_4_10.put("O","E");
        ex_3_4_10.put("N","E");
        System.out.println(ex_3_4_10.get("Q"));
        ex_3_4_10.delete("Q");
        System.out.println(ex_3_4_10.get("Q"));
        System.out.println(ex_3_4_10.get("S"));
    }
    private EX_3_4_10_Node[] nodes;
    private int hashTableSize;
    private int size;

    public EX_3_4_10(int hashTableSize) {
        this.hashTableSize = hashTableSize;
        nodes= (EX_3_4_10_Node[])Array.newInstance(EX_3_4_10_Node.class,hashTableSize);
    }

    public void put(Key key,Value value){
        if (key==null){
            throw new IllegalArgumentException();
        }
        if (size>=hashTableSize/2){
            resize(hashTableSize*2);
        }
        int idx=hashCode(key);
        while (nodes[idx]!=null){
            if (nodes[idx].key.equals(key)){
                nodes[idx].value=value;
                return;
            }
            idx=(idx+1)%hashTableSize;
        }
        size++;
        nodes[idx]=new EX_3_4_10_Node(key,value);
    }

    public Value get(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int idx=hashCode(key);
        for (;nodes[idx]!=null;idx=(idx+1)%hashTableSize){
            if (key.equals(nodes[idx].key)){
                return nodes[idx].value;
            }
        }
        return null;
    }

    public void delete(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        //保证一定存在
        if (!contains(key)){
            return;
        }
        int idx=hashCode(key);
        while (!key.equals(nodes[idx].key)){
            idx=(idx+1)%hashTableSize;
        }
        nodes[idx]=null;
        idx=(idx+1)%hashTableSize;
        //将后面的键往前移动，如果遇到null，说明这个hashCode的都已经被移动完毕，其他的hashCode与这个键无关
        while (nodes[idx]!=null){
            EX_3_4_10_Node nodeToMove=nodes[idx];
            nodes[idx]=null;
            //put操作会令size++
            size--;
            put(nodeToMove.key,nodeToMove.value);
            idx=(idx+1)%hashTableSize;
        }
        size--;
        if (size>0&&size<=hashTableSize/4){
            resize(hashTableSize/2);
        }
    }

    public int hashCode(Key key){
        return (11*key.hashCode())%hashTableSize;
    }

    public boolean contains(Key key){
        return get(key)!=null;
    }

    private void resize(int cap){
        EX_3_4_10<Key,Value> ex_3_4_10=new EX_3_4_10<>(cap);
        for (int i=0;i<nodes.length;i++){
            if (nodes[i]!=null){
                ex_3_4_10.put(nodes[i].key,nodes[i].value);
            }
        }
        hashTableSize=cap;
        nodes=ex_3_4_10.nodes;
    }

    class EX_3_4_10_Node{
        Key key;
        Value value;

        public EX_3_4_10_Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}
