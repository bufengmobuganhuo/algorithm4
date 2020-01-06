package chapter1.chapter103;

import java.util.Iterator;

/**
 * 基于链表的栈
 */
public class StackBasedOnLinkedList<T> implements Iterable{
    private Node first;
    private int size;
    private class Node{
        T item;
        Node next;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public T pop(){
        if (isEmpty()){
            throw new  IndexOutOfBoundsException("栈为空");
        }
        T item=first.item;
        size--;
        first=first.next;
        return item;
    }
    public void push(T item){
        Node oldFirst=first;
        Node node=new Node();
        node.item=item;
        node.next=oldFirst;
        first=node;
        size++;
    }
    public T peek(){
        if(isEmpty()){
            throw new IndexOutOfBoundsException("栈为空");
        }
        return first.item;
    }

    public Iterator iterator() {
        return null;
    }
}
