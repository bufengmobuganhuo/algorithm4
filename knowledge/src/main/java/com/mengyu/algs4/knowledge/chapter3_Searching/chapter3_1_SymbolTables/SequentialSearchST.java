package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_1_SymbolTables;

import java.util.Iterator;

/**
 * @author zhangyu
 * 2020/3/13 10:25
 * 无序链表中的顺序查找
 */
public class SequentialSearchST<Key,Value> implements SymbolTableTemplate<Key,Value>{
    public static void main(String[] args) {
        SequentialSearchST<String,Integer> sequentialSearchST=new SequentialSearchST<>();
        sequentialSearchST.put("a",1);
        sequentialSearchST.put("b",2);
        sequentialSearchST.put("c",3);
        sequentialSearchST.put("d",4);
        System.out.println(sequentialSearchST.get("c"));
        sequentialSearchST.delete("c");
        System.out.println(sequentialSearchST.get("c"));
        System.out.println(sequentialSearchST.size());
        Iterator<String> iterator=sequentialSearchST.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    private Node first;
    private int size;

    @Override
    public Value get(Key key) {
        for (Node node=first;node!=null;node=node.next){
            if (node.key.equals(key)){
                return node.value;
            }
        }
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        if (key==null||value==null){
            throw new IllegalArgumentException();
        }
        for (Node node=first;node!=null;node=node.next){
            if (node.key.equals(key)){
                node.value=value;
                return;
            }
        }
        Node node=new Node(key,value,first);
        first=node;
        size++;
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
        return new Itr();
    }

    private class Itr implements Iterator<Key>{
        private Node node=first;
        @Override
        public boolean hasNext() {
            return node!=null;
        }

        @Override
        public Key next() {
            Key res=node.key;
            node=node.next;
            return res;
        }
    }

    private class Node{
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value,Node next) {
            this.key = key;
            this.value = value;
            this.next=next;
        }
    }
}
