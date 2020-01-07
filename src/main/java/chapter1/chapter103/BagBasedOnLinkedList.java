package chapter1.chapter103;

import java.util.Iterator;

public class BagBasedOnLinkedList<T> implements Iterable{
    private Node first;
    private int size;
    private class Node{
        T item;
        Node next;
    }
    public void add(T item){
        Node oldFirst=first;
        first=new Node();
        first.item=item;
        first.next=oldFirst;
        size++;
    }
    public Iterator iterator() {
        return new BagIterator();
    }
    private class BagIterator implements Iterator<T>{
        private Node current=first;
        public boolean hasNext() {
            return current!=null;
        }

        public T next() {
            if (!hasNext()){
                throw new IndexOutOfBoundsException("背包为空");
            }
            T item=current.item;
            current=current.next;
            return item;
        }

        public void remove() {

        }
    }
}
