package chapter1.chapter103;

import java.io.FileReader;
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
        return new StackIterator();
    }
    private class StackIterator implements Iterator{
        private Node current=first;
        public boolean hasNext() {
            return current!=null;
        }

        public T next() {
            if (!hasNext()){
                throw new  IndexOutOfBoundsException("栈为空");
            }
            T item=current.item;
            current=current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
