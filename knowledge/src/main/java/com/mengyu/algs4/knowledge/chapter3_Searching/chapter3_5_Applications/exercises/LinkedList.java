package com.mengyu.algs4.knowledge.chapter3_Searching.chapter3_5_Applications.exercises;

/**
 * @author zhangyu
 * 2020/4/11 13:26
 * TODO
 */
public class LinkedList<Key extends Comparable<Key>> {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList=new LinkedList<>();
        linkedList.put(1);
        linkedList.put(3);
        linkedList.put(2);
        linkedList.put(5);
        linkedList.put(4);
        linkedList.put(6);
        linkedList.delete(4);
        linkedList.reverse();
        linkedList.delete(4);
    }
    private int size;
    private Entry first;

    public void reverse(){
        Entry lastEntry=null;
        Entry cur=first;
        while (cur!=null){
            Entry next=cur.next;
            cur.next=lastEntry;
            lastEntry=cur;
            cur=next;
        }
        first=lastEntry;
    }

    public Key get(Key key){
        Entry temp=first;
        while (temp!=null){
            if (key.compareTo(temp.key)==0){
                return key;
            }
            temp=temp.next;
        }
        return null;
    }

    public boolean delete(Key key){
        if (first==null||first.key.equals(key)){
            first=null;
            size--;
            return true;
        }
        Entry lastEntry=first;
        Entry cur=first;
        while (cur!=null){
            if (key.compareTo(cur.key)==0){
                lastEntry.next=cur.next;
                size--;
                return true;
            }
            lastEntry=cur;
            cur=cur.next;
        }
        return false;
    }



    public void put(Key key){
        if (key==null){
            throw new IllegalArgumentException();
        }
        for (Entry temp=first;temp!=null;temp=temp.next){
            if (key.compareTo(temp.key)==0){
                return;
            }
        }
        Entry next=first;
        first=new Entry(key,next);
        size++;
    }

    class Entry{
        Key key;
        Entry next;

        public Entry(Key key, Entry next) {
            this.key = key;
            this.next = next;
        }
    }
}
