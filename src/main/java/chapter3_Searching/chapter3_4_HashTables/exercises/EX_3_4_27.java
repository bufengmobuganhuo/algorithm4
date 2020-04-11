package chapter3_Searching.chapter3_4_HashTables.exercises;

import chapter3_Searching.chapter3_4_HashTables.SeparateChainingHashST;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * @author zhangyu
 * 2020/4/3 17:11
 * 练习3.4.27:二次探查
 * 1.算法原理：
 *      采用如下形式的散列函数：h(k,i)=(h1(k)+c1i+c2i^2)mod m，初次探查的位置为T[h1(k)]，
 *      后续的探查都加一个偏移量c1i+c2i^2
 * 2.如果两个键的初始探查位置相同，那么他们的探查序列相同
 */
public class EX_3_4_27<Key extends Comparable<Key>,Value> {
    public static void main(String[] args) {
        EX_3_4_27<String,String> ex_3_4_27=new EX_3_4_27<>(3);
        ex_3_4_27.put("E","E");
        ex_3_4_27.put("A","A");
        ex_3_4_27.put("S","S");
        ex_3_4_27.put("Y","Y");
        ex_3_4_27.put("Q","Q");
        ex_3_4_27.put("U","U");
        ex_3_4_27.put("T","T");
        ex_3_4_27.put("I","I");
        ex_3_4_27.put("O","O");
        ex_3_4_27.put("N","N");
        System.out.println(ex_3_4_27.get("t"));
        ex_3_4_27.delete("t");
        System.out.println(ex_3_4_27.get("t"));
    }
    private int size;
    private int m;
    private QuatraticProbingNode[] nodes;
    private float loadFactor=0.75F;

    public EX_3_4_27(int m) {
        this.m = m;
        nodes=(QuatraticProbingNode[]) Array.newInstance(QuatraticProbingNode.class, m);
    }

    public void put(Key key,Value value){
        if (key==null){
            throw new IllegalArgumentException();
        }
        if (size>=(int) (0.75*m)){
            resize(m*2);
        }
        int hashVal=hashCode(key);
        for (int i=1;nodes[hashVal]!=null;i++){
            if (key.compareTo(nodes[hashVal].key)==0){
                nodes[hashVal].value=value;
                return;
            }
            //此处c1=1,c2=0
            hashVal+=i;
            hashVal%=m;
        }
        nodes[hashVal]=new QuatraticProbingNode(key,value);
        size++;
    }

    public Value get(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int hashVal=hashCode(key);
        for (int i=1;nodes[hashVal]!=null;i++){
            if (key.compareTo(nodes[hashVal].key)==0){
                return nodes[hashVal].value;
            }
            hashVal+=i;
            hashVal%=m;
        }
        return null;
    }

    public void delete(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        //如果不含有这个值，则直接返回
        if (!contains(key)){
            return;
        }
        int hashVal=hashCode(key);
        int i=1;
        //找到该键，并删除
        while (nodes[i]!=null){
            if (nodes[hashVal].key.compareTo(key)==0){
                nodes[hashVal]=null;
                break;
            }
            hashVal+=i;
            hashVal%=m;
            i++;
        }
        hashVal+=i;
        hashVal%=m;
        //将后续节点上移，否则在下次查找时，后续节点无法命中
        while (nodes[hashVal]!=null){
            Key keyToMove=nodes[hashVal].key;
            Value valueToMove=nodes[hashVal].value;
            nodes[hashVal]=null;
            put(keyToMove, valueToMove);
            hashVal+=i;
            hashVal%=m;
            i++;
            size--;
        }
        size--;
        if (size<m/4){
            resize(m/2);
        }
    }

    public boolean contains(Key key){
        if (key==null){
            return false;
        }
        return get(key)!=null;
    }

    private void resize(int cap){
        EX_3_4_27<Key,Value> ex_3_4_27=new EX_3_4_27<>(cap);
        for (int i=0;i<m;i++){
            if (nodes[i]!=null){
                ex_3_4_27.put(nodes[i].key,nodes[i].value);
            }
        }
        nodes=ex_3_4_27.nodes;
        m=cap;
    }

    public int hashCode(Key key){
        return (key.hashCode()&0x7fffffff)%m;
    }

    class QuatraticProbingNode{
        Key key;
        Value value;

        public QuatraticProbingNode(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }


}
