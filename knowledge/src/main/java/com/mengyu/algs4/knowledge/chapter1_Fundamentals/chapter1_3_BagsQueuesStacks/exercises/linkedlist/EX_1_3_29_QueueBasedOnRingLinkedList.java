package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises.linkedlist;

import com.sun.jnlp.IntegrationServiceNSBImpl;

import java.util.Iterator;

/**
 * 环形链表
 */
public class EX_1_3_29_QueueBasedOnRingLinkedList<T> implements Iterable{
    private Node first;
    private Node last;
    private int size;

    public boolean isEmpty(){
        return size==0;
    }
    public void enqueue(T item){
        Node oldLast=last;
        last=new Node();
        last.item=item;
        last.next=first;
        if(isEmpty()){
            first=last;
        }else{
            oldLast.next=last;
        }
        size++;
    }
    public T dequeue(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        Node oldFirst=first;
        first=first.next;
        last.next=first;
        size--;
        return oldFirst.item;
    }

    public Iterator iterator() {
        return new QueueIterator();
    }
    private class QueueIterator implements Iterator<T>{
        private Node current=first;
        //判断是否遍历完
        private int count=size;
        public boolean hasNext() {
            return count!=0;
        }

        public T next() {
            if (!hasNext()){
                throw new IndexOutOfBoundsException();
            }
            T item=current.item;
            current=current.next;
            count--;
            return item;
        }

        public void remove() {

        }
    }

    private class Node{
        T item;
        Node next;
    }
}
