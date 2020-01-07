package chapter1.chapter103;

import java.util.Iterator;

/**
 * 基于链表的队列
 */
public class QueueBasedOnLinkedList<T> implements Iterable{
    //队首元素
    private Node first;
    //队尾元素
    private Node last;
    //队列长度
    public int length;

    public Iterator iterator() {
        return new QueueIterator();
    }
    private class QueueIterator implements Iterator<T>{
        private Node current=first;
        public boolean hasNext() {
            return current!=null;
        }

        public T next() {
            if (!hasNext()){
                throw new IndexOutOfBoundsException("队列为空");
            }
            T item=current.item;
            current=current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    private class Node{
        T item;
        Node next;
    }
    public boolean isEmpty(){
        return length==0;
    }

    public void enqueue(T item){
        Node oldLast=last;
        last=new Node();
        last.item=item;
        //如果此时队列为空，则入队一个元素后，首尾指针指向同一个元素
        if (isEmpty()){
            first=last;
        }else{
            oldLast.next=last;
        }
        length++;
    }

    public T dequeue(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException("队列为空");
        }
        Node node=first;
        //如果队列只有一个元素，则出队后元素为空
        if (length==1){
            last=null;
        }
        first=first.next;
        length--;
        return node.item;
    }
}
