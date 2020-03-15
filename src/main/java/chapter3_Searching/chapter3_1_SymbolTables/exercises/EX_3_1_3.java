package chapter3_Searching.chapter3_1_SymbolTables.exercises;

import chapter3_Searching.chapter3_1_SymbolTables.SymbolTableTemplate;

import java.util.Iterator;

/**
 * @author zhangyu
 * 2020/3/13 16:34
 * 练习3.1.3：有序链表实现符号表
 */
public class EX_3_1_3<Key extends Comparable<Key>,Value> implements SymbolTableTemplate<Key,Value> {
    public static void main(String[] args) {
        EX_3_1_3<String,Integer> ex_3_1_3=new EX_3_1_3<>();
        ex_3_1_3.put("b",1);
        ex_3_1_3.put("d",4);
        ex_3_1_3.put("e",5);
        ex_3_1_3.put("a",3);
        ex_3_1_3.put("c",3);
        ex_3_1_3.delete("d");
    }
    private Node first;
    private int size;
    @Override
    public Value get(Key key) {
        Node node=find(key);
        if (node!=null&&node.key.compareTo(key)==0){
            return node.value;
        }
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        if (key==null||value==null){
            throw new IllegalArgumentException();
        }
        if (first==null){
            first=new Node(key,value,null);
            size++;
            return;
        }
        Node node=find(key);
        if (node.key.compareTo(key)==0){
            node.value=value;
        }else if(node.key.compareTo(key)<0){
            Node node1=new Node(key,value,node.next);
            node.next=node1;
        }else{
            Node node1=new Node(key,value,node);
            first=node1;
        }
        size++;
    }

    public Node find(Key key) {
        Node node=first;
        while (node!=null&&node.key.compareTo(key)<0&&node.next!=null){
            if (node.next.key.compareTo(key)==0){
                return node;
            }else if (node.next.key.compareTo(key)<0){
                node=node.next;
            }else{
                return node;
            }
        }
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Key> keys() {
        return null;
    }

    @Override
    public void delete(Key key) {
        if (first==null||first.key.equals(key)){
            first=null;
            size--;
            return;
        }
        Node lastNode=first;
        Node node=first.next;
        for (;node!=null;node=node.next){
            if (node.key.equals(key)){
                lastNode.next=node.next;
                node=null;
                size--;
                return;
            }
            lastNode=node;
        }
    }

    @Override
    public Iterator<Key> iterator() {
        return null;
    }

    private class Node{
        private Key key;
        private Value value;

        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
