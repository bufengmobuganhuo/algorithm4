package chapter3_Searching.chapter3_4_HashTables.exercises;

import chapter3_Searching.chapter3_4_HashTables.LinearProbingHashST;

import java.lang.reflect.Array;

/**
 * @author zhangyu
 * 2020/4/3 21:39
 * 练习3.4.28：二次散列
 * 1.开放地址法：
 *      当冲突发生时，使用某种探查(亦称探测)技术在散列表中形成一个探查(测)序列（线性探测法是逐个向后查找）。
 * 沿此序列逐个单元地查找，直到找到给定的关键字，或者碰到一个开放的地址(即该地址单元为空)为止
 * 2.二次散列算法原理：开放地址法中最好的方法之一
 *      （1）插入：给定两个独立的哈希函数h1,h2，键k在第j次查找位置时的索引为:h(k,j)=(h1(k)+j*h2(k))%m
 * 其中，h2可以看做发生冲突后，每次哈希值变化的步长
 *      （2）删除：无法真正的删除，只能标记为删除
 *      要求：h2(k)必须与表的大小m互素，方法一：取m为2的幂，并设计一个总产生奇数的h2，
 *          方法二：取m为素数，并设计一个总返回较m小的正整数的函数h2
 */
public class EX_3_4_28<Key extends Comparable<Key>,Value> {
    public static void main(String[] args) {
        EX_3_4_28<String,String> ex_3_4_28=new EX_3_4_28<>(11);
        ex_3_4_28.put("E","E");
        ex_3_4_28.put("A","A");
        ex_3_4_28.put("S","S");
        ex_3_4_28.put("Y","Y");
        ex_3_4_28.put("Q","Q");
        ex_3_4_28.put("U","U");
        ex_3_4_28.put("T","T");
        ex_3_4_28.put("I","I");
        ex_3_4_28.put("O","O");
        ex_3_4_28.put("N","N");
        System.out.println(ex_3_4_28.get("U"));
        System.out.println(ex_3_4_28.delete("Z"));
        System.out.println(ex_3_4_28.get("Z"));

    }
    private int m;
    private int size;
    private DoubleHashingNode[] nodes;

    public EX_3_4_28(int m) {
        this.m = m;
        nodes= (DoubleHashingNode[]) Array.newInstance(DoubleHashingNode.class,m);
    }

    public void put(Key key,Value value){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int hashVal=hashCode1(key);
        int stepSize=hashCode2(key);
        while (nodes[hashVal]!=null){
            if (nodes[hashVal].key.equals(key)){
                nodes[hashVal].value=value;
                nodes[hashVal].isDeleted=false;
                size++;
                return;
            }
            hashVal+=stepSize;
            hashVal%=m;
        }
        nodes[hashVal]=new DoubleHashingNode(key,value);
        size++;
    }

    public Value get(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int hashVal=hashCode1(key);
        int stepSize=hashCode2(key);
        while (nodes[hashVal]!=null&&!nodes[hashVal].isDeleted){
            if (nodes[hashVal].key.compareTo(key)==0){
                return nodes[hashVal].value;
            }
            hashVal+=stepSize;
            hashVal%=m;
        }
        return null;
    }

    public Value delete(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        int hashVal=hashCode1(key);
        int stepSize=hashCode2(key);
        while (nodes[hashVal]!=null&&!nodes[hashVal].isDeleted){
            if (nodes[hashVal].key.equals(key)){
                //无法真正删除，只能标记为删除
                nodes[hashVal].isDeleted=true;
                size--;
                return nodes[hashVal].value;
            }
            hashVal+=stepSize;
            hashVal%=m;
        }
        return null;
    }

    public int hashCode1(Key key){
        return (key.hashCode()&0x7fffffff)%m;
    }

    public int hashCode2(Key key){
        return 1+(key.hashCode()%(m-1));
    }

    class DoubleHashingNode{
        Key key;
        Value value;
        boolean isDeleted;

        public DoubleHashingNode(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

}
