package chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises.linkedlist;

import java.awt.event.ItemEvent;
import java.util.Iterator;

/**
 * 双向链表
 */
public class EX_1_3_31_DoublyLinkedList<T> implements Iterable{
    public DoubleNode head;
    public DoubleNode end;
    public int size;
    public boolean isEmpty(){
        return size==0;
    }
    public void addAtHead(T item){
        DoubleNode oldHead=head;
        head=new DoubleNode();
        head.item= item;
        if (isEmpty()){
            head=end;
        }else{
            head.next=oldHead;
            oldHead.last=head;
        }
        size++;
    }
    public void addAtEnd(T item){
        DoubleNode oldEnd=end;
        end=new DoubleNode();
        end.item=item;
        if (isEmpty()){
            head=end;
        }else{
            oldEnd.next=end;
            end.last=oldEnd;
        }
        size++;
    }
    public void removeAtHead(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        if (size==1){
            head=null;
            end=null;
            return;
        }
        head=head.next;
        head.last=null;
        size--;
    }
    public void removeAtEnd(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        if (size==1){
            end=null;
            head=end;
            return;
        }
        end=end.last;
        end.next=null;
    }
    public void insertBefore(DoubleNode node,T item){
        if (node==null){
            return;
        }
        DoubleNode lastNode=node.last;
        if (lastNode==null){
            addAtHead(item);
            return;
        }
        DoubleNode node1=new DoubleNode();
        node.item=item;
        lastNode.next=node1;
        node1.last=lastNode;
        node1.next=node;
        node.last=node1;
        size++;
    }
    public void insertAfter(DoubleNode node, T item){
        if (node==null){
            return;
        }
        DoubleNode nextNode=node.next;
        if (nextNode==null){
            addAtEnd(item);
            return;
        }
        DoubleNode node1=new DoubleNode();
        node1.item=item;
        nextNode.last=node1;
        node1.next=nextNode;
        node.next=node1;
        node1.last=node;
        size++;
    }
    public void remove(DoubleNode node){
        if (node==null){
            return;
        }
        DoubleNode lastNode=node.last;
        DoubleNode nextNode=node.next;
        if (lastNode==null){
            removeAtHead();
        }else if(nextNode==null){
            removeAtEnd();
        }else{
            lastNode.next=nextNode;
            nextNode.last=lastNode;
        }
    }

    public Iterator iterator() {
        return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements Iterator<T>{
        private DoubleNode current=head;
        public boolean hasNext() {
            return current!=null;
        }

        public T next() {
            if (!hasNext()){
                throw new IndexOutOfBoundsException();
            }
            T item=current.item;
            current=current.next;
            return item;
        }

        public void remove() {

        }
    }
    public class DoubleNode{
        public DoubleNode last;
        T item;
        public DoubleNode next;
    }
}
